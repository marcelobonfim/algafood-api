package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired 
	private PermissaoService permissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}
	}
	
	@Transactional
	public void associarPermissao(Long idGrupo, Long idPermissao) {
		Grupo grupo = buscarOuFalhar(idGrupo);
		Permissao permissao = permissaoService.buscarOuFalhar(idPermissao);
		
		grupo.adicionarPermissao(permissao);
	}
	
	@Transactional
	public void desAssociarPermissao(Long idGrupo, Long idPermissao) {
		Grupo grupo = buscarOuFalhar(idGrupo);
		Permissao permissao = permissaoService.buscarOuFalhar(idPermissao);
		
		grupo.removerPermissao(permissao);
	}

	public Grupo buscarOuFalhar(Long id) {
		return grupoRepository.findById(id)
				.orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}
}
