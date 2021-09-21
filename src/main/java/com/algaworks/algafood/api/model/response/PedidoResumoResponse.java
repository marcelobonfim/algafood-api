package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoResponse {
	
	private String codigo;
	
	private BigDecimal subtotal;
	
    private BigDecimal taxaFrete;
	
    private BigDecimal valorTotal;
	
    private String status;
    
    private OffsetDateTime dataCriacao;
    
	private RestauranteResumoResponse restaurante;
	
	private UsuarioResponse usuario;
}
