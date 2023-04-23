package com.algaworks.algafood.api.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.response.UsuarioResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteResponsavelControllerOpenApi {
	
	@ApiOperation("Lista os responsáveis associados ao restaurante")
	@ApiResponses({
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	CollectionModel<UsuarioResponse> listar(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long idRestaurante);
	
	@ApiOperation("Associação de restaurante com responsável")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante ou responsável não encontrado", 
            response = Problem.class)
    })
	void associoarFormaPagamento(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long idRestaurante, @ApiParam(value = "Id do responsável", example = "1", required = true) Long idResponsavel);
	
	@ApiOperation("Desassociação de restaurante com responsável")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante ou responsável não encontrado", 
            response = Problem.class)
    })
	void desassocioarFormaPagamento(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long idRestaurante, @ApiParam(value = "Id do responsável", example = "1", required = true) Long idResponsavel);

}
