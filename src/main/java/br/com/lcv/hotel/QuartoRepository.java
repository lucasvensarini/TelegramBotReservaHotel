package br.com.lcv.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    Optional<List<Quarto>> findByHotelIdAndCapacidadeGreaterThan(Long hotelId, int qtdHospedes);

}
