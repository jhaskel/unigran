package com.merenda.merenda.api.produtos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    @Query(value = "SELECT * from produto ORDER BY subcategoria,alias", nativeQuery = true)
    List<Produto> findAll();


    @Query(value = "select * from produto  where id = :id", nativeQuery = true)
    List<Produto> findId(Long id);
}
