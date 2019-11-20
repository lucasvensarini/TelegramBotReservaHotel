package br.com.lcv.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lucas on 07/10/19.
 */
@Repository
interface HotelRepository extends JpaRepository<Hotel, Long>, HotelRepositoryCustom {

}
