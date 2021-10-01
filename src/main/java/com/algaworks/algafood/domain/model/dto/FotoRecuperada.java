package com.algaworks.algafood.domain.model.dto;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FotoRecuperada {
	
	private InputStream inputStream;
	private String url;
	
	public boolean temUrl() {
		return url != null;
	}
	
	public boolean temInputStream() {
		return inputStream != null;
	}
	
}
