package br.com.lcv.sessao;

import br.com.lcv.bot.DataUtil;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.hotel.Hotel;
import br.com.lcv.hotel.Quarto;
import br.com.lcv.passo.PassoCorrente;
import br.com.lcv.reserva.ReservaDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lucas on 19/07/19.
 */
public class Sessao {

    private static final long TIMEOUT_MINUTOS = 10;
    private static final String RESERVA_DTO = "reservaDTO";
    private static final String HOTEIS_LISTADOS = "hoteisListados";
    private static final String HOTEL_SELECIONADO = "hotelSelecionado";
    private static final String QUARTO_SELECIONADO = "quartoSelecionado";
    private static final String HOSPEDE_CORRENTE = "hospedeCorrente";
    private static final String PASSO_CORRENTE = "passoCorrente";
    private final boolean usuarioAutorizado;
    private final boolean iniciada;
    private final LocalDateTime dataInicio;
    private final long timeout;
    private final Map<String, Object> atributos;

    Sessao() {
        this.usuarioAutorizado = true;
        this.iniciada = true;
        this.dataInicio = DataUtil.defineDataHoraAtual();
        this.timeout = TIMEOUT_MINUTOS;
        this.atributos = new HashMap<>();
    }

    boolean isUsuarioAutorizado() {
        return usuarioAutorizado;
    }

    boolean isSessaoValida() {
        LocalDateTime dataUltimoAcesso = DataUtil.defineDataHoraAtual();
        return iniciada && Duration.between(dataUltimoAcesso, dataInicio).toMinutes() <= timeout;
    }

    public boolean isPassoCorrente(PassoCorrente passoCorrente) {
        return getPassoCorrente().equals(passoCorrente);
    }

    public ReservaDTO getReservaDTO() {
        return (ReservaDTO) atributos.get(RESERVA_DTO);
    }

    public void adicionaAtributoReservaDTO(ReservaDTO reservaDTO) {
        atributos.put(RESERVA_DTO, reservaDTO);
    }

    public List<Hotel> getHoteisListados() {
        return (List<Hotel>) atributos.get(HOTEIS_LISTADOS);
    }

    public void adicionaAtributoHoteisListados(List<Hotel> hoteis) {
        atributos.put(HOTEIS_LISTADOS, hoteis);
    }

    public Hotel getHotelSelecionado() {
        return (Hotel) atributos.get(HOTEL_SELECIONADO);
    }

    public void adicionaAtributoHotelSelecionado(Hotel hotel) {
        atributos.put(HOTEL_SELECIONADO, hotel);
    }

    public Quarto getQuartoSelecionado() {
        return (Quarto) atributos.get(QUARTO_SELECIONADO);
    }

    public void adicionaAtributoQuartoSelecionado(Quarto quarto) {
        atributos.put(QUARTO_SELECIONADO, quarto);
    }

    public Hospede getHospedeCorrente() {
        return (Hospede) atributos.get(HOSPEDE_CORRENTE);
    }

    public void adicionaAtributoHospedeCorrente(Hospede hospede) {
        atributos.put(HOSPEDE_CORRENTE, hospede);
    }

    public PassoCorrente getPassoCorrente() {
        return (PassoCorrente) atributos.get(PASSO_CORRENTE);
    }

    public void adicionaAtributoPassoCorrente(String passo) {
        atributos.put(PASSO_CORRENTE, passo);
    }

    public void adicionaAtributoPassoCorrente(PassoCorrente passoCorrente) {
        atributos.put(PASSO_CORRENTE, passoCorrente);
    }

}
