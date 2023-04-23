package com.algaworks.algafood.api.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.model.response.CozinhaResponse;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaResponseMapper extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponse> {
	
	public CozinhaResponseMapper() {
		super(CozinhaController.class, CozinhaResponse.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CozinhaResponse toModel(Cozinha cozinha) {
		CozinhaResponse cozinhaResponse = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaResponse);
		
		cozinhaResponse.add(linkTo(CozinhaController.class).withRel("cozinhas"));
		
		return cozinhaResponse;
	}
	
//	public List<CozinhaResponse> toCollectionResponse(List<Cozinha> cozinhas) {
//		return cozinhas.stream()
//				.map(cozinha -> toResponse(cozinha))
//				.collect(Collectors.toList());
//	}

}
