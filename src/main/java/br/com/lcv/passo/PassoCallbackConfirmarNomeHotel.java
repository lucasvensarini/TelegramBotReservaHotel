package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 27/08/19.
 */
@Component
public class PassoCallbackConfirmarNomeHotel implements PassoCallback {

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_NOME_HOTEL);

        Mensagem mensagem = new Mensagem(new SendMessage(chatId, "Qual o nome do hotel?"));

        mensagens.add(mensagem);

        return mensagens;
    }

}
