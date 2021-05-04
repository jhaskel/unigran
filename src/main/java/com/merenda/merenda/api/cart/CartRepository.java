package com.merenda.merenda.api.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT *  FROM cart WHERE escola = :escola AND ativo = TRUE;", nativeQuery = true)
    List<Cart> findByEcola(Long escola);

    @Query(value = "SELECT count(id) as item FROM cart WHERE escola = :escola ", nativeQuery = true)
    double findSoma(Long escola);

    
}
