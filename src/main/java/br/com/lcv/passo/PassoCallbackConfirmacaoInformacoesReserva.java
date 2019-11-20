package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hospede.FaixaEtaria;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 30/08/19.
 */
@Component
public class PassoCallbackConfirmacaoInformacoesReserva implements PassoCallback {

    private KeyboardService keyboardService;
    private HospedeService hospedeService;
    private ReservaService reservaService;
    private SessaoService sessaoService;

    @Autowired
    public PassoCallbackConfirmacaoInformacoesReserva(KeyboardService keyboardService, HospedeService hospedeService,
                                                      ReservaService reservaService, SessaoService sessaoService) {
        this.keyboardService = keyboardService;
        this.hospedeService = hospedeService;
        this.reservaService = reservaService;
        this.sessaoService = sessaoService;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        ReservaDTO reservaDTO = sessao.getReservaDTO();
        Hotel hotelSelecionado = sessao.getHotelSelecionado();
        Quarto quartoSelecionado = sessao.getQuartoSelecionado();

        long qtdAdultos = hospedeService.defineQtdHospedesPorFaixaEtaria(reservaDTO.getHospedes(), FaixaEtaria.ADT);
        if (qtdAdultos > 1) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.ESCOLHA_RESPONSAVEL);
            String texto = "Só uma última perguntinha para finalizar: quem vai ser o responsável pela reserva?";
            InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaResponsavelReservaKeyboard(reservaDTO.getHospedes());
            SendMessage sendMessage = new SendMessage(chatId, texto).setReplyMarkup(inlineKeyboardMarkup);
            mensagens.add(new Mensagem(sendMessage));
        } else {
            Hospede hospedeResponsavel = hospedeService.selecionaHospedeAdulto(reservaDTO.getHospedes());
            mensagens.addAll(criaReserva(usuarioTelegramId, chatId, reservaDTO, hotelSelecionado, quartoSelecionado, hospedeResponsavel));
        }

        return mensagens;
    }

    private List<Mensagem> criaReserva(Integer usuarioTelegramId, Long chatId, ReservaDTO reservaDTO, Hotel hotelSelecionado,
                                       Quarto quartoSelecionado, Hospede hospedeResponsavel) {

        List<Mensagem> mensagens = new ArrayList<>();

        Reserva reserva = reservaService.reservaHotel(hotelSelecionado, quartoSelecionado, hospedeResponsavel, reservaDTO, usuarioTelegramId);

        // salvaReserva

        sessaoService.encerraSessaoUsuario(usuarioTelegramId);

        mensagens.add(new Mensagem(exibeInformacoesReserva(chatId, reserva)));
        mensagens.add(new Mensagem(enviaMensagemInicial(chatId)));

        return mensagens;
    }

    private SendMessage exibeInformacoesReserva(Long chatId, Reserva reserva) {
        String mensagem = "Sua reserva foi criada com sucesso! Segue informações:" + "\n\n" + reserva.listaDadosReserva();
        return new SendMessage(chatId, mensagem).enableHtml(true);
    }

    private SendMessage enviaMensagemInicial(Long chatId) {
        String mensagem = "Utilize os seguintes comandos:" + "\n\n" + "/reservarhotel - Inicia processo de reserva de hotel.";
        return new SendMessage(chatId, mensagem);
    }

}
