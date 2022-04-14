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

import com.algaworks.algafood.api.controller.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.api.mapper.generic.ResponseMapper;
import com.algaworks.algafood.api.model.response.PermissaoResponse;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos/{idGrupo}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private ResponseMapper<Permissao, PermissaoResponse> permissaoResponseMapper;
	
	@GetMapping
	public List<PermissaoResponse> listar(@PathVariable Long idGrupo) {
		Grupo grupo = grupoService.buscarOuFalhar(idGrupo);
		
		return permissaoResponseMapper.toCollectionResponse(grupo.getPermissoes(), PermissaoResponse.class);
	}
	
	@PutMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associoarFormaPagamento(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
		grupoService.associarPermissao(idGrupo, idPermissao);
	}
	
	@DeleteMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassocioarFormaPagamento(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
		grupoService.desAssociarPermissao(idGrupo, idPermissao);
	}

}
