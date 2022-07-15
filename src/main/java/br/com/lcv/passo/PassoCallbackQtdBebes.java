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
public class PassoCallbackQtdBebes implements PassoCallback {

    private static final String MENSAGEM_EXEMPLO_DATA = "Digite a data no formato dd/mm/aaaa. Ex: 10/09/2019";

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        int qtdHospedesINF = Integer.parseInt(valorCallback);
        sessao.getReservaDTO().defineQtdHospedesINF(qtdHospedesINF);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_ENTRADA);

        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text("Qual a data de entrada?" + "\n\n" + MENSAGEM_EXEMPLO_DATA).build();

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
