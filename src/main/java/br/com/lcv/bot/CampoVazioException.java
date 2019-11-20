package br.com.lcv.bot;

/**
 * Created by lucas on 09/08/19.
 */
public class CampoVazioException extends RuntimeException {

    private static final String MENSAGEM_CAMPO_VAZIO = "O %s não pode ser nulo.";

    public CampoVazioException(String campo) {
        super(String.format(MENSAGEM_CAMPO_VAZIO, campo));
    }

}
