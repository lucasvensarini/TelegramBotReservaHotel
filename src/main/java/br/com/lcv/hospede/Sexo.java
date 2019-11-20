package br.com.lcv.hospede;

/**
 * Created by lucas on 25/07/19.
 */
public enum Sexo {

    UNDEF("Undef"), MASCULINO("Masculino"), FEMININO("Feminino");

    private String valor;

    Sexo(String valor) {
        this.valor = valor;
    }

    public static Sexo getSexoByValor(String valor) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.getValor().equals(valor)) {
                return sexo;
            }
        }
        return UNDEF;
    }

    public String getValor() {
        return valor;
    }

}
