package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			estadoRepository.deleteById(id);
			estadoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
		}
	}

	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}
}
