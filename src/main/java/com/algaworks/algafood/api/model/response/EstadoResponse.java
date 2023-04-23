package com.algaworks.algafood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoResponse extends RepresentationModel<EstadoResponse> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "São Paulo")
	private String nome;

}
