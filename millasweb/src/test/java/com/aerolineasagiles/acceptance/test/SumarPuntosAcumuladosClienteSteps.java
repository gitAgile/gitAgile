package com.aerolineasagiles.acceptance.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.openejb.api.LocalClient;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.aerolineasagiles.controller.CanjeoMillasController;
import com.aerolineasagiles.data.ClienteDao;
import com.aerolineasagiles.data.VueloDao;
import com.aerolineasagiles.model.Cliente;
import com.aerolineasagiles.model.EstadoEnum;
import com.aerolineasagiles.model.Vuelo;
import com.aerolineasagiles.service.CanjeoPuntosService;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

@LocalClient
public class SumarPuntosAcumuladosClienteSteps {

	@Mock
	private ClienteDao clienteDao;

	@Mock
	private VueloDao vueloDao;

	@InjectMocks
	private CanjeoPuntosService canjeoPuntosService;

	@InjectMocks
	private CanjeoMillasController canjeoMillasController;

	private Cliente cliente;

	private List<Vuelo> vuelosCliente;

	@Given("una cedula de un cliente (\\w+)")
	public void unaCedulaDeUnCliente(String cedula) {
		cliente = new Cliente();
		cliente.setCodigoCliente(1l);
		cliente.setCedula(cedula);

		assertEquals("1721220000", cliente.getCedula());
	}

	@When("se busca por esa cedula (\\w+)")
	public void seBuscaPorCedulaDeCliente(String cedula) {
		MockitoAnnotations.initMocks(this);
		Mockito.when(clienteDao.buscarClientePorCedula(cedula)).thenReturn(cliente);
		Mockito.when(vueloDao.consultarVuelosPorCliente(cliente.getCodigoCliente())).thenReturn(getVuelosCliente());
		canjeoMillasController.setCanjeoPuntosService(canjeoPuntosService);
		canjeoMillasController.setCedulaCliente(cedula);
		canjeoMillasController.consultarPuntosAcumuladosPorCliente();
	}

	@Then("se obtiene (\\d+) como cantidad de puntos acumulados")
	public void seObtieneVuelosCorrespondientesACliente(Long puntosAcumuladosEsperado) {
		assertEquals(puntosAcumuladosEsperado, canjeoMillasController.getPuntosAcumulados());

	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the vuelosCliente
	 */
	public List<Vuelo> getVuelosCliente() {
		if (vuelosCliente == null) {
			crearListaVuelosDeCliente();
		}
		return vuelosCliente;
	}

	private void crearListaVuelosDeCliente() {
		vuelosCliente = new ArrayList<Vuelo>();
		Vuelo vuelo = new Vuelo();
		vuelo.setPuntosObtenidos(200L);
		vuelo.setEstado(EstadoEnum.CONSUMIDO);
		Vuelo vuelo2 = new Vuelo();
		vuelo2.setPuntosObtenidos(300L);
		vuelo2.setEstado(EstadoEnum.CONSUMIDO);
		vuelosCliente.add(vuelo);
		vuelosCliente.add(vuelo2);
	}

	/**
	 * @param vuelosCliente
	 *            the vuelosCliente to set
	 */
	public void setVuelosCliente(List<Vuelo> vuelosCliente) {
		this.vuelosCliente = vuelosCliente;
	}

}
