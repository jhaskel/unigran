package com.merenda.merenda.api.licitacao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {

    @Query(value = "SELECT *  FROM licitacao WHERE processo = :processo order by id desc;", nativeQuery = true)
    List<Licitacao> findProcesso(String processo);


    
}
