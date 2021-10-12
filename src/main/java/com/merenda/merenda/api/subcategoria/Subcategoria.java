package com.merenda.merenda.api.subcategoria;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor
@Data
@Entity

public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoria;
    private String nome;
    private String icone;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;


/*

    @Transient
    @JsonIgnore
    private String teste;
*/







}

