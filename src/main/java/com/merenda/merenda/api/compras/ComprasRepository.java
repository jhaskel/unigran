package com.merenda.merenda.api.compras;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComprasRepository extends JpaRepository<Compras, Long> {

    @Query(value = "SELECT *  FROM itens WHERE  af = 0;", nativeQuery = true)
    List<Compras> findAll();

    @Query(value = "SELECT * FROM itens WHERE pedido = :pedido order by id desc ", nativeQuery = true)
    List<Compras> findByPedido(String pedido);

    @Query(value = "SELECT ite.* FROM itens ite\n" +
            "            WHERE ite.af = :af\n" +
            "             AND ite.isativo = true  \n" +
            "             GROUP BY ite.id\n" +
            "             ORDER BY ite.fornecedor, ite.alias ", nativeQuery = true)
    List<Compras> findByAf(Long af);


}
