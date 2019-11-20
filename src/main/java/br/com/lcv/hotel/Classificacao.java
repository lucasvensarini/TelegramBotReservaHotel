package br.com.lcv.hotel;

/**
 * Created by lucas on 04/07/19.
 */
public enum Classificacao {

    CINCO_ESTRELAS("5 estrelas", 5), QUATRO_ESTRELAS("4 estrelas", 4), TRES_ESTRELAS("3 estrelas", 3), DUAS_ESTRELAS(
            "2 estrelas", 2), UMA_ESTRELA("1 estrela", 1), UNDEF("Sem classificação", 0);

    private String texto;
    private int valor;

    Classificacao(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
    }

    public static Classificacao getClassificacaoByValor(int valor) {
        for (Classificacao classificacao : Classificacao.values()) {
            if (classificacao.getValor() == valor) {
                return classificacao;
            }
        }
        return UNDEF;
    }

    public String getTexto() {
        return texto;
    }

    public int getValor() {
        return valor;
    }

}
