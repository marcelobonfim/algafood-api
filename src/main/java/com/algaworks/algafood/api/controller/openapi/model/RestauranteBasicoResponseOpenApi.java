package com.algaworks.algafood.api.controller.openapi.model;

import java.math.BigDecimal;

import com.algaworks.algafood.api.model.response.CozinhaResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteBasicoResponse")
@Setter
@Getter
public class RestauranteBasicoResponseOpenApi {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;
	
	@ApiModelProperty(example = "12.00")
	private BigDecimal taxaFrete;
	
	
	private CozinhaResponse cozinha;

}
