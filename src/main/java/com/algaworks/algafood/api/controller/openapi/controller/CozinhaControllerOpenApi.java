package com.algaworks.algafood.api.controller.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.model.response.CozinhaResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Lista as cozinhas")
	PagedModel<CozinhaResponse> listar(Pageable pageable) ;

	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	CozinhaResponse buscar(@ApiParam(value = "Id de uma cozinha", example = "1", required = true) Long id);

	@ApiOperation("Cadastra uma cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada"),
	})
	CozinhaResponse adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);

	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	CozinhaResponse atualizar(@ApiParam(value = "Id de uma cozinha", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de uma nova cozinha com novos dados") CozinhaInput cozinhaInput);

	@ApiOperation("Exclui uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id de uma cozinha", example = "1", required = true) Long id);

}
