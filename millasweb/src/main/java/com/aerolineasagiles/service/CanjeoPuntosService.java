package com.aerolineasagiles.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.aerolineasagiles.data.CatalogoDestinoDao;
import com.aerolineasagiles.data.ClienteDao;
import com.aerolineasagiles.data.VueloDao;
import com.aerolineasagiles.exception.DestinosExcedenPuntosException;
import com.aerolineasagiles.exception.NoPuntosAcumuladosException;
import com.aerolineasagiles.exception.PuntosNoSuficientesException;
import com.aerolineasagiles.model.CatalogoDestino;
import com.aerolineasagiles.model.Cliente;
import com.aerolineasagiles.model.EstadoEnum;
import com.aerolineasagiles.model.Vuelo;

@Stateless
public class CanjeoPuntosService {

	@EJB
	private VueloDao vueloDao;

	@EJB
	private CatalogoDestinoDao catalogoDestinoDao;

	@EJB
	private ClienteDao clienteDao;

	public Cliente buscarClientePorCedula(String cedula) {
		return clienteDao.buscarClientePorCedula(cedula);
	}

	public Long obtenerPuntosAcumulados(String cedulaCliente) throws NoPuntosAcumuladosException {
		Cliente cliente = buscarClientePorCedula(cedulaCliente);
		return contarPuntosAcumulados(cliente.getCodigoCliente());
	}

	private Long contarPuntosAcumulados(Long codigoCliente) throws NoPuntosAcumuladosException {
		List<Vuelo> vuelosDeCliente = vueloDao.consultarVuelosPorCliente(codigoCliente);
		long puntosAcumulados = 0;
		if (!vuelosDeCliente.isEmpty()) {
			for (Vuelo vuelo : vuelosDeCliente) {
				if (EstadoEnum.CONSUMIDO.equals(vuelo.getEstado())) {
					puntosAcumulados = puntosAcumulados + vuelo.getPuntosObtenidos();
				}
			}
		} else {
			throw new NoPuntosAcumuladosException("No existen puntos acumulados.");
		}
		return puntosAcumulados;
	}

	public List<CatalogoDestino> obtenerDestinosCanjeablesPorPuntosAcumulados(Long puntosAcumulados) throws PuntosNoSuficientesException {
		List<CatalogoDestino> destinos = catalogoDestinoDao.obtenerTodos();
		List<CatalogoDestino> destinosCanjeables = new ArrayList<CatalogoDestino>();
		for (CatalogoDestino destino : destinos) {
			if (destino.getEquivalenciaEnPuntos() <= puntosAcumulados && destino.isDestinoCanjeable()) {
				destinosCanjeables.add(destino);
			}
		}
		if (destinosCanjeables.isEmpty()) {
			throw new PuntosNoSuficientesException("Sus puntos son insuficientes para canjear.");
		}
		return destinosCanjeables;
	}

	public Long canjearDestinosConPuntosAcumulados(List<CatalogoDestino> destinosSeleccionados, Long puntosAcumulados)
			throws DestinosExcedenPuntosException {
		Long puntosDeDestinos = 0L;
		for (CatalogoDestino destino : destinosSeleccionados) {
			puntosDeDestinos += destino.getEquivalenciaEnPuntos();
		}
		if (puntosAcumulados < puntosDeDestinos) {
			throw new DestinosExcedenPuntosException("Los suma de puntos de los destinos escogido exceden el de los puntos acumulados.");
		}
		return puntosAcumulados - puntosDeDestinos;

	}

	public CatalogoDestino obtenerDestinoPorCodigo(Long codigo) {
		return catalogoDestinoDao.obtenerPorCodigo(codigo);
	}
}
