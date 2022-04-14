package com.algaworks.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoResponse {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Permite consultas cozinhas")
	private String nome;
	
	@ApiModelProperty(example = "CONSULTAR_COZINHAS")
	private String descricao;

}
