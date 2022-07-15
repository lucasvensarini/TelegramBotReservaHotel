package br.com.lcv.passo;

import br.com.lcv.hospede.Hospede;
import br.com.lcv.hospede.Sexo;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/08/19.
 */
@Component
public class PassoCallbackSexoHospede implements PassoCallback {

    private static final String MENSAGEM_EXEMPLO_DATA = "Digite a data no formato dd/mm/aaaa. Ex: 10/09/2019";

    private final MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackSexoHospede(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(long usuarioTelegramId, String chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        Hospede hospedeCorrente = sessao.getHospedeCorrente();

        Sexo sexo = Sexo.getSexoByValor(valorCallback);
        hospedeCorrente.setSexo(sexo);

        SendMessage sendMessage;
        boolean todosDadosPreenchidos = hospedeCorrente.isTodosDadosPreenchidos();
        if (!todosDadosPreenchidos) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_NASCIMENTO);
            sendMessage = new SendMessage(chatId, "Data de nascimento?" + "\n\n" + MENSAGEM_EXEMPLO_DATA);
        } else {
            sendMessage = mensagemUtil.exibeResumoInformacoesOuContnuaPreenchendoDados(chatId, sessao);
        }

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
