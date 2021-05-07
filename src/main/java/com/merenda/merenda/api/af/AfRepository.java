package com.merenda.merenda.api.af;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AfRepository extends JpaRepository<Af, Long> {
    @Query(value = "SELECT af.*,SUM(ite.total) as tot,forn.nome as nomefor FROM af \n" +
            "INNER JOIN itens ite ON ite.af = af.code\n" +
            "INNER JOIN fornecedor forn ON forn.id = ite.fornecedor\n" +
            "GROUP BY af.code", nativeQuery = true)
    List<Af> findAll();

    @Query(value = "SELECT * FROM af\n" +
            " WHERE fornecedor = :fornecedor AND isativo = TRUE AND isautorizado = true order by id desc", nativeQuery = true)
    List<Af> findByFornecedor(Long fornecedor);

    @Query(value = "SELECT ite.* FROM itens ite\n" +
            "WHERE af = :af order by id desc", nativeQuery = true)
    List<Af> findByAf(Long af);

    @Query(value = "SELECT COUNT(id) as total FROM af WHERE isativo = TRUE AND isautorizado = false", nativeQuery = true)
    long findAf();


}
