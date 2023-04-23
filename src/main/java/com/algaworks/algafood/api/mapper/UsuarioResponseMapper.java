package com.algaworks.algafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.model.response.UsuarioResponse;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioResponseMapper extends RepresentationModelAssemblerSupport<Usuario, UsuarioResponse> {
	
	public UsuarioResponseMapper() {
		super(UsuarioController.class, UsuarioResponse.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UsuarioResponse toModel(Usuario usuario) {
		UsuarioResponse usuarioResponse = createModelWithId(usuario.getId(), usuario);
		
		modelMapper.map(usuario, usuarioResponse);
		
		usuarioResponse.add(linkTo(methodOn(UsuarioController.class)
				.listar()).withRel("usuarios"));

		usuarioResponse.add(linkTo(methodOn(UsuarioGrupoController.class)
				.listar(usuarioResponse.getId())).withRel("grupos-usuarios"));
		
		return usuarioResponse;
	}
	
	@Override
	public CollectionModel<UsuarioResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(UsuarioController.class).withSelfRel());
	}
}
