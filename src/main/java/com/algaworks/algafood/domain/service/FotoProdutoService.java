package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.FotoProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.dto.NovaFoto;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class FotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;

	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto, InputStream arquivoFotoInputStream) {
		Long restauranteId = fotoProduto.getRestauranteId();
		Long produtoId = fotoProduto.getProduto().getId();
		String nomeArquivoAntigo = null;

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			nomeArquivoAntigo = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}

		String novoNomeArquivo = fotoStorageService.gerarNovoNomeArquivo(fotoProduto.getNomeArquivo());
		fotoProduto.setNomeArquivo(novoNomeArquivo);

		FotoProduto fotoProdutoSave = produtoRepository.save(fotoProduto);
		produtoRepository.flush();

		NovaFoto novaFoto = NovaFoto.builder()
					.nomeArquivo(novoNomeArquivo)
					.contentType(fotoProdutoSave.getContentType())
					.inputStream(arquivoFotoInputStream)
				.build();

		fotoStorageService.substituir(nomeArquivoAntigo, novaFoto);

		return fotoProdutoSave;
	}

	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(() -> new FotoProdutoNaoEncontradoException(restauranteId, produtoId));
	}
	
	@Transactional
	public void remover(Long restauranteId, Long produtoId) {
		FotoProduto fotoProduto = buscarOuFalhar(restauranteId, produtoId);
				
		produtoRepository.delete(fotoProduto);
		produtoRepository.flush();
		
		fotoStorageService.remover(fotoProduto.getNomeArquivo());
	}

}
