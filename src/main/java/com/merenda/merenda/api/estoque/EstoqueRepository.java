package com.merenda.merenda.api.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {


    @Query(value = "SELECT pro.*,SUM(pro.quantidade-ite.quantidade) AS estoque FROM produto pro\n" +
            "left JOIN itens ite ON ite.produto = pro.id\n" +
            "GROUP BY pro.id\n" +
            "ORDER BY pro.isativo DESC,pro.categoria,pro.alias ", nativeQuery = true)
    List<Estoque> findAll();


    @Query(value = "SELECT * FROM produto p INNER JOIN categoria cat ON cat.id = p.categoria \n" +
            "WHERE p.is = TRUE AND cat.isativo = true and p.id NOT IN (SELECT produto FROM itens ite \n" +
            "INNER JOIN pedido ped ON ped.code = ite.pedido WHERE ite.escola = :escola AND ped.iscart = TRUE) ORDER BY p.isativo,p.categoria,p.agrofamiliar,p.alias;  ", nativeQuery = true)
    List<Estoque> findByEcola(Long escola);


    @Query(value = "SELECT * FROM produto p\n" +
            "WHERE p.isativo = true and p.id NOT IN (SELECT produto FROM itens ) ORDER BY p.categoria,p.nome", nativeQuery = true)
    List<Estoque> findMenos();



    @Query(value = "select * from produto  where id = :id", nativeQuery = true)
    List<Estoque> findId(Long id);
}
