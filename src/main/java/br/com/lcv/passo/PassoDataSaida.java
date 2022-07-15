package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created by lucas on 28/08/19.
 */
@Component
public class PassoDataSaida implements Passo {

    private final KeyboardService keyboardService;

    @Autowired
    public PassoDataSaida(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public SendMessage executa(String chatId, String texto, Sessao sessao) {
        sessao.getReservaDTO().getDadosBusca().defineDataSaida(texto);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_CLASSIFICACAO_HOTEL);

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaClassificacaoHotelKeyboard();

        return SendMessage.builder().chatId(chatId).text("Gostaria de filtrar por classificação?").replyMarkup(inlineKeyboardMarkup).build();
    }

}
