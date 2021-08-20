package com.algaworks.algafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomain(GrupoInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}

	public void fromInputToDomain(GrupoInput grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}

}
