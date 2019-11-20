package br.com.lcv.bot;

/**
 * Created by lucas on 09/08/19.
 */
public class DataIncompativelComFaixaEtariaException extends RuntimeException {

    private static final String MENSAGEM_DATA_INCOMPATIVEL_COM_FAIXA_ETARIA =
            "Data incompatível com a faixa etária do hóspede. Pode digitar novamente, por favor?";

    public DataIncompativelComFaixaEtariaException() {
        super(MENSAGEM_DATA_INCOMPATIVEL_COM_FAIXA_ETARIA);
    }

}
