package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.model.dto.Mensagem;
import com.algaworks.algafood.domain.service.EnvioEmailService;

@Component
public class NotificacaoClientePedidoCanceladoListener {
	
	@Autowired
	private EnvioEmailService envioEmailService;
	
	@EventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		var pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
				.corpo("pedido-cancelado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getUsuario().getEmail())
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
}
