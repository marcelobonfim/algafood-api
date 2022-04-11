package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.model.response.FormaPagamentoResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface FormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento")
	ResponseEntity<List<FormaPagamentoResponse>> listar(ServletWebRequest request);
	
	@ApiOperation("Busca uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	ResponseEntity<FormaPagamentoResponse> buscar(@ApiParam(value = "Id de uma forma de pagamento", example = "1", required = true) Long id, ServletWebRequest request);
	
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de papgamento cadastrada"),
	})
	FormaPagamentoResponse adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	FormaPagamentoResponse atualizar(@ApiParam(value = "Id de uma forma de pagamento", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento com novos dados") FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id de uma forma de pagamento", example = "1", required = true) Long id);

}
