package com.aerolineasagiles.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.aerolineasagiles.data.VueloDao;
import com.aerolineasagiles.model.Vuelo;

@Stateless
public class CanjeoPuntosService {

	@EJB
	private VueloDao vueloDao;

	public Long obtenerPuntosAcumulados(Long codigoCliente) {
		List<Vuelo> vuelosDeCliente = vueloDao.consultarVuelosPorCliente(codigoCliente);
		long puntosAcumulados = 0;
		if (!vuelosDeCliente.isEmpty()) {
			for (Vuelo vuelo : vuelosDeCliente) {
				puntosAcumulados = puntosAcumulados + vuelo.getPuntosObtenidos();
			}
		}
		return puntosAcumulados;
	}
}
