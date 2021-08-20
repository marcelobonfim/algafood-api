package com.algaworks.algafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.UsuarioUpdateInput;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomain(UsuarioUpdateInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public void fromInputToDomain(UsuarioUpdateInput usuarioInput, Usuario grupo) {
		modelMapper.map(usuarioInput, grupo);
	}

}
