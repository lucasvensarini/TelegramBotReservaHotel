package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassoCallbackQtdAdultos implements PassoCallback {

    private static final String MENSAGEM_DEFINICAO_CRIANCA = "Crianças são pessoas com idade entre 1 e 12 anos";

    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackQtdAdultos(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        int qtdHospedesADT = Integer.parseInt(valorCallback);
        sessao.getReservaDTO().defineQtdHospedesADT(qtdHospedesADT);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_CRIANCAS);

        String mensagem = "Quantas crianças vão se hospedar?" + "\n\n" + MENSAGEM_DEFINICAO_CRIANCA;
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaQtdHospedesKeyboard(KeyboardService.PREFIXO_CALLBACK_HOSPEDES_CHD);
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
