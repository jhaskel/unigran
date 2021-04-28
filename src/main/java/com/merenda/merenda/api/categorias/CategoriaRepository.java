package com.merenda.merenda.api.categorias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    @Query(value = "sELECT cat.*,user.nome as teste FROM categoria cat inner join user on user.id = cat.id WHERE cat.id = 2 and cat.isativo = TRUE  ;", nativeQuery = true)
    List<Categoria> findAll();

    @Query(value = "sELECT * FROM categoria  WHERE isativo = TRUE  ;", nativeQuery = true)
    List<Categoria> findAtivo();


}
