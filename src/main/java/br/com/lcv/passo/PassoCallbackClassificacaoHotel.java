package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hotel.Classificacao;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.HotelService;
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
public class PassoCallbackClassificacaoHotel implements PassoCallback {

    private KeyboardService keyboardService;
    private HotelService hotelService;

    @Autowired
    public PassoCallbackClassificacaoHotel(KeyboardService keyboardService, HotelService hotelService) {
        this.keyboardService = keyboardService;
        this.hotelService = hotelService;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        int classificacaoValor = Integer.parseInt(valorCallback);
        Classificacao classificacao = Classificacao.getClassificacaoByValor(classificacaoValor);
        sessao.getReservaDTO().getDadosBusca().setClassificacao(classificacao);

        SendMessage sendMessage = new SendMessage().setChatId(chatId);

        List<Hotel> hoteis = hotelService.buscaHoteis(sessao.getReservaDTO());
        sessao.adicionaAtributoHoteisListados(hoteis);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.ESCOLHA_HOTEL);

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaListaHoteisKeyboard(hoteis);
        sendMessage.setText("Encontrei esses hotéis. Selecione um para ver mais detalhes e os quartos disponíveis.");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        mensagens.add(new Mensagem(new SendMessage(chatId, "Vou buscar alguns hotéis pra você. Só um segundo...")));
        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
