package com.merenda.merenda.api.cardapio.cardapioAdd;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor
@Data
@Entity(name = "cardapio")
public class CardapioAdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semana;
    private String escola;
    private String dia;
    private String periodo;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;


}

