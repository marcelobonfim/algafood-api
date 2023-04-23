package com.algaworks.algafood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeResponse extends RepresentationModel<CidadeResponse> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Ribeirão Preto")
	private String nome;
	
    private EstadoResponse estado;

}
