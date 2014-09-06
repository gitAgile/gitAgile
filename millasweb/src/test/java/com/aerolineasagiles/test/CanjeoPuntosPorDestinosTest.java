package com.aerolineasagiles.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.aerolineasagiles.data.CatalogoDestinoDao;
import com.aerolineasagiles.exception.DestinosExcedenPuntosException;
import com.aerolineasagiles.exception.PuntosNoSuficientesException;
import com.aerolineasagiles.model.CatalogoDestino;
import com.aerolineasagiles.service.CanjeoPuntosService;

public class CanjeoPuntosPorDestinosTest {

	@Mock
	private CatalogoDestinoDao catalogoDestinoDao;

	@InjectMocks
	private CanjeoPuntosService canjeoPuntosService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void debeActualizarPuntosAcumuladosCuandoCanjeaDestinos() throws DestinosExcedenPuntosException {
		Long puntosAcumulados = 7500L;
		List<CatalogoDestino> destinosSeleccionados = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		catalogDestino1.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		catalogDestino2.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino2);

		Long saldoDePuntos = canjeoPuntosService.canjearDestinosConPuntosAcumulados(destinosSeleccionados,
				puntosAcumulados);
		Long saldoDePuntosEsperado = 2500L;
		Assert.assertEquals(saldoDePuntosEsperado, saldoDePuntos);

	}

	@Test
	public void debeObtenerMensajeCuandoDestinosSuperanAlcanceDePuntos() {
		Long puntosAcumulados = 7500L;
		List<CatalogoDestino> destinosSeleccionados = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		catalogDestino1.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		catalogDestino2.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino2);

		CatalogoDestino catalogDestino4 = new CatalogoDestino();
		catalogDestino4.setEquivalenciaEnPuntos(2501l);
		catalogDestino4.setNombreDestino("Rio de Janeiro");
		catalogDestino4.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino4);

		try {
			canjeoPuntosService.canjearDestinosConPuntosAcumulados(destinosSeleccionados, puntosAcumulados);
		} catch (DestinosExcedenPuntosException e) {
			Assert.assertEquals("Los suma de puntos de los destinos escogido exceden el de los puntos acumulados.",
					e.getMessage());
		}
	}

	@Test(expected = DestinosExcedenPuntosException.class)
	public void debeLanzarExcepcionCuandoDestinosSuperanAlcanceDePuntos() throws DestinosExcedenPuntosException {
		Long puntosAcumulados = 7500L;
		List<CatalogoDestino> destinosSeleccionados = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		catalogDestino1.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		catalogDestino2.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino2);

		CatalogoDestino catalogDestino4 = new CatalogoDestino();
		catalogDestino4.setEquivalenciaEnPuntos(2501l);
		catalogDestino4.setNombreDestino("Rio de Janeiro");
		catalogDestino4.setDestinoCanjeable(true);
		destinosSeleccionados.add(catalogDestino4);

		canjeoPuntosService.canjearDestinosConPuntosAcumulados(destinosSeleccionados, puntosAcumulados);

	}

	@Test
	public void debeObtenerMensajeCuandoNoAlcanzanLosPuntosAcumuladosParaUnDestino() {

		Long puntosAcumulados = 1000L;

		List<CatalogoDestino> destinos = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		catalogDestino1.setDestinoCanjeable(true);
		destinos.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		catalogDestino2.setDestinoCanjeable(true);
		destinos.add(catalogDestino2);

		CatalogoDestino catalogDestino3 = new CatalogoDestino();
		catalogDestino3.setEquivalenciaEnPuntos(1800l);
		catalogDestino3.setNombreDestino("Texas");
		catalogDestino3.setDestinoCanjeable(false);
		destinos.add(catalogDestino3);

		CatalogoDestino catalogDestino4 = new CatalogoDestino();
		catalogDestino4.setEquivalenciaEnPuntos(2501l);
		catalogDestino4.setNombreDestino("Rio de Janeiro");
		catalogDestino4.setDestinoCanjeable(true);
		destinos.add(catalogDestino4);

		Mockito.when(catalogoDestinoDao.obtenerTodos()).thenReturn(destinos);

		try {
			canjeoPuntosService.obtenerDestinosCanjeablesPorPuntosAcumulados(puntosAcumulados);
		} catch (PuntosNoSuficientesException e) {
			Assert.assertEquals("Sus puntos son insuficientes para canjear.", e.getMessage());
		}

	}

	@Test(expected = PuntosNoSuficientesException.class)
	public void debeLanzarExcepcionCuandoNoAlcanzanLosPuntosAcumuladosParaUnDestino()
			throws PuntosNoSuficientesException {

		Long puntosAcumulados = 1000L;

		List<CatalogoDestino> destinos = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		catalogDestino1.setDestinoCanjeable(true);
		destinos.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		catalogDestino2.setDestinoCanjeable(true);
		destinos.add(catalogDestino2);

		CatalogoDestino catalogDestino3 = new CatalogoDestino();
		catalogDestino3.setEquivalenciaEnPuntos(1800l);
		catalogDestino3.setNombreDestino("Texas");
		catalogDestino3.setDestinoCanjeable(false);
		destinos.add(catalogDestino3);

		CatalogoDestino catalogDestino4 = new CatalogoDestino();
		catalogDestino4.setEquivalenciaEnPuntos(2501l);
		catalogDestino4.setNombreDestino("Rio de Janeiro");
		catalogDestino4.setDestinoCanjeable(true);
		destinos.add(catalogDestino4);

		Mockito.when(catalogoDestinoDao.obtenerTodos()).thenReturn(destinos);

		canjeoPuntosService.obtenerDestinosCanjeablesPorPuntosAcumulados(puntosAcumulados);

	}

	@Test
	public void debeObtenerDestinosCanjeablesYCubiertosPorLosPuntosAcumulados() throws PuntosNoSuficientesException {
		// Arrange
		Long puntosAcumulados = 2500L;

		List<CatalogoDestino> destinos = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		catalogDestino1.setDestinoCanjeable(true);
		destinos.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		catalogDestino2.setDestinoCanjeable(true);
		destinos.add(catalogDestino2);

		CatalogoDestino catalogDestino3 = new CatalogoDestino();
		catalogDestino3.setEquivalenciaEnPuntos(1800l);
		catalogDestino3.setNombreDestino("Texas");
		catalogDestino3.setDestinoCanjeable(false);
		destinos.add(catalogDestino3);

		CatalogoDestino catalogDestino4 = new CatalogoDestino();
		catalogDestino4.setEquivalenciaEnPuntos(2501l);
		catalogDestino4.setNombreDestino("Rio de Janeiro");
		catalogDestino4.setDestinoCanjeable(true);
		destinos.add(catalogDestino4);

		Mockito.when(catalogoDestinoDao.obtenerTodos()).thenReturn(destinos);

		// Act
		List<CatalogoDestino> destinosCanjeables = canjeoPuntosService
				.obtenerDestinosCanjeablesPorPuntosAcumulados(puntosAcumulados);
		// Assert
		CatalogoDestino[] expecteds = { catalogDestino1 };
		Assert.assertArrayEquals(expecteds, destinosCanjeables.toArray());
	}

	// Este test ya no aporta valor por lo que se lo ignora
	@Ignore
	@Test
	public void debeObtenerDestinosCubiertosPorLosPuntosAcumulados() throws PuntosNoSuficientesException {

		// Arrange
		Long puntosAcumulados = 2500L;

		List<CatalogoDestino> destinos = new ArrayList<CatalogoDestino>();
		CatalogoDestino catalogDestino1 = new CatalogoDestino();
		catalogDestino1.setEquivalenciaEnPuntos(2000L);
		catalogDestino1.setNombreDestino("Miami");
		destinos.add(catalogDestino1);

		CatalogoDestino catalogDestino2 = new CatalogoDestino();
		catalogDestino2.setEquivalenciaEnPuntos(3000l);
		catalogDestino2.setNombreDestino("New York");
		destinos.add(catalogDestino2);

		CatalogoDestino catalogDestino3 = new CatalogoDestino();
		catalogDestino3.setEquivalenciaEnPuntos(1800l);
		catalogDestino3.setNombreDestino("Texas");
		destinos.add(catalogDestino3);

		CatalogoDestino catalogDestino4 = new CatalogoDestino();
		catalogDestino4.setEquivalenciaEnPuntos(2501l);
		catalogDestino4.setNombreDestino("Rio de Janeiro");
		destinos.add(catalogDestino4);

		Mockito.when(catalogoDestinoDao.obtenerTodos()).thenReturn(destinos);

		// Act
		List<CatalogoDestino> destinosCanjeables = canjeoPuntosService
				.obtenerDestinosCanjeablesPorPuntosAcumulados(puntosAcumulados);
		// Assert
		CatalogoDestino[] expecteds = { catalogDestino1, catalogDestino3 };
		Assert.assertArrayEquals(expecteds, destinosCanjeables.toArray());

	}

}
