package br.com.lcv.passo;

import br.com.lcv.hospede.Hospede;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created by lucas on 30/08/19.
 */
@Component
public class PassoDataNascimento implements Passo {

    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoDataNascimento(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public SendMessage executa(Long chatId, String texto, Sessao sessao) {
        Hospede hospede = sessao.getHospedeCorrente();
        hospede.defineDataNascimento(texto);

        return mensagemUtil.exibeResumoInformacoesOuContnuaPreenchendoDados(chatId, sessao);
    }

}
