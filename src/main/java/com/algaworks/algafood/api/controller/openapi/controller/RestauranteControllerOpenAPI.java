package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.algaworks.algafood.api.controller.openapi.model.RestauranteBasicoResponseOpenApi;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.response.RestauranteResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenAPI {
	
	@ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoResponseOpenApi.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
					name = "projecao", paramType = "query", type = "string")
	})
	List<RestauranteResponse> listar();
	
	@ApiOperation(value = "Lista restaurantes", hidden = true)
	List<RestauranteResponse> listarApenasNomes();

	@ApiOperation("Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	RestauranteResponse buscar(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id);

	@ApiOperation("Cadastra um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante cadastrado"),
	})
	RestauranteResponse salvar(@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true) RestauranteInput restauranteInput);

	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurante atualizado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	RestauranteResponse atualizar(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id, @ApiParam(name = "corpo", value = "Representação de um restaurante com novos dados", required = true) RestauranteInput restauranteInput);

	@ApiOperation("Atualiza um restaurante parcialmente por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurante atualizado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	RestauranteResponse atualizarParcial(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id, Map<String, Object> campos,
			HttpServletRequest request);

	@ApiOperation("Exclui uma restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante excluído"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id);

	@ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	void ativar(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id);

	@ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	void inativar(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id);

	@ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	void abrir(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id);

	@ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	void fechar(@ApiParam(value = "Id do restaurante", example = "1", required = true) Long id);

	@ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
	void ativarMultiplos( @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);

	@ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
	void inativarMultiplos( @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);

}
