package com.merenda.merenda.api.setor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Long> {


    @Query(value = "sELECT * FROM setor  order by isativo desc ;", nativeQuery = true)
    List<Setor> findAll();

    @Query(value = "SELECT *  FROM setor WHERE id = :id ", nativeQuery = true)
    List<Setor> findId(Long id);



}
