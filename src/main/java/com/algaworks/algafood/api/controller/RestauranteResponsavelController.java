package com.algaworks.algafood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.controller.openapi.controller.RestauranteResponsavelControllerOpenApi;
import com.algaworks.algafood.api.mapper.UsuarioResponseMapper;
import com.algaworks.algafood.api.model.response.UsuarioResponse;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{idRestaurante}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteResponsavelController implements RestauranteResponsavelControllerOpenApi {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioResponseMapper usuarioResponseMapper;
	
	@GetMapping
	public CollectionModel<UsuarioResponse> listar(@PathVariable Long idRestaurante) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(idRestaurante);
		
		return usuarioResponseMapper.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(linkTo(methodOn(RestauranteResponsavelController.class)
				.listar(idRestaurante)).withSelfRel());
	}
	
	@PutMapping("/{idResponsavel}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associoarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idResponsavel) {
		restauranteService.associarResponsavel(idRestaurante, idResponsavel);
	}
	
	@DeleteMapping("/{idResponsavel}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassocioarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idResponsavel) {
		restauranteService.desAssociarResponsavel(idRestaurante, idResponsavel);
	}

}
