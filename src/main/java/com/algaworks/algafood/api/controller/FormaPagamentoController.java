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

import com.algaworks.algafood.api.mapper.FormaPagamentoInputMapper;
import com.algaworks.algafood.api.mapper.FormaPagamentoResponseMapper;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.model.response.FormaPagamentoResponse;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoResponseMapper formaPagamentoResponseMapper;
	
	@Autowired
	private FormaPagamentoInputMapper formaPagamentoInputMapper;
	
	@GetMapping
	public List<FormaPagamentoResponse> listar() {
		return formaPagamentoResponseMapper.toCollectionResponse(formaPagamentoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public FormaPagamentoResponse buscar(@PathVariable Long id) {
		return formaPagamentoResponseMapper.toResponse(formaPagamentoService.buscarOuFalhar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponse adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputMapper.toDomain(formaPagamentoInput);
		
		return formaPagamentoResponseMapper.toResponse(formaPagamentoService.salvar(formaPagamento));
	}
	
	@PutMapping("/{id}")
	public FormaPagamentoResponse atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(id);
		
		formaPagamentoInputMapper.fromInputToDomain(formaPagamentoInput, formaPagamentoAtual);
		
		return formaPagamentoResponseMapper.toResponse(formaPagamentoService.salvar(formaPagamentoAtual));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		formaPagamentoService.excluir(id);
	}

}
