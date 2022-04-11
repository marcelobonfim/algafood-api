package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.algaworks.algafood.api.controller.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.api.mapper.CidadeInputMapper;
import com.algaworks.algafood.api.mapper.CidadeResponseMapper;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.model.response.CidadeResponse;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;

import io.swagger.annotations.Api;

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeResponseMapper cidadeResponseMapper;
	
	@Autowired
	private CidadeInputMapper cidadeInputMapper;
	
	@Override
	@GetMapping
	public List<CidadeResponse> listar() {
		return cidadeResponseMapper.toCollectionResponse(cidadeRepository.findAll());
	}
	
	@Override
	@GetMapping("/{id}")
	public CidadeResponse buscar(@PathVariable Long id) {
		return cidadeResponseMapper.toResponse(cidadeService.buscarOuFalhar(id));
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeResponse salvar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputMapper.toDomain(cidadeInput);
			
			return cidadeResponseMapper.toResponse(cidadeService.salvar(cidade));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	@PutMapping("/{id}")
	public CidadeResponse atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);
			
			cidadeInputMapper.fromInputToDomain(cidadeInput, cidadeAtual);
			
			return cidadeResponseMapper.toResponse(cidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cidadeService.remover(id);
	}
}
