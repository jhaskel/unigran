package com.merenda.merenda.api.cardapio;


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
    private Long escola;
    private String nomedaescola;
    private String imagem;
    private String title;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;



}

