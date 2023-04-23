package com.algaworks.algafood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation("restaurantes")
@Setter
@Getter
public class RestauranteResumoResponse extends RepresentationModel<RestauranteResumoResponse> {
	
	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;
}
