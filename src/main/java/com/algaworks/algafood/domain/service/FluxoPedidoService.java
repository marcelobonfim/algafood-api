package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional
	public void confirmacao(String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
		
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void entrega(String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		pedido.entregar();
	}
	
	@Transactional
	public void cancelamento(String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		pedido.cancelar();
		
		pedidoRepository.save(pedido);
	}
}
