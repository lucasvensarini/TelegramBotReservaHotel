package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 30/08/19.
 */
@Component
public class PassoCallbackSairModificacaoDadosHospedes implements PassoCallback {

    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackSairModificacaoDadosHospedes(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        mensagens.add(new Mensagem(mensagemUtil.exibeResumoInformacoesOuContnuaPreenchendoDados(chatId, sessao)));

        return mensagens;
    }

}
