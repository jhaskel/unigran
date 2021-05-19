package com.merenda.merenda.api.cardapio.cardapioAdd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardapioAddRepository extends JpaRepository<CardapioAdd, Long> {

    @Query(value = "SELECT *  FROM cardapio order by isativo DESC,semana", nativeQuery = true)
    List<CardapioAdd> findAll();

    @Query(value = "SELECT *  FROM cardapio WHERE id = :id ", nativeQuery = true)
    List<CardapioAdd> findId(Long id);

}
