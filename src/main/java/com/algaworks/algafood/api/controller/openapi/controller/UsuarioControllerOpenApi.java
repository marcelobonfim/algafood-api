package com.algaworks.algafood.api.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.UsuarioNovoInput;
import com.algaworks.algafood.api.model.input.UsuarioSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioUpdateInput;
import com.algaworks.algafood.api.model.response.UsuarioResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {
	
	@ApiOperation("Lista os usuários")
	CollectionModel<UsuarioResponse> listar();

	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	UsuarioResponse buscar(@ApiParam(value = "Id do usuário", example = "1", required = true) Long id);

	@ApiOperation("Cadastra um usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado"),
	})
	UsuarioResponse adicionar(@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true) UsuarioNovoInput usuarioInput);

	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	UsuarioResponse atualizar(@ApiParam(value = "Id do usuário", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de um usuário com novos dados", required = true) UsuarioUpdateInput usuarioInput);

	@ApiOperation("Exclui um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário excluído"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id do usuário", example = "1", required = true) Long id);
	
	@ApiOperation("Altera a senha do usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Senha alterada"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	void alterarSenha(@ApiParam(value = "Id do usuário", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de senha atual e senha nova", required = true) UsuarioSenhaInput usuarioSenhaInput);

}
