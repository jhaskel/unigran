package com.merenda.merenda.api.af;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Af {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    private Long fornecedor;
    private String status;
    private Boolean isenviado;
    private String createdAt;
    private Boolean isativo;



    //não esta no banco
    private String nomefor;
    private Double tot;













}

