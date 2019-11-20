package br.com.lcv.hotel;

/**
 * Created by lucas on 09/08/19.
 */
public class CidadeDesconhecidaException extends RuntimeException {

    private static final String MENSAGEM_CIDADE_DESCONHECIDA = "Não conheço essa cidade. Pode digitar novamente, por favor?";

    public CidadeDesconhecidaException() {
        super(MENSAGEM_CIDADE_DESCONHECIDA);
    }

}
