package com.merenda.merenda.api.cardapio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

    @Query(value = "SELECT * from cardapio order by escola", nativeQuery = true)
    List<Cardapio> findAll();

    @Query(value = "SELECT *  FROM cardapio WHERE id = :id ", nativeQuery = true)
    List<Cardapio> findId(Long id);

}
