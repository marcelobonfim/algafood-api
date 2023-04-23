package com.algaworks.algafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.response.CidadeResponse;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeResponseMapper extends RepresentationModelAssemblerSupport<Cidade, CidadeResponse> {
	
	public CidadeResponseMapper() {
		super(CidadeController.class, CidadeResponse.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CidadeResponse toModel(Cidade cidade) {
		CidadeResponse cidadeModel = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, cidadeModel);

		cidadeModel.add(linkTo(methodOn(CidadeController.class)
				.listar()).withRel("cidades"));

		cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class)
				.buscar(cidadeModel.getEstado().getId())).withSelfRel());

		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeResponse> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(CidadeController.class).withSelfRel());
	}
}
