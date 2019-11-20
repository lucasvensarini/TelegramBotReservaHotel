package br.com.lcv.reserva;

import br.com.lcv.bot.DataUtil;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.Quarto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by lucas on 24/07/19.
 */
@Entity
public class Reserva {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String loc;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;

    @Column(nullable = false)
    private LocalDateTime dataSaida;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "quarto_id", referencedColumnName = "id", nullable = false)
    private Quarto quarto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsavel_id", referencedColumnName = "id", nullable = false)
    private Hospede hospedeResponsavel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservaId")
    private List<Hospede> hospedes;

    @Column(nullable = false)
    private BigDecimal valor;

    @JoinColumn(referencedColumnName = "telegramId", nullable = false)
    private int usuarioTelegramId;

    Reserva(String loc, LocalDateTime dataCriacao, LocalDateTime dataEntrada, LocalDateTime dataSaida, Hotel hotel, Quarto quarto,
            Hospede hospedeResponsavel, List<Hospede> hospedes, BigDecimal valor, int usuarioTelegramId) {
        this.loc = loc;
        this.dataCriacao = dataCriacao;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.hotel = hotel;
        this.quarto = quarto;
        this.hospedeResponsavel = hospedeResponsavel;
        this.hospedes = hospedes;
        this.valor = valor;
        this.usuarioTelegramId = usuarioTelegramId;
    }

    Long getId() {
        return id;
    }

    private String getLoc() {
        return loc;
    }

    private LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    private LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    private LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String listaDadosReserva() {
        StringBuilder
                sb =
                new StringBuilder("<b>Loc: </b>"
                        + getLoc()
                        + "\n"
                        + "<b>Data de criação: </b>"
                        + DataUtil.formataDataHora(getDataCriacao())
                        + "\n"
                        + "<b>Data de entrada: </b>"
                        + DataUtil.formataDataHora(getDataEntrada())
                        + "\n"
                        + "<b>Data de saída: </b>"
                        + DataUtil.formataDataHora(getDataSaida())
                        + "\n\n"
                        + "<b>Hotel</b>"
                        + "\n"
                        + hotel.listaDadosHotel()
                        + "\n\n"
                        + "<b>Quarto</b>"
                        + "\n"
                        + quarto.listaDadosQuarto()
                        + "\n\n"
                        + "<b>Responsável</b>"
                        + "\n"
                        + hospedeResponsavel.listaDadosHospede()
                        + "\n\n"
                        + "<b>Hóspedes</b>"
                        + "\n");

        for (Hospede hospede : hospedes) {
            sb.append(hospede.listaDadosHospede()).append("\n\n");
        }

        sb.append("<b>Valor</b>" + "\n" + "R$").append(valor);

        return sb.toString();
    }

}
