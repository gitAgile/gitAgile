package com.aerolineasagiles.acceptance.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.aerolineasagiles.controller.CanjeoMillasController;
import com.aerolineasagiles.data.CatalogoDestinoDao;
import com.aerolineasagiles.model.CatalogoDestino;
import com.aerolineasagiles.service.CanjeoPuntosService;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class BuscarDestinosCanjeablesConPuntosAcumuladosSteps {

	@Mock
	private CatalogoDestinoDao catalogoDestinoDao;

	@InjectMocks
	private CanjeoPuntosService canjeoPuntosService;

	@InjectMocks
	private CanjeoMillasController canjeoMillasController;

	private List<CatalogoDestino> todosLosDestinos;

	@Given("puntos acumulados del cliente igual (\\d+)")
	public void unaCedulaDeUnCliente(Long puntosAcumulados) {
		MockitoAnnotations.initMocks(this);
		canjeoMillasController.setPuntosAcumulados(puntosAcumulados);
	}

	@When("se busca opciones de destinos canjeables con (\\d+) puntos")
	public void seBuscaPorCedulaDeCliente(Long puntosAcumulados) {
		Mockito.when(catalogoDestinoDao.obtenerTodos()).thenReturn(getTodosLosDestinos());
		canjeoMillasController.setCanjeoPuntosService(canjeoPuntosService);
		canjeoMillasController.consultarDestinosCanjeables();
	}

	@Then("se obtiene las opciones (\\w+) y (\\w+)")
	public void seObtieneDestinosCanjeables(String opcion1, String opcion2) {
		String[] arregloDestinosCanjeables = { opcion1, opcion2 };
		List<CatalogoDestino> destinosObtenidos = canjeoMillasController.getDestinosCanjeables();

		String[] arregloeDestinosObtenidos = obtenerArregloDeNombresDeDestinos(destinosObtenidos);

		Assert.assertArrayEquals(arregloDestinosCanjeables, arregloeDestinosObtenidos);

	}

	private String[] obtenerArregloDeNombresDeDestinos(List<CatalogoDestino> destinosObtenidos) {
		String[] arregloeDestinosObtenidos = new String[destinosObtenidos.size()];
		int contador = 0;
		for (CatalogoDestino catalogoDestino : destinosObtenidos) {
			arregloeDestinosObtenidos[contador] = catalogoDestino.getNombreDestino();
			contador++;
		}
		return arregloeDestinosObtenidos;
	}

	/**
	 * @return the todosLosDestinos
	 */
	public List<CatalogoDestino> getTodosLosDestinos() {
		todosLosDestinos = new ArrayList<CatalogoDestino>();
		CatalogoDestino destino1 = new CatalogoDestino();
		destino1.setDestinoCanjeable(true);
		destino1.setNombreDestino("Chicago");
		destino1.setEquivalenciaEnPuntos(850L);

		CatalogoDestino destino2 = new CatalogoDestino();
		destino2.setDestinoCanjeable(true);
		destino2.setNombreDestino("Londres");
		destino2.setEquivalenciaEnPuntos(1050L);

		CatalogoDestino destino3 = new CatalogoDestino();
		destino3.setDestinoCanjeable(true);
		destino3.setNombreDestino("Bogota");
		destino3.setEquivalenciaEnPuntos(600L);

		CatalogoDestino destino4 = new CatalogoDestino();
		destino4.setDestinoCanjeable(false);
		destino4.setNombreDestino("Miami");
		destino4.setEquivalenciaEnPuntos(800L);

		todosLosDestinos.add(destino1);
		todosLosDestinos.add(destino2);
		todosLosDestinos.add(destino3);
		todosLosDestinos.add(destino4);
		return todosLosDestinos;
	}

	/**
	 * @param todosLosDestinos
	 *            the todosLosDestinos to set
	 */
	public void setTodosLosDestinos(List<CatalogoDestino> todosLosDestinos) {

		this.todosLosDestinos = todosLosDestinos;
	}

}
