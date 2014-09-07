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

import com.aerolineasagiles.data.ClienteDao;
import com.aerolineasagiles.data.VueloDao;
import com.aerolineasagiles.exception.NoPuntosAcumuladosException;
import com.aerolineasagiles.model.Cliente;
import com.aerolineasagiles.model.EstadoEnum;
import com.aerolineasagiles.model.Vuelo;
import com.aerolineasagiles.service.CanjeoPuntosService;

public class CanjeoMillasPorPuntosTest {

	@Mock
	private ClienteDao clienteDao;

	@Mock
	private VueloDao vueloDao;

	@InjectMocks
	private CanjeoPuntosService canjeoPuntosService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void debeObtenerUnMensajeCuandoNoSeTienePuntosAcumulados() {

		String cedulaCliente = "1712345678";
		Cliente cliente = new Cliente();
		cliente.setCodigoCliente(5L);
		cliente.setCedula(cedulaCliente);

		Long codigoCliente = 5L;
		List<Vuelo> listaVuelosMock = new ArrayList<Vuelo>();

		Mockito.when(clienteDao.buscarClientePorCedula(cedulaCliente)).thenReturn(cliente);
		Mockito.when(vueloDao.consultarVuelosPorCliente(codigoCliente)).thenReturn(listaVuelosMock);
		try {
			canjeoPuntosService.obtenerPuntosAcumulados(cedulaCliente);
		} catch (NoPuntosAcumuladosException e) {
			Assert.assertEquals("No existen puntos acumulados.", e.getMessage());
		}

	}

	@Test(expected = NoPuntosAcumuladosException.class)
	public void debeLanzarExcepcionCuandoNoSeTienePuntosAcumulados() throws NoPuntosAcumuladosException {

		String cedulaCliente = "1712345678";
		Cliente cliente = new Cliente();
		cliente.setCodigoCliente(5L);
		cliente.setCedula(cedulaCliente);

		Long codigoCliente = 5L;
		List<Vuelo> listaVuelosMock = new ArrayList<Vuelo>();

		Mockito.when(clienteDao.buscarClientePorCedula(cedulaCliente)).thenReturn(cliente);
		Mockito.when(vueloDao.consultarVuelosPorCliente(codigoCliente)).thenReturn(listaVuelosMock);
		canjeoPuntosService.obtenerPuntosAcumulados(cedulaCliente);

	}

	@Test
	public void debeObtenerLosPuntosAcumuladosDeUnClienteSoloDeViajesConsumidos() throws NoPuntosAcumuladosException {

		String cedulaCliente = "1712345678";
		Cliente cliente = new Cliente();
		cliente.setCodigoCliente(5L);
		cliente.setCedula(cedulaCliente);

		List<Vuelo> listaVuelosMock = new ArrayList<Vuelo>();
		Vuelo vuelo1 = new Vuelo();
		vuelo1.setPuntosObtenidos(1000L);
		vuelo1.setEstado(EstadoEnum.CONSUMIDO);
		Vuelo vuelo2 = new Vuelo();
		vuelo2.setPuntosObtenidos(1500L);
		vuelo2.setEstado(EstadoEnum.CANCELADO);
		listaVuelosMock.add(vuelo1);
		listaVuelosMock.add(vuelo2);

		Mockito.when(clienteDao.buscarClientePorCedula(cedulaCliente)).thenReturn(cliente);
		Mockito.when(vueloDao.consultarVuelosPorCliente(cliente.getCodigoCliente())).thenReturn(listaVuelosMock);
		// Act
		long puntosAcumulados = canjeoPuntosService.obtenerPuntosAcumulados(cedulaCliente);
		long puntosAcumuladosEsperados = 1000l;
		// Assert
		Assert.assertEquals(puntosAcumuladosEsperados, puntosAcumulados);

	}

	// Esta prueba ya no aporta valor, se la va a ignorar. Para ellos se utiliza
	// la anotacion @Ignore
	@Ignore
	@Test
	public void debeObtenerLosPuntosAcumuladosDeUnCliente() throws NoPuntosAcumuladosException {

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
		long puntosAcumulados = canjeoPuntosService.obtenerPuntosAcumulados(null);
		long puntosAcumuladosEsperados = 2500L;
		// Assert
		Assert.assertEquals(puntosAcumuladosEsperados, puntosAcumulados);

	}

}
