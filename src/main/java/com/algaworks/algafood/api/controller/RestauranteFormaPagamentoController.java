package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.controller.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.algaworks.algafood.api.mapper.FormaPagamentoResponseMapper;
import com.algaworks.algafood.api.model.response.FormaPagamentoResponse;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{idRestaurante}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoResponseMapper formaPagamentoResponseMapper;
	
	@GetMapping
	public List<FormaPagamentoResponse> listar(@PathVariable Long idRestaurante) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(idRestaurante);
		
		return formaPagamentoResponseMapper.toCollectionResponse(restaurante.getFormasPagamento());
	}
	
	@PutMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associoarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idFormaPagamento) {
		restauranteService.associarFormaPagamento(idRestaurante, idFormaPagamento);
	}
	
	@DeleteMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassocioarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idFormaPagamento) {
		restauranteService.desassociarFormaPagamento(idRestaurante, idFormaPagamento);
	}

}
