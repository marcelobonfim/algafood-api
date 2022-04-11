package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.model.response.EstadoResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface EstadoControllerOpenApi {
	
	@ApiOperation("Lista os estados")
	List<EstadoResponse> listar();

	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoResponse buscar(@ApiParam(value = "Id de um estado", example = "1", required = true) Long id);

	@ApiOperation("Cadastra um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado"),
	})
	EstadoResponse adicionar(@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true) EstadoInput estadoInput);

	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoResponse atualizar(@ApiParam(value = "Id de um estado", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de um estado com novos dados") EstadoInput estadoInput);

	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluída"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id de um estado", example = "1", required = true) Long id);

}
