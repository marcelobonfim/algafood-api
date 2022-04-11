package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.model.response.CidadeResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface CidadeControllerOpenApi {
	
	@ApiOperation("Lista as cidades")
	List<CidadeResponse> listar();
	
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	CidadeResponse buscar(@ApiParam(value = "Id de uma cidade", example = "1", required = true) Long id);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	CidadeResponse salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	CidadeResponse atualizar(@ApiParam(value = "Id de uma cidade", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados") CidadeInput cidadeInput);
	
	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id de uma cidade", example = "1", required = true) Long id);

}