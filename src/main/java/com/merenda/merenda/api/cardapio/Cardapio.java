package com.merenda.merenda.api.cardapio;

import com.merenda.merenda.api.refeicao.Refeicao;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Cardapio {

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

