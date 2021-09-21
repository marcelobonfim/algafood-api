package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.PedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;

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
	
//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//		List<Pedido> pedidos = pedidoRepository.findAll();
//		List<PedidoResumoResponse> pedidosResumoResponse = pedidoResumoResponseMapper.toCollectionResponse(pedidos, PedidoResumoResponse.class);  
//		
//		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosResumoResponse);
//		
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//		
//		if (StringUtils.isNotBlank(campos)) {
//			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//		}
//		
//		pedidosWrapper.setFilters(filterProvider);
//		
//		return pedidosWrapper;
//	}
	
	@GetMapping
	public Page<PedidoResumoResponse> pesquisar(PedidoFilter pedidoFilter, @PageableDefault(size = 10) Pageable pageable) {
		pageable = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.comFiltro(pedidoFilter), pageable);
		
		List<PedidoResumoResponse> pedidosResumoResponse = pedidoResumoResponseMapper.toCollectionResponse(pedidosPage.getContent(), PedidoResumoResponse.class);
		
		Page<PedidoResumoResponse> pedidosResumoResponsePage = new PageImpl<>(pedidosResumoResponse, pageable, pedidosPage.getTotalElements());
		
		return pedidosResumoResponsePage;
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
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.nome", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"usuario.id", "usuario.id",
				"usuario.nome", "usuario.nome"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
