package com.aerolineasagiles.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;

import com.aerolineasagiles.service.CanjeoPuntosService;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class CanjeoMillasPorPuntosTest extends UnitilsJUnit4 {

	@Mock
	private CanjeoPuntosService canjeoPuntosService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void debeObtenerLosPuntosAcumuladosDeUnCliente() {

		Long codigoCliente = 5L;
		Long puntosAcumuladsos = 2500L;
		Mockito.when(canjeoPuntosService.obtenerPuntosAcumulados(Mockito.anyLong())).thenReturn(puntosAcumuladsos);
		long puntosAcumulados = canjeoPuntosService.obtenerPuntosAcumulados(codigoCliente);
		long puntosAcumuladosEsperados = 2500L;
		Assert.assertEquals(puntosAcumuladosEsperados, puntosAcumulados);

	}

}
