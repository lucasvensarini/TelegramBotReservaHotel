package br.com.lcv.hotel;

import br.com.lcv.bot.DataUtil;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 04/07/19.
 */
@Entity
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelId", fetch = FetchType.LAZY)
    private List<Quarto> quartos;

    @Column(nullable = false)
    private LocalTime horarioCheckin;

    @Column(nullable = false)
    private LocalTime horarioCheckout;

    @Column(nullable = false)
    private String urlThumbnail;

    @Transient
    //    @Column(nullable = false)
    private List<String> urlFotos;

    @Transient
    //    @Column(nullable = false)
    private List<String> informacoes;

    @Transient
    private boolean quartosBuscados;

    Hotel() {
        this.quartos = new ArrayList<>();
        this.urlFotos = new ArrayList<>();
        this.informacoes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    private Classificacao getClassificacao() {
        return classificacao;
    }

    private Endereco getEndereco() {
        return endereco;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(List<Quarto> quartos) {
        this.quartos = quartos;
    }

    public LocalTime getHorarioCheckin() {
        return horarioCheckin;
    }

    public LocalTime getHorarioCheckout() {
        return horarioCheckout;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
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

    public boolean isQuartosBuscados() {
        return quartosBuscados;
    }

    public void setQuartosBuscados(boolean quartosBuscados) {
        this.quartosBuscados = quartosBuscados;
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

    public String listaDadosHotel() {
        return "<b>Nome: </b>"
                + getNome()
                + "\n"
                + "<b>Classificação: </b>"
                + getClassificacao().getTexto()
                + "\n"
                + "<b>Endereço: </b>"
                + getEndereco()
                + "\n"
                + "<b>Horário de checkin: </b>"
                + DataUtil.formataHora(getHorarioCheckin())
                + "\n"
                + "<b>Horário de checkout: </b>"
                + DataUtil.formataHora(getHorarioCheckout());
    }

}
