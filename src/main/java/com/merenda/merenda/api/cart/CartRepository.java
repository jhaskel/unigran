package com.merenda.merenda.api.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT *  FROM cart WHERE local = :local order by id desc;", nativeQuery = true)
    List<Cart> findByLocal(Long local);

    @Query(value = "SELECT count(id) as item FROM cart WHERE local = :local ", nativeQuery = true)
    double findSoma(Long local);

    @Query(value = "SELECT sum(quantidade) as item FROM cart WHERE  produto = :produto", nativeQuery = true)
    double findCart(Long produto);



}
