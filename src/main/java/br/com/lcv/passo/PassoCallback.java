package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;

import java.util.List;

/**
 * Created by lucas on 27/08/19.
 */
public interface PassoCallback {

    List<Mensagem> executa(Integer usuarioTelegramId, Long chatId, String valorCallback, Sessao sessao);

}
