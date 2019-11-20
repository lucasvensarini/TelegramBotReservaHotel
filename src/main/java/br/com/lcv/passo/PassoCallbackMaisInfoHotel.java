package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/08/19.
 */
@Component
public class PassoCallbackMaisInfoHotel implements PassoCallback {

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        SendMessage sendMessage = new SendMessage(chatId, sessao.getHotelSelecionado().listaInformacoesAdicionais());

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
