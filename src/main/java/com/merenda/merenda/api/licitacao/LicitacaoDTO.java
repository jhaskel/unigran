package com.merenda.merenda.api.licitacao;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class LicitacaoDTO {
    private Long id;
    private Long ano;
    private String processo;
    private String edital;
    private String objeto;
    private String alias;
    private Double valorestimado;
    private Double valorfinal;
    private Long prazo;
    private String status;
    private String aberturaAt;
    private String homologadodAt;
    private String createdAt;
    private String modifiedAt;
    private Boolean isativo;

    public static LicitacaoDTO create(Licitacao licitacao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(licitacao, LicitacaoDTO.class);
    }
}
