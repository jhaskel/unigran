package com.merenda.merenda.api.categorias;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@NoArgsConstructor
@Data
@Entity

public class Categoria {
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

