package com.merenda.merenda.api.unidade;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nivel;
    private String nome;
    private String alias;
    private String endereco;
    private String bairro;
    private Long alunos;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;


}

