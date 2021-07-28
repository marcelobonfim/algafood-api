package com.algaworks.algafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomain(EstadoInput estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}

	public void fromInputToDomain(EstadoInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}

}
