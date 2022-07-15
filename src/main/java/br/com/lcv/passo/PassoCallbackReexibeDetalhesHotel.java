package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 02/09/19.
 */
@Component
public class PassoCallbackReexibeDetalhesHotel implements PassoCallback {

    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackReexibeDetalhesHotel(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        Hotel hotelSelecionado = sessao.getHotelSelecionado();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.ESCOLHA_QUARTO);

        String mensagem = hotelSelecionado.listaDadosHotel();
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaMaisInfoListaQuartosHotelKeyboard(hotelSelecionado.getQuartos());
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).parseMode("html").build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
