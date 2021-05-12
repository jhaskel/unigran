package com.merenda.merenda.api.compras;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComprasRepository extends JpaRepository<Compras, Long> {

    @Query(value = "SELECT *  FROM itens WHERE  af = 0;", nativeQuery = true)
    List<Compras> findAll();

    @Query(value = "SELECT * FROM itens WHERE pedido = :pedido order by id desc ", nativeQuery = true)
    List<Compras> findByPedido(String pedido);


}
