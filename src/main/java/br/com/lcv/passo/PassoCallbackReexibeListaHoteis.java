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
public class PassoCallbackReexibeListaHoteis implements PassoCallback {

    private final KeyboardService keyboardService;

    @Autowired
    public PassoCallbackReexibeListaHoteis(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        List<Hotel> hoteisListados = sessao.getHoteisListados();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.ESCOLHA_HOTEL);

        String texto = "Encontrei esses hotéis. Selecione um para ver mais detalhes e os quartos disponíveis.";
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaListaHoteisKeyboard(hoteisListados);
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(texto).replyMarkup(inlineKeyboardMarkup).build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
