package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.response.FormaPagamentoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento associadas ao restaurante")
	@ApiResponses({
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	List<FormaPagamentoResponse> listar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long idRestaurante);
	
	@ApiOperation("Associação de restaurante com forma de pagamento")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", 
            response = Problem.class)
    })
	void associoarFormaPagamento(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long idRestaurante, @ApiParam(value = "Id da forma de pagamento", example = "1", required = true)  Long idFormaPagamento);
	
	@ApiOperation("Desassociação de restaurante com forma de pagamento")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", 
            response = Problem.class)
    })
	void desassocioarFormaPagamento(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long idRestaurante, @ApiParam(value = "Id da forma de pagamento", example = "1", required = true)  Long idFormaPagamento);

}
