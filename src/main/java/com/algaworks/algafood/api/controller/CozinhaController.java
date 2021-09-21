package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.mapper.CozinhaInputMapper;
import com.algaworks.algafood.api.mapper.CozinhaResponseMapper;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.model.response.CozinhaResponse;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired 
	private CozinhaResponseMapper cozinhaResponseMapper;
	
	@Autowired
	private CozinhaInputMapper cozinhaInputMapper;

	@GetMapping
	public Page<CozinhaResponse> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		List<CozinhaResponse> cozinhasResponse = cozinhaResponseMapper.toCollectionResponse(cozinhasPage.getContent());
		
		Page<CozinhaResponse> cozinhasResponsePage = new PageImpl<CozinhaResponse>(cozinhasResponse, pageable, cozinhasPage.getTotalElements());
		
		return cozinhasResponsePage;
	}

	@GetMapping("/{id}")
	public CozinhaResponse buscar(@PathVariable Long id) {
		return cozinhaResponseMapper.toResponse(cozinhaService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaResponse adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputMapper.toDomain(cozinhaInput);
		return cozinhaResponseMapper.toResponse(cozinhaService.salvar(cozinha));
	}

	@PutMapping("/{id}")
	public CozinhaResponse atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
		
		cozinhaInputMapper.fromInputToDomain(cozinhaInput, cozinhaAtual);

		return cozinhaResponseMapper.toResponse(cozinhaService.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cozinhaService.excluir(id);
	}
}
