package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";


	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			restauranteRepository.deleteById(id);
			restauranteRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO, id));
		}
	}
	
	@Transactional
	public void ativar(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);
		
		restaurante.ativar();
	}
	
	@Transactional
	public void inativar(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);
		
		restaurante.inativar();
	}
	
	@Transactional
	public void associarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {
		Restaurante restaurante = buscarOuFalhar(idRestaurante);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);
		
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {
		Restaurante restaurante = buscarOuFalhar(idRestaurante);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);
		
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void fechar(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);
		
		restaurante.fechar();
	}
	
	@Transactional
	public void abrir(Long id) {
		Restaurante restaurante = buscarOuFalhar(id);
		
		restaurante.abrir();
	}
	
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}
	
	@Transactional
	public void associarResponsavel(Long idRestaurante, Long idResponsavel) {
		Restaurante restaurante = buscarOuFalhar(idRestaurante);
		Usuario responsavel = usuarioService.buscarOuFalhar(idResponsavel);
		
		restaurante.adicionarResponsavel(responsavel);
	}
	
	@Transactional
	public void desAssociarResponsavel(Long idRestaurante, Long idResponsavel) {
		Restaurante restaurante = buscarOuFalhar(idRestaurante);
		Usuario responsavel = usuarioService.buscarOuFalhar(idResponsavel);
		
		restaurante.removerResponsavel(responsavel);
	}
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}

}
