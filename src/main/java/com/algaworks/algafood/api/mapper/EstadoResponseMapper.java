package com.algaworks.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.response.EstadoResponse;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoResponseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoResponse toResponse(Estado estado) {
		return modelMapper.map(estado, EstadoResponse.class);
	}
	
	public List<EstadoResponse> toCollectionResponse(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toResponse(estado))
				.collect(Collectors.toList());
	}

}
