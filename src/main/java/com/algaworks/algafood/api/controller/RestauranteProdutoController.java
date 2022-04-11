package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.controller.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.api.mapper.generic.InputMapper;
import com.algaworks.algafood.api.mapper.generic.ResponseMapper;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.api.model.response.ProdutoResponse;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{idRestaurante}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	private ResponseMapper<Produto, ProdutoResponse> produtoResponseMapper;

	@Autowired
	InputMapper<ProdutoInput, Produto> produtoInputMapper;

	@GetMapping
	public List<ProdutoResponse> listar(@PathVariable Long idRestaurante, @RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(idRestaurante);

		List<Produto> produtos = null;
		
		if (incluirInativos) {
			produtos = produtoRepository.findTodosByRestaurante(restaurante);
		} else {
			produtos = produtoRepository.findAtivosByRestaurante(restaurante);
		}

		return produtoResponseMapper.toCollectionResponse(produtos, ProdutoResponse.class);
	}

	@GetMapping("/{idProduto}")
	public ProdutoResponse buscar(@PathVariable Long idRestaurante, @PathVariable Long idProduto) {
		Produto produto = produtoService.buscarOuFalhar(idRestaurante, idProduto);

		return produtoResponseMapper.toResponse(produto, ProdutoResponse.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoResponse salvar(@PathVariable Long idRestaurante, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(idRestaurante);

		Produto produto = produtoInputMapper.toDomain(produtoInput, Produto.class);
		produto.setRestaurante(restaurante);

		produto = produtoService.salvar(produto);
		return produtoResponseMapper.toResponse(produto, ProdutoResponse.class);
	}

	@PutMapping("/{idProduto}")
	public ProdutoResponse atualizar(@PathVariable Long idRestaurante, @PathVariable Long idProduto,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produto = produtoService.buscarOuFalhar(idRestaurante, idProduto);

		produtoInputMapper.fromInputToDomain(produtoInput, produto);

		produto = produtoService.salvar(produto);
		return produtoResponseMapper.toResponse(produto, ProdutoResponse.class);
	}

}
