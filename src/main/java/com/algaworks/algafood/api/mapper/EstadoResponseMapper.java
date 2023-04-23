package com.algaworks.algafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.response.EstadoResponse;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoResponseMapper extends RepresentationModelAssemblerSupport<Estado, EstadoResponse> {
	
	public EstadoResponseMapper() {
		super(EstadoController.class, EstadoResponse.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EstadoResponse toModel(Estado estado) {
		EstadoResponse estadoResponse =  createModelWithId(estado.getId(), estado);
		
		modelMapper.map(estado, estadoResponse);
		
		estadoResponse.add(linkTo(methodOn(EstadoController.class)
				.listar()).withRel("estados"));
		
		return estadoResponse;
	}
	
	@Override
	public CollectionModel<EstadoResponse> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(EstadoController.class).withSelfRel());
	}
}
