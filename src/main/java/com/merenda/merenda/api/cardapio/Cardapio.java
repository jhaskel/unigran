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
    private Long escola;
    private String nomedaescola;
    private String tipo;
    private String imagem;
    private String title;
    private String content;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

}

