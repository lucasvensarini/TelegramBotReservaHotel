package br.com.lcv.passo;

/**
 * Created by lucas on 02/09/19.
 */
class PassoInvalidoException extends RuntimeException {

    private static String MENSAGEM = "Nesse momento você deve %s";

    PassoInvalidoException(String instrucao) {
        super(String.format(MENSAGEM, instrucao));
    }

}
