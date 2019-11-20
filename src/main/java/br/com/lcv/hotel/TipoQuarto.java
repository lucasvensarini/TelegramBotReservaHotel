package br.com.lcv.hotel;

/**
 * Created by lucas on 04/07/19.
 */
public enum TipoQuarto {

    SIMPLES("Simples"), CASAL("Casal"), LUXO("Luxo"), FAMILIA("Família");

    private String valor;

    TipoQuarto(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
