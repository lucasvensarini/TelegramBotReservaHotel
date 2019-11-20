package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Passo {

    SendMessage executa(Long chatId, String texto, Sessao sessao);

}
