package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.response.PermissaoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {
	
	@ApiOperation("Lista os permissões associadas ao grupo")
	@ApiResponses({
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
	List<PermissaoResponse> listar(@ApiParam(value = "Id do grupo", example = "1", required = true) Long idGrupo);
	
	@ApiOperation("Associação de grupo com permissão")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", 
            response = Problem.class)
    })
	void associoarFormaPagamento(@ApiParam(value = "Id do grupo", example = "1", required = true) Long idGrupo, @ApiParam(value = "Id da permissão", example = "1", required = true) Long idPermissao);
	
	@ApiOperation("Desassociação de grupo com permissão")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", 
            response = Problem.class)
    })
	void desassocioarFormaPagamento(@ApiParam(value = "Id do grupo", example = "1", required = true) Long idGrupo, @ApiParam(value = "Id da permissão", example = "1", required = true) Long idPermissao);

}
