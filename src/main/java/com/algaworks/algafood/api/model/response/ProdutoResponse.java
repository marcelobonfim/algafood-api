package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoResponse {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Porco com molho agridoce")
	private String nome;
	
	@ApiModelProperty(example = "Deliciosa carne suína ao molho especial")
	private String descricao;
	
	@ApiModelProperty(example = "78.90")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;

}
