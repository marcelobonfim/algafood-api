package com.algaworks.algafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.response.FormaPagamentoResponse;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoResponse toResponse(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoResponse.class);
	}
	
	public List<FormaPagamentoResponse> toCollectionResponse(Collection<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream()
				.map(formaPagamento -> toResponse(formaPagamento))
				.collect(Collectors.toList());
	}

}
