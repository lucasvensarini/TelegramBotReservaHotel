package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class PassoDataEntrada implements Passo {

    private static final String MENSAGEM_EXEMPLO_DATA = "Digite a data no formato dd/mm/aaaa. Ex: 10/09/2019";

    @Override
    public SendMessage executa(String chatId, String texto, Sessao sessao) {
        sessao.getReservaDTO().getDadosBusca().defineDataEntrada(texto);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_SAIDA);

        return new SendMessage(chatId, "Qual a data de saída?" + "\n\n" + MENSAGEM_EXEMPLO_DATA);
    }

}
