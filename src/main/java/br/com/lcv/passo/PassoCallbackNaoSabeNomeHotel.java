package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 27/08/19.
 */
@Component
public class PassoCallbackNaoSabeNomeHotel implements PassoCallback {

    private final MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackNaoSabeNomeHotel(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_CIDADE);

        Mensagem mensagem = new Mensagem(mensagemUtil.exibePreenchimentoCidade(chatId));

        mensagens.add(mensagem);

        return mensagens;
    }

}
