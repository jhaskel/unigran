package com.merenda.merenda.api.unidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query(value = "SELECT *  FROM unidades order by id;", nativeQuery = true)
    List<Unidade> findAll();

    List<Unidade> findEscolaById(Long escola);

    @Query(value = "SELECT count(id) as quant  FROM unidades ", nativeQuery = true)
    long findQuantidade();

    @Query(value = "SELECT sum(alunos) as quantAlunos  FROM unidades ", nativeQuery = true)
    long findQuantAlunos();

    @Query(value = "SELECT sum(alunos) as quantAlunos  FROM unidades where id = :id ", nativeQuery = true)
    long findQuantAlunosEscola(Long id);

    @Query(value = "select nome from unidades where id = :id", nativeQuery = true)
    String findNome(Long id);

}
