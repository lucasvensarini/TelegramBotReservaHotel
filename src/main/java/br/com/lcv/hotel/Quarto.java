package br.com.lcv.hotel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 04/07/19.
 */
@Entity
public class Quarto {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @Column(nullable = false)
    private String urlThumbnail;

    @Transient
    //    @Column(nullable = false)
    private List<String> urlFotos;

    @Transient
    //    @Column(nullable = false)
    private List<String> informacoes;

    @Column(nullable = false)
    @JoinColumn(referencedColumnName = "id")
    private Long hotelId;

    public Quarto() {
        this.urlFotos = new ArrayList<>();
        this.informacoes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    int getCapacidade() {
        return capacidade;
    }

    void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public List<String> getUrlFotos() {
        return urlFotos;
    }

    void setUrlFotos(List<String> urlFotos) {
        this.urlFotos = urlFotos;
    }

    private List<String> getInformacoes() {
        return informacoes;
    }

    void setInformacoes(List<String> informacoes) {
        this.informacoes = informacoes;
    }

    public String listaInformacoesAdicionais() {
        StringBuilder sb = new StringBuilder();

        if (getInformacoes().isEmpty()) {
            sb.append("Sem informações adicionais");
        } else {
            for (String informacao : getInformacoes()) {
                sb.append(informacao).append("\n");
            }
        }

        return sb.toString();
    }

    public String listaDadosQuarto() {
        return "<b>Tipo do quarto: </b>" + getTipoQuarto().getValor() + "\n" + "<b>Capacidade: </b>" + getCapacidade() + " pessoas";
    }

}
