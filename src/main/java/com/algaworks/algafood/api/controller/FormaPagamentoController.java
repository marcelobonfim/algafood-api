package com.algaworks.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algaworks.algafood.api.controller.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.api.mapper.FormaPagamentoInputMapper;
import com.algaworks.algafood.api.mapper.FormaPagamentoResponseMapper;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.model.response.FormaPagamentoResponse;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;

import io.swagger.annotations.Api;

@Api(tags = "Formas de pagamento")
@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private FormaPagamentoResponseMapper formaPagamentoResponseMapper;

	@Autowired
	private FormaPagamentoInputMapper formaPagamentoInputMapper;

	@GetMapping
	public ResponseEntity<List<FormaPagamentoResponse>> listar(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}

		if (request.checkNotModified(eTag)) {
			return null;
		}

		List<FormaPagamentoResponse> formasPagamentosResponse = formaPagamentoResponseMapper
				.toCollectionResponse(formaPagamentoRepository.findAll());

		return ResponseEntity.ok()
//					.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//					.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//					.cacheControl(CacheControl.noCache())
//					.cacheControl(CacheControl.noStore())
				.eTag(eTag).body(formasPagamentosResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamentoResponse> buscar(@PathVariable Long id, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacaoById(id);

		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}

		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		FormaPagamentoResponse formaPagamentoResponse = formaPagamentoResponseMapper
				.toResponse(formaPagamentoService.buscarOuFalhar(id));

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)).eTag(eTag).body(formaPagamentoResponse);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponse adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputMapper.toDomain(formaPagamentoInput);

		return formaPagamentoResponseMapper.toResponse(formaPagamentoService.salvar(formaPagamento));
	}

	@PutMapping("/{id}")
	public FormaPagamentoResponse atualizar(@PathVariable Long id,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(id);

		formaPagamentoInputMapper.fromInputToDomain(formaPagamentoInput, formaPagamentoAtual);

		return formaPagamentoResponseMapper.toResponse(formaPagamentoService.salvar(formaPagamentoAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		formaPagamentoService.excluir(id);
	}

}
