package com.merenda.merenda.api.categorias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    @Query(value = "sELECT * FROM categoria  ;", nativeQuery = true)
    List<Categoria> findAll();

    @Query(value = "sELECT * FROM categoria  WHERE isativo = TRUE  ;", nativeQuery = true)
    List<Categoria> findAtivo();


}
