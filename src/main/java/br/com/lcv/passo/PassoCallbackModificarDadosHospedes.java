package br.com.lcv.passo;

import br.com.lcv.hospede.Hospede;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 30/08/19.
 */
@Component
public class PassoCallbackModificarDadosHospedes implements PassoCallback {

    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoCallbackModificarDadosHospedes(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao) {
        List<Mensagem> mensagens = new ArrayList<>();

        SendMessage sendMessage = null;
        if (valorCallback.equalsIgnoreCase(Hospede.NOME)) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_NOME);
            sendMessage = mensagemUtil.exibePreenchimentoNome(chatId);
        } else if (valorCallback.equalsIgnoreCase(Hospede.CPF)) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_CPF);
            sendMessage = mensagemUtil.exibePreenchimentoCPF(chatId);
        } else if (valorCallback.equalsIgnoreCase(Hospede.SEXO)) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_SEXO);
            sendMessage = mensagemUtil.exibePreenchimentoSexo(chatId);
        } else if (valorCallback.equalsIgnoreCase(Hospede.DATA_NASCIMENTO)) {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_NASCIMENTO);
            sendMessage = mensagemUtil.exibePreenchimentoDataNascimento(chatId);
        }

        mensagens.add(new Mensagem(sendMessage));

        return mensagens;
    }

}
