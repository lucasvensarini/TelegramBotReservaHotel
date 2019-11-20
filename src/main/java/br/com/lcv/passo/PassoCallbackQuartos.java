package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.hospede.HospedeService;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.HotelService;
import br.com.lcv.hotel.Quarto;
import br.com.lcv.reserva.ReservaDTO;
import br.com.lcv.reserva.ReservaService;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/08/19.
 */
@Component
public class PassoCallbackQuartos implements PassoCallback {

    private HotelService hotelService;
    private ReservaService reservaService;
    private HospedeService hospedeService;
    private KeyboardService keyboardService;
    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackQuartos(HotelService hotelService, ReservaService reservaService, HospedeService hospedeService,
                                KeyboardService keyboardService, MensagemUtil mensagemUtil) {
        this.hotelService = hotelService;
        this.reservaService = reservaService;
        this.hospedeService = hospedeService;
        this.keyboardService = keyboardService;
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        long quartoId = Long.parseLong(valorCallback);

        ReservaDTO reservaDTO = sessao.getReservaDTO();
        List<Hospede> hospedes = reservaDTO.getHospedes();
        Hotel hotelSelecionado = sessao.getHotelSelecionado();

        Quarto quartoSelecionado = hotelService.filtraQuartoPorId(hotelSelecionado, quartoId);
        sessao.adicionaAtributoQuartoSelecionado(quartoSelecionado);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.CONFIRMAR_QUARTO);

        String mensagem = quartoSelecionado.listaDadosQuarto();
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaMaisInfoQuartoKeyboard();
        SendMessage sendMessageInfosQuarto = new SendMessage(chatId, mensagem).enableHtml(true).setReplyMarkup(inlineKeyboardMarkup);

        BigDecimal valorReserva = reservaService.calculaValorReserva(quartoSelecionado);
        String hospedesReservaToString = hospedeService.qtdHospedesToString(hospedes);
        String mensagemConfirmacaoQuarto = "Sua reserva para <b>"
                + hospedesReservaToString
                + "</b>"
                + " custará "
                + "<b>R$"
                + valorReserva
                + "</b>"
                + ". Posso prosseguir com a reserva?";

        InlineKeyboardMarkup inlineKeyboardMarkupConfirmacaoQuarto = keyboardService.montaConfirmacaoQuartoEscolhidoKeyboard();
        SendMessage sendMessageConfirmacaoQuarto = new SendMessage(chatId, mensagemConfirmacaoQuarto)
                .enableHtml(true).setReplyMarkup(inlineKeyboardMarkupConfirmacaoQuarto);

        mensagens.add(new Mensagem(mensagemUtil.criaMediaGroup(chatId, quartoSelecionado.getUrlThumbnail(), quartoSelecionado.getUrlFotos())));
        mensagens.add(new Mensagem(sendMessageInfosQuarto));
        mensagens.add(new Mensagem(sendMessageConfirmacaoQuarto));

        return mensagens;
    }

}
