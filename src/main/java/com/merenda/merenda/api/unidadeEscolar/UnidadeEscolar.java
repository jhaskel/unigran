package com.merenda.merenda.api.unidadeEscolar;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class UnidadeEscolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nivelescolar;
    private String nome;
    private String alias;
    private String endereco;
    private String bairro;
    private Long alunos;
    private Boolean ativo;
    private String created;
    private String modified;

}

