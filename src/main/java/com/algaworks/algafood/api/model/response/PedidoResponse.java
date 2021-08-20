package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResponse {
	
	private String codigo;
	
	private BigDecimal subtotal;
	
    private BigDecimal taxaFrete;
	
    private BigDecimal valorTotal;
	
    private String status;
    
    private OffsetDateTime dataCriacao;
    
    private OffsetDateTime dataConfirmacao;
    
    private OffsetDateTime dataCancelamento;
    
    private OffsetDateTime dataEntrega;
    
	private RestauranteResumoResponse restaurante;
	
	private UsuarioResponse usuario;
	
	private FormaPagamentoResponse formaPagamento;
	
	private EnderecoResponse enderecoEntrega;
	
	private List<ItemPedidoResponse> itens;

}
