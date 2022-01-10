package com.algaworks.algafood.api.controller.openapi;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.model.response.GrupoResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface GrupoControllerOpenApi {
	
	@ApiOperation("Lista os grupos")
	public List<GrupoResponse> listar();

	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoResponse buscar(@ApiParam(value = "Id de um grupo", example = "1") Long id);

	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado"),
	})
	public GrupoResponse adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);

	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoResponse atualizar(@ApiParam(value = "Id de um grupo", example = "1") Long id, @ApiParam(name = "corpo", value = "Representação de um novo grupo com novos dados") GrupoInput grupoInput);

	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public void remover(@ApiParam(value = "Id de um grupo", example = "1") Long id);

}
