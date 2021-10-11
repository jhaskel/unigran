package com.merenda.merenda.api.setor;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor
@Data
@Entity

public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Boolean isativo;
    private Boolean isalimento;
    private String createdAt;
    private String modifiedAt;


/*

    @Transient
    @JsonIgnore
    private String teste;
*/







}

