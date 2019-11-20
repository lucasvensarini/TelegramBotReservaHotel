package br.com.lcv.hospede;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by lucas on 04/10/19.
 */
@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    Optional<Hospede> findHospedeByCpf(String cpf);

}
