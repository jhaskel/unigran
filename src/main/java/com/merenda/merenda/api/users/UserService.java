package com.merenda.merenda.api.users;


import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public List<UserDTO> getUsers() {
        return rep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }


    public UserDTO insert(User user) {
        Assert.isNull(user.getId(),"Não foi possível inserir o registro");
        return UserDTO.create(rep.save(user));
    }

    public UserDTO getUserById(Long id) {
        Optional<User> carro = rep.findById(id);
        return carro.map(UserDTO::create).orElseThrow(() -> new ObjectNotFoundException("Rotas não encontrado"));
    }


    public UserDTO update(User user, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) {
            User db = optional.get();
            // Copiar as propriedades
            db.setIsativo(user.getIsativo());
            db.setNome(user.getNome());
            db.setEmail(user.getEmail());
            db.setSenha(user.getSenha());
            db.setEscola(user.getEscola());
            db.setNivel(user.getNivel());
            db.setModifiedAt(user.getModifiedAt());
            db.setRole(user.getRole());


            System.out.println("Nivel id " + db.getId());
            // Atualiza o carro
            rep.save(db);

            return UserDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }

}
