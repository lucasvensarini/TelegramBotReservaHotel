package br.com.lcv.sessao;

/**
 * Created by lucas on 09/08/19.
 */
class SessaoInvalidaException extends RuntimeException {

    private static final String MENSAGEM_DEVE_INICAR_PROCESSO = "Sua sessão expirou. Digite /reservarhotel para inicar o processo.";

    SessaoInvalidaException() {
        super(MENSAGEM_DEVE_INICAR_PROCESSO);
    }

}
