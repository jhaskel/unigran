package com.merenda.merenda.api.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query(value = "SELECT *  FROM fornecedor order by isativo DESC,nome", nativeQuery = true)
    List<Fornecedor> findAll();

    @Query(value = "SELECT *  FROM fornecedor WHERE id = :id ", nativeQuery = true)
    List<Fornecedor> findId(Long id);

}
