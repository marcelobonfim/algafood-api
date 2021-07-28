package com.algaworks.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.response.RestauranteResponse;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteResponseMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteResponse toResponse(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteResponse.class);
	}
	
	public List<RestauranteResponse> toCollectionResponse(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toResponse(restaurante))
				.collect(Collectors.toList());
	}

}
