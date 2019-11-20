package br.com.lcv.hospede;

/**
 * Created by lucas on 04/09/19.
 */
class CPFInvalidoException extends RuntimeException {

    private static final String MENSAGEM_CPF_INVALIDO = "CPF inválido. Pode digitar novamente, por favor?";

    CPFInvalidoException() {
        super(MENSAGEM_CPF_INVALIDO);
    }

}
