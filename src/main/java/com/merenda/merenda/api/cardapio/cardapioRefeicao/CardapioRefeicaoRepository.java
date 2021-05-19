package com.merenda.merenda.api.cardapio.cardapioRefeicao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardapioRefeicaoRepository extends JpaRepository<CardapioRefeicao, Long> {

    @Query(value = "SELECT *  FROM cardapio_refeicao ", nativeQuery = true)
    List<CardapioRefeicao> findAll();

    @Query(value = "SELECT *  FROM cardapio_refeicao WHERE id = :id ", nativeQuery = true)
    List<CardapioRefeicao> findId(Long id);

}
