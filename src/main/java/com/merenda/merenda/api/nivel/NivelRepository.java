package com.merenda.merenda.api.nivel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NivelRepository extends JpaRepository<Nivel, Long> {

    @Query(value = "select nome from nivel where id = :id", nativeQuery = true)
    String findNome(Long id);

    @Query(value = "select * from nivel where setor = :setor", nativeQuery = true)
    List<Nivel> findSetor(Long setor);

    @Query(value = "select * from nivel where id = :id", nativeQuery = true)
    List<Nivel> findId(Long id);

}
