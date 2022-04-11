package com.algaworks.algafood.api.controller.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.model.response.PedidoResponse;
import com.algaworks.algafood.api.model.response.PedidoResumoResponse;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@ApiOperation("Lista os pedidos")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", value = "Nome das propriedades para filtrar na resposta, separados por vírgula", paramType = "query", type = "string")
	})
	Page<PedidoResumoResponse> pesquisar(PedidoFilter pedidoFilter, Pageable pageable);
	
	@ApiOperation("Busca um pedido por código")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", value = "Nome das propriedades para filtrar na resposta, separados por vírgula", paramType = "query", type = "string")
	})
	@ApiResponses({
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	PedidoResponse buscar(@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigo);
	
	@ApiOperation("Cadastra um pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido cadastrado"),
	})
	PedidoResponse salvar(@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

}
