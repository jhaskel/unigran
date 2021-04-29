package com.merenda.merenda.api.nivelEscolar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NivelEscolarRepository extends JpaRepository<NivelEscolar, Long> {

    @Query(value = "select nome from nivel_escolar where id = :id", nativeQuery = true)
    String findNome(Long id);

    @Query(value = "select * from nivel_escolar where id = :id", nativeQuery = true)
    List<NivelEscolar> findId(Long id);

}
