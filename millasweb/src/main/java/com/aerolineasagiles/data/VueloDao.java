package com.aerolineasagiles.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.aerolineasagiles.model.Vuelo;

@Stateless
public class VueloDao extends GenericDaoImpl<Vuelo, Serializable> {

	public VueloDao() {
		super(Vuelo.class);
	}

	@SuppressWarnings("unchecked")
	public List<Vuelo> consultarVuelosPorCliente(Long codigoCliente) {
		Query query = em.createQuery("Select v from Vuelo v where v.cliente.codigoCliente=:codigoCliente");
		query.setParameter("codigoCliente", codigoCliente);
		return query.getResultList();
	}

}
