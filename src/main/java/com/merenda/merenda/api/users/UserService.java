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




    public UserDTO getUserById(Long id) {
        Optional<User> usuario = rep.findById(id);
        return usuario.map(UserDTO::create).orElseThrow(() -> new ObjectNotFoundException("Rotas não encontrado"));
    }

    public UserDTO insert(User user) {
        Assert.isNull(user.getId(),"Não foi possível inserir o registro");
        return UserDTO.create(rep.save(user));
    }


    public UserDTO update(User user, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o usuario no banco de dados
        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) {
            User db = optional.get();
            // Copiar as propriedades
            db.setIsativo(user.getIsativo());
            db.setNome(user.getNome());
            db.setEmail(user.getEmail());
            db.setSenha(user.getSenha());
            db.setNivel(user.getNivel());
            db.setModifiedAt(user.getModifiedAt());
            db.setUnidade(user.getUnidade());
            db.setRole(user.getRole());


            System.out.println("Nivel id " + db.getId());
            // Atualiza o usuario
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
