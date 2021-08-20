package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.algaworks.algafood.api.mapper.GrupoInputMapper;
import com.algaworks.algafood.api.mapper.GrupoResponseMapper;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.model.response.GrupoResponse;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoResponseMapper grupoResponseMapper;
	
	@Autowired
	private GrupoInputMapper grupoInputMapper;

	@GetMapping
	public List<GrupoResponse> listar() {
		return grupoResponseMapper.toCollectionResponse(grupoRepository.findAll());
	}

	@GetMapping("/{id}")
	public GrupoResponse buscar(@PathVariable Long id) {
		return grupoResponseMapper.toResponse(grupoService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoResponse adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputMapper.toDomain(grupoInput);
		
		return grupoResponseMapper.toResponse(grupoService.salvar(grupo));
	}

	@PutMapping("/{id}")
	public GrupoResponse atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = grupoService.buscarOuFalhar(id);
		
		grupoInputMapper.fromInputToDomain(grupoInput, grupoAtual);

		return grupoResponseMapper.toResponse(grupoService.salvar(grupoAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		grupoService.remover(id);
	}

}
