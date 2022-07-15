package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/08/19.
 */
@Component
public class PassoCallbackQuartoSelecionado implements PassoCallback {

    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackQuartoSelecionado(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_HOSPEDES);

        List<Hospede> hospedes = sessao.getReservaDTO().getHospedes();

        String mensagem = "Vamos começar a preencher os dados dos hóspedes.";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaHospedesKeyboard(hospedes);
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
