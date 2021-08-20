package com.algaworks.algafood.api.mapper.generic;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper<E, R> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public R toResponse(E entity, Class<R> type) {
		return modelMapper.map(entity, type);
	}
	
	public List<R> toCollectionResponse(Collection<E> entities, Class<R> type) {
		return entities.stream()
				.map(entity -> toResponse(entity, type))
				.collect(Collectors.toList());
	}

}
