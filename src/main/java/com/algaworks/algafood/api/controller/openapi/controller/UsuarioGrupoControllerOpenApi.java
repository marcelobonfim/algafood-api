package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.response.GrupoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {
	
	@ApiOperation("Lista os grupos associados ao usuário")
	@ApiResponses({
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
	List<GrupoResponse> listar(@ApiParam(value = "Id do usuário", example = "1", required = true) Long idUsuario);
	
	@ApiOperation("Associação de usuário com grupo")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", 
            response = Problem.class)
    })
	void associoarGrupo(@ApiParam(value = "Id do usuário", example = "1", required = true) Long idUsuario, @ApiParam(value = "Id do grupo", example = "1", required = true) Long idGrupo);
	
	@ApiOperation("Desassociação de usuário com grupo")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", 
            response = Problem.class)
    })
	void desassocioarFormaPagamento(@ApiParam(value = "Id do usuário", example = "1", required = true) Long idUsuario, @ApiParam(value = "Id do grupo", example = "1", required = true) Long idGrupo);

}
