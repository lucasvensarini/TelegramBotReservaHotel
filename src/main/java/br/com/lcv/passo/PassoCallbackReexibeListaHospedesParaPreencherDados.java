package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.reserva.ReservaDTO;
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
public class PassoCallbackReexibeListaHospedesParaPreencherDados implements PassoCallback {

    private KeyboardService keyboardService;

    @Autowired
    public PassoCallbackReexibeListaHospedesParaPreencherDados(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        ReservaDTO reservaDTO = sessao.getReservaDTO();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_HOSPEDES);

        String mensagem = "Escolha o hóspede para alterar os dados.";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaHospedesKeyboard(reservaDTO.getHospedes());
        SendMessage sendMessage = new SendMessage(chatId, mensagem).setReplyMarkup(inlineKeyboardMarkup);

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
