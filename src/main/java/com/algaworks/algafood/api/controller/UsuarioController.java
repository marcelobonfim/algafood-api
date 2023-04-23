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

import com.algaworks.algafood.api.controller.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.api.mapper.UsuarioInputMapper;
import com.algaworks.algafood.api.mapper.UsuarioResponseMapper;
import com.algaworks.algafood.api.model.input.UsuarioNovoInput;
import com.algaworks.algafood.api.model.input.UsuarioSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioUpdateInput;
import com.algaworks.algafood.api.model.response.UsuarioResponse;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioResponseMapper usuarioResponseMapper;
	
	@Autowired
	private UsuarioInputMapper usuarioInputMapper;

	@GetMapping
	public CollectionModel<UsuarioResponse> listar() {
		return usuarioResponseMapper.toCollectionModel(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public UsuarioResponse buscar(@PathVariable Long id) {
		return usuarioResponseMapper.toModel(usuarioService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponse adicionar(@RequestBody @Valid UsuarioNovoInput usuarioInput) {
		Usuario usuario = usuarioInputMapper.toDomain(usuarioInput);
		
		return usuarioResponseMapper.toModel(usuarioService.salvar(usuario));
	}

	@PutMapping("/{id}")
	public UsuarioResponse atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateInput usuarioInput) {
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(id);
		
		usuarioInputMapper.fromInputToDomain(usuarioInput, usuarioAtual);

		return usuarioResponseMapper.toModel(usuarioService.salvar(usuarioAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		usuarioService.remover(id);
	}
	
	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid UsuarioSenhaInput usuarioSenhaInput) {
		usuarioService.alterarSenha(id, usuarioSenhaInput.getSenhaAtual(), usuarioSenhaInput.getSenhaNova());
	}

}
