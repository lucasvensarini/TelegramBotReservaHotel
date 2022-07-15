package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 27/08/19.
 */
@Component
public class PassoCallbackQtdCriancas implements PassoCallback {

    private static final String MENSAGEM_DEFINICAO_BEBE = "Bebês são pessoas com idade menor que 1 ano";

    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackQtdCriancas(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        int qtdHospedesCHD = Integer.parseInt(valorCallback);
        sessao.getReservaDTO().defineQtdHospedesCHD(qtdHospedesCHD);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_BEBES);

        String mensagem = "Quantos bebês vão se hospedar?" + "\n\n" + MENSAGEM_DEFINICAO_BEBE;
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaQtdHospedesKeyboard(KeyboardService.PREFIXO_CALLBACK_HOSPEDES_INF);
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
