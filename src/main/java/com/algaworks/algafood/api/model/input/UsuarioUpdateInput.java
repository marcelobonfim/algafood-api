package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateInput {
	
	@ApiModelProperty(example = "Marcelo Silva", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "marceloras@gmail.com", required = true)
	@NotBlank
	@Email
	private String email;
	
}
