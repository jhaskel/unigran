package com.merenda.merenda.api.users;

import com.merenda.merenda.api.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);

    @Query(value = "SELECT * from user where id = :id",nativeQuery = true)
    List<User> findId(Long id);
}
