package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.Quarto;
import br.com.lcv.reserva.ReservaDTO;
import br.com.lcv.reserva.ReservaService;
import br.com.lcv.sessao.Sessao;
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
public class PassoCallbackConfirmacaoHospedes implements PassoCallback {

    private final ReservaService reservaService;
    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackConfirmacaoHospedes(ReservaService reservaService, KeyboardService keyboardService) {
        this.reservaService = reservaService;
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.CONFIRMAR_INFORMACOES);

        ReservaDTO reservaDTO = sessao.getReservaDTO();
        Hotel hotelSelecionado = sessao.getHotelSelecionado();
        Quarto quartoSelecionado = sessao.getQuartoSelecionado();

        String resumoInformacoes = reservaService.exibeResumoInformacoes(hotelSelecionado, quartoSelecionado, reservaDTO);
        String mensagem = "Fechou! Posso finalizar a reserva?" + "\n\n" + resumoInformacoes;
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaConfirmacaoInformacoesReservaKeyboard();

        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).parseMode("html").build();
        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
