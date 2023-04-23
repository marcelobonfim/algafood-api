package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.controller.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.api.mapper.EstadoInputMapper;
import com.algaworks.algafood.api.mapper.EstadoResponseMapper;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.model.response.EstadoResponse;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;

import io.swagger.annotations.Api;

@Api(tags = "Estados")
@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoResponseMapper estadoResponseMapper;
	
	@Autowired
	private EstadoInputMapper estadoInputMapper;

	@GetMapping
	public CollectionModel<EstadoResponse> listar() {
		return estadoResponseMapper.toCollectionModel(estadoRepository.findAll());
	}

	@GetMapping("/{id}")
	public EstadoResponse buscar(@PathVariable Long id) {
		return estadoResponseMapper.toModel(estadoService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoResponse adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputMapper.toDomain(estadoInput);
		
		return estadoResponseMapper.toModel(estadoService.salvar(estado));
	}

	@PutMapping("/{id}")
	public EstadoResponse atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(id);
		
		estadoInputMapper.fromInputToDomain(estadoInput, estadoAtual);

		return estadoResponseMapper.toModel(estadoService.salvar(estadoAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		estadoService.remover(id);
	}
}
