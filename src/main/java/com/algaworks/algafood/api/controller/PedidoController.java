package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.mapper.generic.InputMapper;
import com.algaworks.algafood.api.mapper.generic.ResponseMapper;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.model.response.PedidoResponse;
import com.algaworks.algafood.api.model.response.PedidoResumoResponse;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ResponseMapper<Pedido, PedidoResponse> pedidoResponseMapper;
	
	@Autowired
	private ResponseMapper<Pedido, PedidoResumoResponse> pedidoResumoResponseMapper;
	
	@Autowired
	InputMapper<PedidoInput, Pedido> pedidoInputMapper;
	
	@GetMapping
	public List<PedidoResumoResponse> listar() {
		return pedidoResumoResponseMapper.toCollectionResponse(pedidoRepository.findAll(), PedidoResumoResponse.class);
	}
	
	@GetMapping("{codigo}")
	public PedidoResponse buscar(@PathVariable String codigo) {
		return pedidoResponseMapper.toResponse(pedidoService.buscarOuFalhar(codigo), PedidoResponse.class);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoResponse salvar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido pedidoNovo = pedidoInputMapper.toDomain(pedidoInput, Pedido.class);
	    
	        // TODO pegar usuário autenticado
			pedidoNovo.setUsuario(new Usuario());
			pedidoNovo.getUsuario().setId(1L);
			
	        pedidoNovo = pedidoService.salvar(pedidoNovo);
	        
	        return pedidoResponseMapper.toResponse(pedidoNovo, PedidoResponse.class);
	    } catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
}
