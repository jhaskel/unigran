package com.merenda.merenda.api.categorias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    @Query(value = "sELECT * FROM categoria  order by isativo desc ;", nativeQuery = true)
    List<Categoria> findAll();

    @Query(value = "SELECT *  FROM categoria WHERE id = :id ", nativeQuery = true)
    List<Categoria> findId(Long id);





}
