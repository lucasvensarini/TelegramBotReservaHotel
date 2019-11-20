package br.com.lcv.hotel;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucas on 24/10/19.
 */
public interface HotelRepositoryCustom {

    Optional<List<Hotel>> findHoteis(String cidade, int qtdHospedes, String nome, Classificacao classificacao);

}
