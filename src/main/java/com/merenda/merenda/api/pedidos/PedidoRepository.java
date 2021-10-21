package com.merenda.merenda.api.pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT ped.*,uni.alias as nomedaunidade,seto.id AS idsetor FROM pedido ped\n" +
            "INNER JOIN unidades uni ON uni.id = ped.unidade\n" +
            "INNER JOIN setor seto ON seto.id = uni.setor\n" +
            "ORDER BY ped.isaf ,ped.id desc", nativeQuery = true)
    List<Pedido> findAll();



    @Query(value = "SELECT ped.*,esc.alias as nomedaunidade,seto.id AS idsetor FROM pedido ped\n" +
            "INNER JOIN unidades esc ON esc.id = ped.unidade\n" +
            "INNER JOIN setor seto ON seto.id = uni.setor\n" +
            " WHERE ped.unidade = :unidade\n" +
            " ORDER BY ped.id desc", nativeQuery = true)
    List<Pedido> findByUnidade(Long unidade);


    @Query(value = "SELECT COUNT(id) AS totalCart FROM pedido where isaf = false", nativeQuery = true)
    long findPedidoSemAf();

    @Query(value = "SELECT * FROM pedido WHERE id = :id ;", nativeQuery = true)
    List<Pedido> findId(Long id);

}
