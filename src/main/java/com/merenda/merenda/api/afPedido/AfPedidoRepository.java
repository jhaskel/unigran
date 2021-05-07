package com.merenda.merenda.api.afPedido;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AfPedidoRepository extends JpaRepository<AfPedido, Long> {


    List<AfPedido> findAll();






}
