package com.merenda.merenda.api.usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByLogin(String login);

    @Query(value = "SELECT * from user where id = :id",nativeQuery = true)
    List<Usuario> findTesteById(Long id);


    @Query(value = "SELECT * from user where email = :email",nativeQuery = true)
    List<Usuario> findTesteByEmail(String email);


    @Query(value = "SELECT * from user where nivel = :nivel",nativeQuery = true)
    List<Usuario> findTesteByNivel(String nivel);


    @Query(value = "SELECT  quantnoticias  FROM user WHERE  id = :usuario", nativeQuery = true)
    double QuantNoticia(Long usuario);


    @Query(value = "SELECT  count(id)  FROM user WHERE  email = :email", nativeQuery = true)
    Long QuantEmail(String email);



}
