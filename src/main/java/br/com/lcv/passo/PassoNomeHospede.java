package br.com.lcv.passo;

import br.com.lcv.hospede.FaixaEtaria;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created by lucas on 30/08/19.
 */
@Component
public class PassoNomeHospede implements Passo {

    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoNomeHospede(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public SendMessage executa(Long chatId, String texto, Sessao sessao) {
        Hospede hospedeCorrente = sessao.getHospedeCorrente();
        hospedeCorrente.defineNome(texto);

        boolean todosDadosPreenchidos = hospedeCorrente.isTodosDadosPreenchidos();
        if (!todosDadosPreenchidos) {
            if (hospedeCorrente.getFaixaEtaria().equals(FaixaEtaria.INF)) {
                sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_SEXO);
                return mensagemUtil.exibePreenchimentoSexo(chatId);
            } else {
                sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_CPF);
                return mensagemUtil.exibePreenchimentoCPF(chatId);
            }
        } else {
            return mensagemUtil.exibeResumoInformacoesOuContnuaPreenchendoDados(chatId, sessao);
        }
    }

}
