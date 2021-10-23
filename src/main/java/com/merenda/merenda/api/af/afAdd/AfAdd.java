package com.merenda.merenda.api.af.afAdd;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity(name = "af")
public class AfAdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    private Long nivel;
    private Long setor;
    private Long fornecedor;
    private String status;
    private Boolean isenviado;
    private String createdAt;
    private Boolean isativo;












}

