package br.com.lcv.usuario;

/**
 * Created by lucas on 09/08/19.
 */
public class UsuarioNaoAutorizadoException extends RuntimeException {

    private static final String MENSAGEM_USUARIO_NAO_AUTORIZADO = "Infelizmente, você não esta autorizado a utilizar essa função.";

    public UsuarioNaoAutorizadoException() {
        super(MENSAGEM_USUARIO_NAO_AUTORIZADO);
    }

}
