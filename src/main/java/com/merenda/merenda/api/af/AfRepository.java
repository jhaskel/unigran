package com.merenda.merenda.api.af;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AfRepository extends JpaRepository<Af, Long> {
    @Query(value = "SELECT * FROM af WHERE ativo = TRUE ORDER BY id desc", nativeQuery = true)
    List<Af> findAll();

    @Query(value = "SELECT * FROM af\n" +
            " WHERE fornecedor = :fornecedor AND ativo = TRUE AND isautorizado = true order by id desc", nativeQuery = true)
    List<Af> findByFornecedor(Long fornecedor);

    @Query(value = "SELECT COUNT(id) as total FROM af WHERE ativo = TRUE AND isautorizado = false", nativeQuery = true)
    long findAf();


}
