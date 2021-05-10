package com.merenda.merenda.api.itens;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItensRepository extends JpaRepository<Itens, Long> {

    @Query(value = "SELECT *  FROM itens WHERE  af = 0;", nativeQuery = true)
    List<Itens> findAll();

    @Query(value = "SELECT * FROM itens WHERE af > 0  GROUP BY af;", nativeQuery = true)
    List<Itens> findAll2();

    @Query(value = "SELECT * FROM itens GROUP BY pedido;", nativeQuery = true)
    List<Itens> findAll3();


    @Query(value = "SELECT * FROM itens WHERE pedido = :pedido", nativeQuery = true)
    List<Itens> findByPedido(String pedido);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod AS nomec FROM itens ite\n" +
            "\n" +
            " WHERE ite.pedido = :pedido\n" +
            " AND ite.isativo = true  \n" +
            " GROUP BY ite.id\n" +
            " ORDER BY ite.fornecedor, ite.alias  ", nativeQuery = true)
    List<Itens> findByPedidoAll(String pedido);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod AS nomec FROM itens ite\n" +
            " WHERE ite.af = :af\n" +
            " AND ite.isativo = true  \n" +
            " GROUP BY ite.id\n" +
            " ORDER BY ite.fornecedor, ite.alias ", nativeQuery = true)
    List<Itens> findByAf(Long af);

    @Query(value = "SELECT * FROM itens  WHERE escola = :escola  AND pedido = :pedido ", nativeQuery = true)
    List<Itens> findByEscola(Long escola, Long pedido);


    //verificado
    @Query(value = "SELECT ite.*,sum(ite.quantidade) AS tot,ite.cod AS nomec FROM itens ite \n" +
            " WHERE ite.ano = :ano \n" +
            " GROUP BY ite.produto \n" +
            " ORDER BY tot desc", nativeQuery = true)
    List<Itens> findMaisPedidos(Long ano);

    @Query(value = "SELECT * FROM itens ite\n" +
            "INNER join af ON af.code = ite.af\n" +
            " WHERE af.isativo = TRUE and ite.escola = :escola  AND ite.ano = :ano ", nativeQuery = true)
    List<Itens> findEscolar(Long escola, Long ano);

    @Query(value = "SELECT ite.* FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            " WHERE ite.fornecedor = :fornecedor and af > 0 AND af.isativo = TRUE \n" +
            " ", nativeQuery = true)
    List<Itens> findByFornecedor(Long fornecedor);

    @Query(value = "SELECT * FROM itens ite \n" +
            "                   INNER JOIN af ON af.code = ite.af          \n" +
            "                     WHERE af.isativo= true and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    List<Itens> findEscolaAll(Long ano);



    @Query(value = "SELECT ite.*,sum(ite.quantidade) as tot,ite.cod AS nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE ite.ano = :ano and af > 0 AND af.isativo = TRUE\n" +
            "GROUP BY ite.produto\n" +
            "ORDER BY tot DESC ,ite.categoria,ite.alias", nativeQuery = true)
    List<Itens> findItensAno(Long ano);

    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Itens> findTotalMes(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano and ite.nivel = :nivel\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Itens> findTotalMesNivel(Long nivel, Long ano);

   //verificado
    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano and ite.escola = :escola\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Itens> findTotalMesEscola(Long escola, Long ano);


    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, cat.nome as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano and ite.nivel = :nivel\n" +
            "GROUP BY ite.categoria ", nativeQuery = true)
    List<Itens> findTotalCategoriaNivel(Long nivel, Long ano);

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
            "            GROUP BY ite.escola ORDER BY ite.nivel", nativeQuery = true)
    List<Itens> findTotalEscolas(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, esc.alias as nomec FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            INNER JOIN unidade_escolar esc ON esc.id = ite.escola \n" +
            "            WHERE af.isativo = TRUE AND ite.ano = :ano and ite.nivel = :nivel\n" +
            "            GROUP BY ite.escola", nativeQuery = true)
    List<Itens> findTotalEscolaNivel(Long nivel, Long ano);


    //verificado
    @Query(value = "SELECT ite.*,sum(ite.total/esc.alunos) AS tot, esc.alias as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano AND esc.alunos > 0\n" +
            "GROUP BY ite.escola ORDER BY ite.nivel ", nativeQuery = true)
    List<Itens> findMediaAlunos(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total/esc.alunos) AS tot, esc.alias as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE af.isativo = TRUE AND ite.ano = :ano AND esc.alunos > 0 and ite.nivel = :nivel\n" +
            "GROUP BY ite.escola ORDER BY ite.nivel ", nativeQuery = true)
    List<Itens> findMediaAlunosNivel(Long nivel, Long ano);

    @Query(value = "SELECT *, SUM(ite.quantidade) AS tot , ite.cod AS nomec\n" +
            "FROM itens ite \n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE ite.ano = :ano and  af.isativo = true and ite.produto = :produto group BY ite.escola ORDER BY ite.nivel", nativeQuery = true)
    List<Itens> findProduto(Long produto, Long ano);


//verfiicado
    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotal(Long ano);


    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.ano = :ano AND ite.af > 0 AND ite.nivel = :nivel ", nativeQuery = true)
    double findTotalNivel(Long nivel, Long ano);

    //verificado
    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.ano = :ano AND ite.af > 0 AND ite.escola = :escola ", nativeQuery = true)
    double findTotalEscola(Long escola, Long ano);




    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.escola = :escola AND ite.af > 0 ", nativeQuery = true)
    double findSoma(Long escola);

    @Query(value = "SELECT sum(ite.total) as totalAgro  FROM itens ite \n" +
            "INNER JOIN af ON af.code = ite.af          \n" +
            "WHERE af.isativo= true  and ano = :ano and ite.escola = :escola AND ite.af > 0 AND ite.isagro = true", nativeQuery = true)
    double findTotalAgroEscola(Long escola, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.isativo= true and ite.nivel=:nivel AND ite.isagro = true and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotalAgroNivel(Long nivel, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.isativo= true  AND ite.isagro = true and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotalAgro(Long ano);

    @Query(value = "SELECT sum(ite.total) as totalPedido  FROM itens ite \n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.pedido = :pedido AND ite.af > 0 ", nativeQuery = true)
    double findTotalPedido(String pedido);

    @Query(value = "SELECT sum(ite.total) as totalPedido  FROM itens ite \n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.isativo= true  and ite.af = :af AND ite.af > 0 ", nativeQuery = true)
    double findTotalAf(Long af);

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

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.isativo= true   and ite.ano = :ano AND ite.af > 0 \n" +
            "\t\t\t\tAND (ite.categoria = 1 OR ite.categoria = 2 \n" +
            "OR ite.categoria = 3 \n" +
            "OR ite.categoria = 5\n" +
            "OR ite.categoria = 6) AND ite.isagro = FALSE and ite.nivel = :nivel", nativeQuery = true)
    double findTradicionalNivel(Long nivel, Long ano);

    //verificado
    @Query(value = "SELECT sum(ite.total) as tot  \n" +
            "            FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "            INNER JOIN produto pro ON pro.id = ite.produto\n" +
            "            WHERE af.isativo= true   AND ite.ano = :ano AND ite.af > 0 \n" +
            "            AND cat.isalimento = TRUE AND pro.agrofamiliar = FALSE and ite.escola = :escola", nativeQuery = true)
    double findTradicionalEscola(Long escola, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.ano = :ano AND ite.af > 0\n" +
            "AND (ite.categoria = 4 OR ite.categoria = 7)  ", nativeQuery = true)
    double findDiversos(Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.ano = :ano AND ite.nivel = :nivel AND ite.af > 0\n" +
            "AND (ite.categoria = 4 OR ite.categoria = 7)  ", nativeQuery = true)
    double findDiversosNivel(Long nivel, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.isativo= true   and ite.ano = :ano AND ite.escola = :escola AND ite.af > 0\n" +
            "AND (ite.categoria = 4 OR ite.categoria = 7)  ", nativeQuery = true)
    double findDiversosEscola(Long escola, Long ano);

    @Query(value = "SELECT count(ite.id) as tot FROM itens ite INNER JOIN pedido ped ON ped.code = ite.pedido WHERE  ite.isativo = true AND ped.isativo=true AND ite.escola=:escola AND ped.iscart = true", nativeQuery = true)
    long findCart(Long escola);

    @Query(value = " SELECT sum(ite.quantidade) as tot from itens ite\n" +
            "            INNER JOIN pedido ped ON ped.id = ite.pedido\n" +
            "            INNER JOIN produto pro ON pro.id = ite.produto\n" +
            "            WHERE  ped.isativo = TRUE AND ite.produto  = :id", nativeQuery = true)
    long findEstoque(Long id);

}

