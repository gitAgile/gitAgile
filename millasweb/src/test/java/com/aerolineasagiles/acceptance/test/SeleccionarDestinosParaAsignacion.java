package com.aerolineasagiles.acceptance.test;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.aerolineasagiles.controller.CanjeoMillasController;
import com.aerolineasagiles.data.CatalogoDestinoDao;
import com.aerolineasagiles.model.CatalogoDestino;
import com.aerolineasagiles.service.CanjeoPuntosService;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FacesContext.class })
public class SeleccionarDestinosParaAsignacion {
	@Mock
	private CatalogoDestinoDao catalogoDestinoDao;

	@InjectMocks
	private CanjeoPuntosService canjeoPuntosService;

	@InjectMocks
	private CanjeoMillasController canjeoMillasController;

	private List<CatalogoDestino> todosLosDestinos;

	private Long[] destinosSeleccionados;

	@Given("Los destinos seleccionados son Chicago y Bogota cuyos codigos son (\\d+) y (\\d+) respectivamente.")
	public void losDestinosSeleccionadosChicagoYBogota(Long codigoDestino1, Long codigoDestino2) {
		MockitoAnnotations.initMocks(this);
		destinosSeleccionados = new Long[] { codigoDestino1, codigoDestino2 };
		canjeoMillasController.setDestinosSeleccionados(destinosSeleccionados);
	}

	@When("el cliente utiliza sus (\\d+) puntos para canjear los destinos")
	public void elClienteUtilizaPuntosAcumuladosParaCanjearDestinos(Long puntosAcumulados) throws Exception {
		CatalogoDestino destino1 = new CatalogoDestino();
		destino1.setCodigoCatalogoDestino(destinosSeleccionados[0]);
		destino1.setEquivalenciaEnPuntos(500L);

		CatalogoDestino destino2 = new CatalogoDestino();
		destino2.setCodigoCatalogoDestino(destinosSeleccionados[1]);
		destino2.setEquivalenciaEnPuntos(350L);

		Mockito.when(catalogoDestinoDao.obtenerPorCodigo(destinosSeleccionados[0])).thenReturn(destino1);
		Mockito.when(catalogoDestinoDao.obtenerPorCodigo(destinosSeleccionados[1])).thenReturn(destino2);

		canjeoMillasController.setPuntosAcumulados(puntosAcumulados);
		canjeoMillasController.setCanjeoPuntosService(canjeoPuntosService);
		canjeoMillasController.asignarDestinosSeleccionados();
	}

	@Then("los puntos acumulados del cliente se actualizan a (\\d+)")
	public void seActualizanLaCantidadDePuntosAcumuladosDelCliente(Long puntosActualizadosEsperado) {
		Assert.assertEquals(puntosActualizadosEsperado, canjeoMillasController.getPuntosAcumulados());
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
