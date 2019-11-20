package br.com.lcv.hotel;

import br.com.lcv.reserva.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by lucas on 04/07/19.
 */
@Service
public class HotelService {

    private HotelRepository hotelRepository;
    private QuartoRepository quartoRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, QuartoRepository quartoRepository) {
        this.hotelRepository = hotelRepository;
        this.quartoRepository = quartoRepository;
    }

    public List<Hotel> buscaHoteis(ReservaDTO reservaDTO) {
        String nomeHotelBuscado = reservaDTO.getDadosBusca().getNomeHotel();
        String cidadeBuscada = reservaDTO.getDadosBusca().getCidade();
        int qtdHospedes = reservaDTO.getHospedes().size();
        Classificacao classificacaoBuscada = reservaDTO.getDadosBusca().getClassificacao();

        Optional<List<Hotel>> optional = hotelRepository.findHoteis(cidadeBuscada, qtdHospedes, nomeHotelBuscado, classificacaoBuscada);
        if (optional.isPresent() && !optional.get().isEmpty()) {
            return optional.get();
        }

        throw new HoteisNaoEncontradosException();
    }

    public List<Quarto> buscaQuartosPorCapacidade(Hotel hotel, int qtdHospedes) {
        Optional<List<Quarto>> optional = quartoRepository.findByHotelIdAndCapacidadeGreaterThan(hotel.getId(), qtdHospedes);
        if (optional.isPresent() && !optional.get().isEmpty()) {
            return optional.get();
        }

        throw new QuartosNaoEncontradosException();
    }

    public Hotel filtraHotelPorId(List<Hotel> hoteis, Long id) {
        return hoteis.stream().filter(hotel -> Objects.equals(hotel.getId(), id)).findFirst().get();
    }

    public Quarto filtraQuartoPorId(Hotel hotel, Long quartoId) {
        return hotel.getQuartos().stream().filter(quarto -> Objects.equals(quarto.getId(), quartoId)).findFirst().get();
    }

}
