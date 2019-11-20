package br.com.lcv.reserva;

import br.com.lcv.bot.DataUtil;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.hospede.HospedeService;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.Quarto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by lucas on 24/07/19.
 */
@Service
public class ReservaService {

    private static final double MARKUP_DEFAULT = 0.1;

    private HospedeService hospedeService;
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(HospedeService hospedeService, ReservaRepository reservaRepository) {
        this.hospedeService = hospedeService;
        this.reservaRepository = reservaRepository;
    }

    public ReservaDTO iniciaReservaHotel() {
        return new ReservaDTO();
    }

    public BigDecimal calculaValorReserva(Quarto quarto) {
        BigDecimal markup = quarto.getValorDiaria().multiply(BigDecimal.valueOf(MARKUP_DEFAULT));
        return quarto.getValorDiaria().add(markup).setScale(2, RoundingMode.FLOOR);
    }

    public String exibeResumoInformacoes(Hotel hotelSelecionado, Quarto quartoSelecionado, ReservaDTO reservaDTO) {
        return "<b>Hotel</b>"
                + "\n"
                + hotelSelecionado.listaDadosHotel()
                + "\n\n"
                + "<b>Quarto</b>"
                + "\n"
                + quartoSelecionado.listaDadosQuarto()
                + "\n\n"
                + "<b>Hóspedes</b>"
                + "\n"
                + hospedeService.dadosHospoedesToString(reservaDTO.getHospedes())
                + "\n\n"
                + "<b>Valor</b>"
                + "\n"
                + "R$"
                + calculaValorReserva(quartoSelecionado);
    }

    public Reserva reservaHotel(Hotel hotelSelecionado, Quarto quartoSelecionado, Hospede hospedeResponsavel, ReservaDTO reservaDTO,
                                int usuarioTelegramId) {

        String loc = "abc123";
        LocalDateTime criacao = DataUtil.defineDataHoraAtual();

        LocalDate dataEntrada = reservaDTO.getDadosBusca().getDataEntrada();
        LocalTime horarioCheckin = hotelSelecionado.getHorarioCheckin();
        LocalDateTime entrada = LocalDateTime.of(dataEntrada, horarioCheckin);

        LocalDate dataSaida = reservaDTO.getDadosBusca().getDataSaida();
        LocalTime horarioCheckout = hotelSelecionado.getHorarioCheckout();
        LocalDateTime saida = LocalDateTime.of(dataSaida, horarioCheckout);

        List<Hospede> hospedes = reservaDTO.getHospedes();
        BigDecimal valor = calculaValorReserva(quartoSelecionado);

        Reserva reserva = new Reserva(loc, criacao, entrada, saida, hotelSelecionado, quartoSelecionado, hospedeResponsavel, hospedes, valor,
                        usuarioTelegramId);

        Reserva reservaSalva = reservaRepository.save(reserva);
        hospedeService.salvaHospedes(reservaSalva.getId(), hospedes);

        return reservaSalva;
    }

}
