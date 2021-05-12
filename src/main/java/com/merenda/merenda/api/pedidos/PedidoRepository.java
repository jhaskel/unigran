package com.merenda.merenda.api.pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT ped.*,esc.alias as nomedaescola FROM pedido ped\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ped.escola\n" +
            " ORDER BY ped.id desc", nativeQuery = true)
    List<Pedido> findAll();

    @Query(value = "SELECT *  FROM pedido where code = :code order by id desc;", nativeQuery = true)
    List<Pedido> findByCode(String code);

    @Query(value = "SELECT ped.*,esc.alias as nomedaescola FROM pedido ped\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ped.escola\n" +
            " WHERE ped.escola = :escola\n" +
            " ORDER BY ped.id desc", nativeQuery = true)
    List<Pedido> findByEscola(Long escola);

    @Query(value = "SELECT COUNT(id) AS totalCart FROM pedido", nativeQuery = true)
    long findCart();

    @Query(value = "SELECT id FROM pedido order by id desc limit 1", nativeQuery = true)
    long findUltimoId();


    @Query(value = "SELECT count(id) as temCart FROM pedido where escola = :escola  ", nativeQuery = true)
    long findTemCart(Long escola);


    @Query(value = "SELECT id FROM pedido where escola = :escola limit 1", nativeQuery = true)
    long findTemCart1(Long escola);

    @Query(value = "SELECT * FROM pedido WHERE id = :id ;", nativeQuery = true)
    List<Pedido> findId(Long id);

}
