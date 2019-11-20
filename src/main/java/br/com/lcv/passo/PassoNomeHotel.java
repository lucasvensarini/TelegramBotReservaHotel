package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class PassoNomeHotel implements Passo {

    private MensagemUtil mensagemUtil;

    @Autowired
    public PassoNomeHotel(MensagemUtil mensagemUtil) {
        this.mensagemUtil = mensagemUtil;
    }

    @Override
    public SendMessage executa(Long chatId, String texto, Sessao sessao) {
        sessao.getReservaDTO().getDadosBusca().defineNomeHotel(texto);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_CIDADE);

        return mensagemUtil.exibePreenchimentoCidade(chatId);
    }

}
