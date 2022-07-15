package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.HotelService;
import br.com.lcv.hotel.Quarto;
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
public class PassoCallbackHoteis implements PassoCallback {

    private final KeyboardService keyboardService;
    private final HotelService hotelService;
    private final MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackHoteis(KeyboardService keyboardService, HotelService hotelService, MensagemUtil mensagemUtil) {
        this.keyboardService = keyboardService;
        this.hotelService = hotelService;
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        long hotelId = Long.parseLong(valorCallback);

        List<Hotel> hoteisListados = sessao.getHoteisListados();
        int qtdHospedes = sessao.getReservaDTO().getHospedes().size();

        Hotel hotelSelecionado = hotelService.filtraHotelPorId(hoteisListados, hotelId);
        sessao.adicionaAtributoHotelSelecionado(hotelSelecionado);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.ESCOLHA_QUARTO);

        if (!hotelSelecionado.isQuartosBuscados()) {
            List<Quarto> quartos = hotelService.buscaQuartosPorCapacidade(hotelSelecionado, qtdHospedes);
            hotelSelecionado.setQuartos(quartos);
            hotelSelecionado.setQuartosBuscados(true);
        }

        String mensagem = hotelSelecionado.listaDadosHotel();
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaMaisInfoListaQuartosHotelKeyboard(hotelSelecionado.getQuartos());
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).parseMode("html").build();

        mensagens.add(new Mensagem(mensagemUtil.criaMediaGroup(chatId, hotelSelecionado.getUrlThumbnail(), hotelSelecionado.getUrlFotos())));
        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
