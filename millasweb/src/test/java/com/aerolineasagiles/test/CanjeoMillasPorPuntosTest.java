package com.aerolineasagiles.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;

import com.aerolineasagiles.data.VueloDao;
import com.aerolineasagiles.model.Vuelo;
import com.aerolineasagiles.service.CanjeoPuntosService;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class CanjeoMillasPorPuntosTest extends UnitilsJUnit4 {

	@Mock
	private VueloDao vueloDao;

	@InjectMocks
	private CanjeoPuntosService canjeoPuntosService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void debeObtenerLosPuntosAcumuladosDeUnCliente() {

		// Arrange
		Long codigoCliente = 5L;
		List<Vuelo> listaVuelosMock = new ArrayList<Vuelo>();
		Vuelo vuelo1 = new Vuelo();
		vuelo1.setPuntosObtenidos(1000L);
		Vuelo vuelo2 = new Vuelo();
		vuelo2.setPuntosObtenidos(1500L);
		listaVuelosMock.add(vuelo1);
		listaVuelosMock.add(vuelo2);
		Mockito.when(vueloDao.consultarVuelosPorCliente(codigoCliente)).thenReturn(listaVuelosMock);
		// Act
		long puntosAcumulados = canjeoPuntosService.obtenerPuntosAcumulados(codigoCliente);
		long puntosAcumuladosEsperados = 2500L;
		// Assert
		Assert.assertEquals(puntosAcumuladosEsperados, puntosAcumulados);

	}

}
