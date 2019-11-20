package br.com.lcv.hospede;

/**
 * Created by lucas on 24/07/19.
 */
public enum FaixaEtaria {

    ADT("Adulto(s)"), CHD("Criança(s)"), INF("Bebê(s)");

    private String valor;

    FaixaEtaria(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
