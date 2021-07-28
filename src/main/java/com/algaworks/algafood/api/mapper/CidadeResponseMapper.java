package com.algaworks.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.response.CidadeResponse;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeResponseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeResponse toResponse(Cidade cidade) {
		return modelMapper.map(cidade, CidadeResponse.class);
	}
	
	public List<CidadeResponse> toCollectionResponse(List<Cidade> cidades) {
		return cidades.stream()
				.map(cidade -> toResponse(cidade))
				.collect(Collectors.toList());
	}
}
