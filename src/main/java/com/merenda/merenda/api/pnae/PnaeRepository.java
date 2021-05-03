package com.merenda.merenda.api.pnae;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PnaeRepository extends JpaRepository<Pnae, Long> {

    @Query(value = "SELECT *  FROM pnae order by id DESC", nativeQuery = true)
    List<Pnae> findAll();


    @Query(value = "SELECT sum(valor) as soma  FROM pnae \n" +
            "WHERE    ano = :ano ", nativeQuery = true)
    double findSoma(Long ano);

}
