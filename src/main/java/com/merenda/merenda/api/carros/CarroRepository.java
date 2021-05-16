package com.merenda.merenda.api.carros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    @Query(value = " SELECT car.* FROM carro car where car.tipo = :tipo", nativeQuery = true)
    List<Carro> findByTipo(String tipo);

    @Query(value = " update carro set tipo = 'novo' where tipo = :tipo", nativeQuery = true)
    List<Carro> findByPutTipo(String tipo);
}
