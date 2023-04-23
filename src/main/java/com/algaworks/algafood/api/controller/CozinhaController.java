package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.controller.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.api.mapper.CozinhaInputMapper;
import com.algaworks.algafood.api.mapper.CozinhaResponseMapper;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.model.response.CozinhaResponse;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

import io.swagger.annotations.Api;

@Api(tags = "Cozinhas")
@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired 
	private CozinhaResponseMapper cozinhaResponseMapper;
	
	@Autowired
	private CozinhaInputMapper cozinhaInputMapper;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<CozinhaResponse> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaResponse> cozinhasPagedModel = pagedResourcesAssembler
				.toModel(cozinhasPage, cozinhaResponseMapper);
		
		return cozinhasPagedModel;
	}

	@GetMapping("/{id}")
	public CozinhaResponse buscar(@PathVariable Long id) {
		return cozinhaResponseMapper.toModel(cozinhaService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaResponse adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputMapper.toDomain(cozinhaInput);
		return cozinhaResponseMapper.toModel(cozinhaService.salvar(cozinha));
	}

	@PutMapping("/{id}")
	public CozinhaResponse atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
		
		cozinhaInputMapper.fromInputToDomain(cozinhaInput, cozinhaAtual);

		return cozinhaResponseMapper.toModel(cozinhaService.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cozinhaService.excluir(id);
	}
}
