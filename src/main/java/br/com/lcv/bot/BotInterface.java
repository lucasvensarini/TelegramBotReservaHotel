package br.com.lcv.bot;

import br.com.lcv.passo.*;
import br.com.lcv.reserva.ReservaDTO;
import br.com.lcv.reserva.ReservaService;
import br.com.lcv.sessao.Sessao;
import br.com.lcv.sessao.SessaoService;
import br.com.lcv.usuario.UsuarioNaoAutorizadoException;
import br.com.lcv.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * Created by lucas on 27/06/19.
 */
public class BotInterface extends TelegramLongPollingBot {

    @Autowired
    private PassoFactory passoFactory;

    @Autowired
    private PassoCallbackFactory passoCallbackFactory;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private KeyboardService keyboardService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private MensagemUtil mensagemUtil;

    // TODO - substituir pelas credenciais do bot
    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            long usuarioId = update.getMessage().getFrom().getId();
            String chatId = message.getChatId().toString();
            String text = message.getText();
            if (text.startsWith("/start")) {
                enviaMensagem(mensagemUtil.enviaMensagemInicial(chatId));
            } else if (text.startsWith("/reservarhotel")) {
                enviaMensagem(iniciaReservaHotel(message));
            } else {
                SendMessage sendMessage = new SendMessage();
                try {
                    validaSessao(usuarioId);
                    validaUsuario(usuarioId);
                    Sessao sessao = sessaoService.buscaSessaoUsuario(usuarioId);
                    Passo passo = passoFactory.definePasso(sessao);
                    sendMessage = passo.executa(chatId, text, sessao);
                } catch (Exception e) {
                    sendMessage.setChatId(chatId);
                    String mensagemErro = e.getMessage();
                    sendMessage.setText(mensagemErro != null && !mensagemErro.isEmpty() ? mensagemErro : "Ops! Deu ruim aqui hein, belo!");
                }
                enviaMensagem(sendMessage);
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            long usuarioId = callbackQuery.getFrom().getId();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            try {
                validaSessao(usuarioId);
                validaUsuario(usuarioId);
                Sessao sessao = sessaoService.buscaSessaoUsuario(usuarioId);
                String[] split = callbackQuery.getData().split(KeyboardService.CALLBACK_DELIMITADOR);
                String prefixo = split[0];
                String valorCallback = split.length > 1 ? split[1] : "";
                PassoCallback passoCallback = passoCallbackFactory.definePasso(prefixo);

                List<Mensagem> mensagens = passoCallback.executa(usuarioId, chatId, valorCallback, sessao);
                for (Mensagem mensagem : mensagens) {
                    if (mensagem.temBotApiMethod()) {
                        enviaMensagem(mensagem.getBotApiMethod());
                    } else if (mensagem.temMediaGroup()) {
                        enviaMensagem(mensagem.getSendMediaGroup());
                    }
                }
            } catch (Exception e) {
                enviaMensagem(new SendMessage(chatId, e.getMessage()));
            }
        }
    }

    private void validaUsuario(long usuarioId) {
        sessaoService.validaUsuario(usuarioId);
    }

    private void validaSessao(long usuarioId) {
        sessaoService.validaSessao(usuarioId);
    }

    private SendMessage iniciaReservaHotel(Message message) {
        long usuarioId = message.getFrom().getId();
        try {
            usuarioService.buscaUsuarioPorTelegramId(usuarioId);

            ReservaDTO reservaDTO = reservaService.iniciaReservaHotel();

            Sessao sessao = sessaoService.iniciaSessaoUsuario(usuarioId);
            sessao.adicionaAtributoReservaDTO(reservaDTO);
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.CONFIRMAR_NOME_HOTEL);

            String primeiroNome = message.getFrom().getFirstName();
            String mensagem = "Olá, " + primeiroNome + "!" + "\n\n" + "Vamos começar a reservar seu hotel. Você sabe o nome do hotel?";

            return enviaTecladoConfirmacaoNomeHotel(message.getChatId().toString(), mensagem);
        } catch (UsuarioNaoAutorizadoException e) {
            return SendMessage.builder().chatId(message.getChatId().toString()).text(e.getMessage()).build();
        }
    }

    private SendMessage enviaTecladoConfirmacaoNomeHotel(String chatId, String mensagem) {
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaConfirmacaoNomeHotelKeyboard();
        return SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).build();
    }

    private void enviaMensagem(SendMediaGroup sendMediaGroup) {
        try {
            execute(sendMediaGroup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void enviaMensagem(BotApiMethod botApiMethod) {
        try {
            execute(botApiMethod);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
