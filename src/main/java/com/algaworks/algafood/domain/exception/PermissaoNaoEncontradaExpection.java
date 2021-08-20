package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradaExpection extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaExpection(String mensagem) {
		super(mensagem);
	}

	public PermissaoNaoEncontradaExpection(Long id) {
		this(String.format("Não existe um cadastro de permissão com código %d", id));
	}

}
