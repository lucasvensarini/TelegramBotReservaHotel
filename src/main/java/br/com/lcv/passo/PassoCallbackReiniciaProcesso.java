package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import br.com.lcv.sessao.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 02/09/19.
 */
@Component
public class PassoCallbackReiniciaProcesso implements PassoCallback {

    private SessaoService sessaoService;
    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackReiniciaProcesso(SessaoService sessaoService, MensagemUtil mensagemUtil) {
        this.sessaoService = sessaoService;
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        sessaoService.encerraSessaoUsuario(usuarioTelegramId);

        mensagens.add(new Mensagem(mensagemUtil.enviaMensagemInicial(chatId)));

        return mensagens;
    }

}
