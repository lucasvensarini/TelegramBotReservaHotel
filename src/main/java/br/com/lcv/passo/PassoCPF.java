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
public class PassoCPF implements Passo {

    private final MensagemUtil mensagemUtil;

    @Autowired
    public PassoCPF(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public SendMessage executa(String chatId, String texto, Sessao sessao) {
        Hospede hospedeCorrente = sessao.getHospedeCorrente();
        hospedeCorrente.defineCPF(texto);

        boolean todosDadosPreenchidos = hospedeCorrente.isTodosDadosPreenchidos();
        if (!todosDadosPreenchidos) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_SEXO);
            return mensagemUtil.exibePreenchimentoSexo(chatId);
        } else {
            return mensagemUtil.exibeResumoInformacoesOuContnuaPreenchendoDados(chatId, sessao);
        }
    }

}
