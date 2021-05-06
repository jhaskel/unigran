package com.merenda.merenda.api.compras;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComprasRepository extends JpaRepository<Compras, Long> {

    @Query(value = "SELECT *  FROM itens WHERE  af = 0;", nativeQuery = true)
    List<Compras> findAll();

    @Query(value = "SELECT * FROM itens WHERE af > 0  GROUP BY af;", nativeQuery = true)
    List<Compras> findAll2();

    @Query(value = "SELECT * FROM itens GROUP BY pedido;", nativeQuery = true)
    List<Compras> findAll3();


    @Query(value = "SELECT * FROM itens WHERE pedido = :pedido ", nativeQuery = true)
    List<Compras> findByPedido(String pedido);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot FROM itens ite\n" +
            "\n" +
            " WHERE ite.pedido = :pedido \n" +
            " AND ite.ativo = true  \n" +
            " GROUP BY ite.id\n" +
            " ORDER BY ite.fornecedor, ite.alias  ", nativeQuery = true)
    List<Compras> findByPedidoAll(String pedido);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod AS nomec FROM itens ite\n" +
            " WHERE ite.af = :af\n" +
            " AND ite.ativo = true  \n" +
            " GROUP BY ite.id\n" +
            " ORDER BY ite.fornecedor, ite.alias ", nativeQuery = true)
    List<Compras> findByAf(Long af);

    @Query(value = "SELECT * FROM itens  WHERE escola = :escola  AND pedido = :pedido ", nativeQuery = true)
    List<Compras> findByEscola(Long escola, Long pedido);

    @Query(value = "SELECT * FROM itens ite\n" +
            "INNER join af ON af.code = ite.af\n" +
            " WHERE af.ativo = TRUE and ite.escola = :escola  AND ite.ano = :ano ", nativeQuery = true)
    List<Compras> findEscolar(Long escola, Long ano);

    @Query(value = "SELECT ite.* FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            " WHERE ite.fornecedor = :fornecedor and af > 0 AND af.ativo = TRUE \n" +
            " ", nativeQuery = true)
    List<Compras> findByFornecedor(Long fornecedor);

    @Query(value = "SELECT * FROM itens ite \n" +
            "                   INNER JOIN af ON af.code = ite.af          \n" +
            "                     WHERE af.ativo= true and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    List<Compras> findEscolaAll(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.quantidade) as tot,ite.cod AS nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE ite.ano = :ano and af > 0 AND af.ativo = TRUE\n" +
            "GROUP BY ite.produto\n" +
            "ORDER BY tot DESC ,ite.categoria,ite.alias", nativeQuery = true)
    List<Compras> findItensAno(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Compras> findTotalMes(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano and ite.nivel = :nivel\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Compras> findTotalMesNivel(Long nivel, Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot,ite.cod as nomec  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano and ite.escola = :escola\n" +
            "GROUP BY ite.mes ", nativeQuery = true)
    List<Compras> findTotalMesEscola(Long escola, Long ano);


    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, cat.nome as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano and ite.nivel = :nivel\n" +
            "GROUP BY ite.categoria ", nativeQuery = true)
    List<Compras> findTotalCategoriaNivel(Long nivel, Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, cat.nome as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano and ite.escola = :escola\n" +
            "GROUP BY ite.categoria ", nativeQuery = true)
    List<Compras> findTotalCategoriaEscola(Long escola, Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, cat.nome as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN categoria cat ON cat.id = ite.categoria\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano\n" +
            "GROUP BY ite.categoria ", nativeQuery = true)
    List<Compras> findTotalCategoria(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, esc.alias as nomec FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            INNER JOIN unidade_escolar esc ON esc.id = ite.escola \n" +
            "            WHERE af.ativo = TRUE AND ite.ano = :ano \n" +
            "            GROUP BY ite.escola", nativeQuery = true)
    List<Compras> findTotalEscolas(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total) AS tot, esc.alias as nomec FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            INNER JOIN unidade_escolar esc ON esc.id = ite.escola \n" +
            "            WHERE af.ativo = TRUE AND ite.ano = :ano and ite.nivel = :nivel\n" +
            "            GROUP BY ite.escola", nativeQuery = true)
    List<Compras> findTotalEscolaNivel(Long nivel, Long ano);


    @Query(value = "SELECT ite.*,sum(ite.total/esc.alunos) AS tot, esc.alias as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano AND esc.alunos > 0\n" +
            "GROUP BY ite.escola ", nativeQuery = true)
    List<Compras> findMediaAlunos(Long ano);

    @Query(value = "SELECT ite.*,sum(ite.total/esc.alunos) AS tot, esc.alias as nomec FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "INNER JOIN unidade_escolar esc ON esc.id = ite.escola\n" +
            "WHERE af.ativo = TRUE AND ite.ano = :ano AND esc.alunos > 0 and ite.nivel = :nivel\n" +
            "GROUP BY ite.escola ", nativeQuery = true)
    List<Compras> findMediaAlunosNivel(Long nivel, Long ano);


    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotal(Long ano);


    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0 AND ite.nivel = :nivel ", nativeQuery = true)
    double findTotalNivel(Long nivel, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0 AND ite.escola = :escola ", nativeQuery = true)
    double findTotalEscola(Long escola, Long ano);




    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.escola = :escola AND ite.af > 0 ", nativeQuery = true)
    double findSoma(Long escola);

    @Query(value = "SELECT sum(ite.total) as totalAgro  FROM itens ite \n" +
            "INNER JOIN af ON af.code = ite.af          \n" +
            "WHERE af.ativo= true  and ano = :ano and ite.escola = :escola AND ite.af > 0 AND ite.isagro = true", nativeQuery = true)
    double findTotalAgroEscola(Long escola, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.ativo= true and ite.nivel=:nivel AND ite.isagro = true and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotalAgroNivel(Long nivel, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.ativo= true  AND ite.isagro = true and ite.ano = :ano AND ite.af > 0 ", nativeQuery = true)
    double findTotalAgro(Long ano);

    @Query(value = "SELECT sum(ite.total) as totalPedido  FROM itens ite \n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.pedido = :pedido AND ite.af > 0 ", nativeQuery = true)
    double findTotalPedido(String pedido);

    @Query(value = "SELECT sum(ite.total) as totalPedido  FROM itens ite \n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.ativo= true  and ite.af = :af AND ite.af > 0 ", nativeQuery = true)
    double findTotalAf(Long af);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0 \n" +
            "\t\t\t\tAND (ite.categoria = 1 OR ite.categoria = 2 \n" +
            "OR ite.categoria = 3 \n" +
            "OR ite.categoria = 5\n" +
            "OR ite.categoria = 6) AND ite.isagro = FALSE ", nativeQuery = true)
    double findTradicional(Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0 \n" +
            "\t\t\t\tAND (ite.categoria = 1 OR ite.categoria = 2 \n" +
            "OR ite.categoria = 3 \n" +
            "OR ite.categoria = 5\n" +
            "OR ite.categoria = 6) AND ite.isagro = FALSE and ite.nivel = :nivel", nativeQuery = true)
    double findTradicionalNivel(Long nivel, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "            INNER JOIN af ON af.code = ite.af\n" +
            "            WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0 \n" +
            "\t\t\t\tAND (ite.categoria = 1 OR ite.categoria = 2 \n" +
            "OR ite.categoria = 3 \n" +
            "OR ite.categoria = 5\n" +
            "OR ite.categoria = 6) AND ite.isagro = FALSE and ite.escola = :escola", nativeQuery = true)
    double findTradicionalEscola(Long escola, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.ano = :ano AND ite.af > 0\n" +
            "AND (ite.categoria = 4 OR ite.categoria = 7)  ", nativeQuery = true)
    double findDiversos(Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.ano = :ano AND ite.nivel = :nivel AND ite.af > 0\n" +
            "AND (ite.categoria = 4 OR ite.categoria = 7)  ", nativeQuery = true)
    double findDiversosNivel(Long nivel, Long ano);

    @Query(value = "SELECT sum(ite.total) as tot  FROM itens ite\n" +
            "INNER JOIN af ON af.code = ite.af\n" +
            "WHERE af.ativo= true   and ite.ano = :ano AND ite.escola = :escola AND ite.af > 0\n" +
            "AND (ite.categoria = 4 OR ite.categoria = 7)  ", nativeQuery = true)
    double findDiversosEscola(Long escola, Long ano);



}
