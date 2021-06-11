package com.merenda.merenda.api.itens;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItensRepository extends JpaRepository<Itens, Long> {

    @Query(value = "SELECT *,sum(total) AS tot,status AS nomec  FROM itens where af>0", nativeQuery = true)
    List<Itens> findAll();


    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.pedido AS nomec FROM itens ite\n" +
            " WHERE ite.pedido = :pedido\n" +
            " AND ite.isativo = true  \n" +
            " GROUP BY ite.id\n" +
            " ORDER BY ite.fornecedor, ite.alias  ", nativeQuery = true)
    List<Itens> findByPedido(Long pedido);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,esc.alias AS nomec \n" +
            "FROM itens ite\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE ite.af = :af AND ite.isativo = true \n" +
            "GROUP BY ite.id\n" +
            "ORDER BY nomec, ite.alias ", nativeQuery = true)
    List<Itens> findByAf(Long af);


    //verificado
    @Query(value = "SELECT *,sum(quantidade) AS tot,pedido AS nomec FROM itens  \n" +
            " WHERE ano = :ano \n" +
            " GROUP BY produto \n" +
            " ORDER BY tot desc", nativeQuery = true)
    List<Itens> findMaisPedidos(Long ano);


    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.pedido as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Itens> findTotalMes(Long ano);



   //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.pedido as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano and ite.escola = :escola\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Itens> findTotalMesEscola(Long escola, Long ano);


    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, cat.nome as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano and ite.escola = :escola\n" +
            "GROUP BY ite.categoria ", nativeQuery = true)
    List<Itens> findTotalCategoriaEscola(Long escola, Long ano);

    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, cat.nome as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano\n" +
            "GROUP BY ite.categoria ", nativeQuery = true)
    List<Itens> findTotalCategoria(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, esc.alias as nomec FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            INNER JOIN unidade_escolar esc ON esc.id = ite.escola \n" +
            "            WHERE af.isativo = TRUE AND ite.ano = :ano \n" +
            "            GROUP BY ite.escola ORDER BY ite.escola", nativeQuery = true)
    List<Itens> findTotalEscolas(Long ano);

    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total/esc.alunos) AS tot, esc.alias as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano AND esc.alunos > 0\n" +
            "GROUP BY ite.escola ORDER BY ite.escola ", nativeQuery = true)
    List<Itens> findMediaAlunos(Long ano);

    //verificado
    @Query(value = "SELECT ite.*,SUM(ite.quantidade) AS tot ,esc.alias AS nomec\n" +
            "from itens ite \n" +
            "INNER join produto pro ON pro.id = ite.produto\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE ite.produto = :produto and ite.af >0 \n" +
            "GROUP BY ite.escola\n" +
            "ORDER BY tot desc", nativeQuery = true)
    List<Itens> findProduto(Long produto);

    //verificado
    @Query(value = "SELECT ite.*,SUM(ite.quantidade) AS tot ,niv.nome AS nomec\n" +
            "from itens ite \n" +
            "INNER join produto pro ON pro.id = ite.produto\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "INNER JOIN nivel_escolar niv ON niv.id = esc.nivelescolar\n" +
            "WHERE ite.af > 0 AND produto = :produto\n" +
            "GROUP BY niv.id\n" +
            "ORDER BY tot desc", nativeQuery = true)
    List<Itens> findProduto2(Long produto);

    //verfiicado
    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "WHERE  ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotal(Long ano);


    //verificado
    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "WHERE ite.ano = :ano AND ite.af > 0 AND ite.escola = :escola ", nativeQuery = true)
    double findTotalEscola(Long escola, Long ano);


   //verificado
    @Query(value = "SELECT sum(ite.total) as tot  \n" +
            "FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "INNER JOIN produto pro ON pro.id = ite.produto\n" +
            "WHERE af.isativo= true   AND ite.ano = :ano AND ite.af > 0 \n" +
            "AND cat.isalimento = TRUE AND pro.agrofamiliar = FALSE ", nativeQuery = true)
    double findTradicional(Long ano);
    //verificado
    @Query(value = "SELECT sum(ite.total) as tot  \n" +
            "FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "INNER JOIN produto pro ON pro.id = ite.produto\n" +
            "WHERE af.isativo= true   AND ite.ano = :ano AND ite.af > 0 \n" +
            "AND cat.isalimento = TRUE AND pro.agrofamiliar = true ", nativeQuery = true)
    double findFamiliar(Long ano);

    //verificado
    @Query(value = "SELECT sum(ite.total) as tot  \n" +
            "FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "INNER JOIN produto pro ON pro.id = ite.produto\n" +
            "WHERE af.isativo= true   AND ite.ano = :ano AND ite.af > 0 \n" +
            "AND cat.isalimento = TRUE AND pro.agrofamiliar = true and ite.escola =:escola ", nativeQuery = true)
    double findFamiliarEscola(Long escola,Long ano);


    //verificado
    @Query(value = "SELECT sum(ite.total) as tot  \n" +
            "            FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "            INNER JOIN produto pro ON pro.id = ite.produto\n" +
            "            WHERE af.isativo= true   AND ite.ano = :ano AND ite.af > 0 \n" +
            "            AND cat.isalimento = TRUE AND pro.agrofamiliar = FALSE and ite.escola = :escola", nativeQuery = true)
    double findTradicionalEscola(Long escola, Long ano);


}

