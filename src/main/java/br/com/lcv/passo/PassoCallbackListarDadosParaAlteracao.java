package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.reserva.ReservaDTO;
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
public class PassoCallbackListarDadosParaAlteracao implements PassoCallback {

    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackListarDadosParaAlteracao(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        int indiceHospede = Integer.parseInt(valorCallback);

        ReservaDTO reservaDTO = sessao.getReservaDTO();
        List<Hospede> hospedes = reservaDTO.getHospedes();
        Hospede hospede = hospedes.get(indiceHospede);

        sessao.adicionaAtributoHospedeCorrente(hospede);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.MODIFICAR_DADOS_HOSPEDES);

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaDadosHospedeKeyboard(hospede);
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text("Qual informação você quer alterar?").replyMarkup(inlineKeyboardMarkup).build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
