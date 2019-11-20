package br.com.lcv.hotel;

import javax.persistence.*;

/**
 * Created by lucas on 04/07/19.
 */
@Entity
public class Endereco {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String pais;

    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id", nullable = false)
    private Cidade cidade;

    public Endereco() {

    }

    private String getLogradouro() {
        return logradouro;
    }

    private int getNumero() {
        return numero;
    }

    private String getBairro() {
        return bairro;
    }

    private Cidade getCidade() {
        return cidade;
    }

    private String getEstado() {
        return estado;
    }

    private String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getLogradouro()).append(", ").append(getNumero());

        if (getBairro() != null && !getBairro().isEmpty()) {
            sb.append(", ").append(getBairro());
        }

        sb.append(" - ").append(getCidade().getNome());

        if (getEstado() != null && !getEstado().isEmpty()) {
            sb.append("/").append(getEstado());
        }

        if (getPais() != null && !getPais().isEmpty()) {
            sb.append(" - ").append(getPais());
        }

        return sb.toString();
    }

}
