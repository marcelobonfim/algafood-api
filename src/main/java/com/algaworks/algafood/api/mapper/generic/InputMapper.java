package com.algaworks.algafood.api.mapper.generic;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputMapper<I, D> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public D toDomain(I Input, Class<D> type) {
		return modelMapper.map(Input, type);
	}

	public void fromInputToDomain(I Input, D type) {
		modelMapper.map(Input, type);
	}

}
