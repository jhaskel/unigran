package com.merenda.merenda.api.subcategoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {


    @Query(value = "sELECT * FROM subcategoria  order by isativo desc ;", nativeQuery = true)
    List<Subcategoria> findAll();

    @Query(value = "SELECT *  FROM subcategoria WHERE id = :id ", nativeQuery = true)
    List<Subcategoria> findId(Long id);



}
