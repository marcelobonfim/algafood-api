package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.controller.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.api.mapper.generic.ResponseMapper;
import com.algaworks.algafood.api.model.response.GrupoResponse;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios/{idUsuario}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ResponseMapper<Grupo, GrupoResponse> grupoResponseMapper;
	
	@GetMapping
	public List<GrupoResponse> listar(@PathVariable Long idUsuario) {
		Usuario usuario = usuarioService.buscarOuFalhar(idUsuario);
		
		return grupoResponseMapper.toCollectionResponse(usuario.getGrupos(), GrupoResponse.class);
	}
	
	@PutMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associoarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo) {
		usuarioService.associarGrupo(idUsuario, idGrupo);
	}
	
	@DeleteMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassocioarFormaPagamento(@PathVariable Long idUsuario, @PathVariable Long idGrupo) {
		usuarioService.desAssociarGrupo(idUsuario, idGrupo);
	}

}
