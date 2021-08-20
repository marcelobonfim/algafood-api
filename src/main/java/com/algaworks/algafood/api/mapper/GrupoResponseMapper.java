package com.algaworks.algafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.response.GrupoResponse;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoResponseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoResponse toResponse(Grupo grupo) {
		return modelMapper.map(grupo, GrupoResponse.class);
	}
	
	public List<GrupoResponse> toCollectionResponse(Collection<Grupo> grupos) {
		return grupos.stream()
				.map(grupo -> toResponse(grupo))
				.collect(Collectors.toList());
	}

}
