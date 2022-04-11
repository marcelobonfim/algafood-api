package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.controller.openapi.controller.FluxoPedidoControllerOpenApi;
import com.algaworks.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{codigoPedido}")
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmacao(@PathVariable String codigoPedido) {
		fluxoPedidoService.confirmacao(codigoPedido);
	}
	
	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable String codigoPedido) {
		fluxoPedidoService.entrega(codigoPedido);
	}
	
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelamento(@PathVariable String codigoPedido) {
		fluxoPedidoService.cancelamento(codigoPedido);
	}

}
