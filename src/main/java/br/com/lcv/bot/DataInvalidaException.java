package br.com.lcv.bot;

/**
 * Created by lucas on 09/08/19.
 */
public class DataInvalidaException extends RuntimeException {

    private static final String MENSAGEM_DATA_FORMATO_INVALIDO = "Data em formato inválido. Pode digitar no formato dd/mm/aaaa, por favor?";

    public DataInvalidaException() {
        super(MENSAGEM_DATA_FORMATO_INVALIDO);
    }

}
