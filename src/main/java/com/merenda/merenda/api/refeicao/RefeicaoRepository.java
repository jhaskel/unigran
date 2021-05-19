package com.merenda.merenda.api.refeicao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {

    @Query(value = "SELECT *  FROM refeicao order by isativo DESC,nome", nativeQuery = true)
    List<Refeicao> findAll();

    @Query(value = "SELECT *  FROM refeicao WHERE id = :id ", nativeQuery = true)
    List<Refeicao> findId(Long id);

}
