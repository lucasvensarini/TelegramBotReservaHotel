package br.com.lcv.passo;

import br.com.lcv.hospede.Hospede;
import br.com.lcv.hospede.HospedeService;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.Quarto;
import br.com.lcv.reserva.Reserva;
import br.com.lcv.reserva.ReservaDTO;
import br.com.lcv.reserva.ReservaService;
import br.com.lcv.sessao.Sessao;
import br.com.lcv.sessao.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 02/09/19.
 */
@Component
public class PassoCallbackIniciaCriacaoReserva implements PassoCallback {

    private final HospedeService hospedeService;
    private final ReservaService reservaService;
    private final SessaoService sessaoService;

    @Autowired
    public PassoCallbackIniciaCriacaoReserva(HospedeService hospedeService, ReservaService reservaService, SessaoService sessaoService) {
        this.hospedeService = hospedeService;
        this.reservaService = reservaService;
        this.sessaoService = sessaoService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {

        ReservaDTO reservaDTO = sessao.getReservaDTO();
        Hotel hotelSelecionado = sessao.getHotelSelecionado();
        Quarto quartoSelecionado = sessao.getQuartoSelecionado();

        Hospede hospedeResponsavel = hospedeService.selecionaHospedePorCPF(reservaDTO.getHospedes(), valorCallback);

        return new ArrayList<>(criaReserva(usuarioTelegramId, chatId, reservaDTO, hotelSelecionado, quartoSelecionado, hospedeResponsavel));
    }

    private List<Mensagem> criaReserva(long usuarioTelegramId, String chatId, ReservaDTO reservaDTO, Hotel hotelSelecionado,
                                       Quarto quartoSelecionado, Hospede hospedeResponsavel) {

        List<Mensagem> mensagens = new ArrayList<>();

        Reserva reserva = reservaService.reservaHotel(hotelSelecionado, quartoSelecionado, hospedeResponsavel, reservaDTO, usuarioTelegramId);

        sessaoService.encerraSessaoUsuario(usuarioTelegramId);

        mensagens.add(new Mensagem(exibeInformacoesReserva(chatId, reserva)));
        mensagens.add(new Mensagem(enviaMensagemInicial(chatId)));

        return mensagens;
    }

    private SendMessage exibeInformacoesReserva(String chatId, Reserva reserva) {
        String mensagem = "Sua reserva foi criada com sucesso! Segue informações:" + "\n\n" + reserva.listaDadosReserva();
        return SendMessage.builder().chatId(chatId).text(mensagem).parseMode("html").build();
    }

    private SendMessage enviaMensagemInicial(String chatId) {
        String mensagem = "Utilize os seguintes comandos:" + "\n\n" + "/reservarhotel - Inicia processo de reserva de hotel.";
        return new SendMessage(chatId, mensagem);
    }

}
