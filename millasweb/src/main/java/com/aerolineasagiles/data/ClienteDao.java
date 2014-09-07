package com.aerolineasagiles.data;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.aerolineasagiles.model.Cliente;

@Stateless
public class ClienteDao extends GenericDaoImpl<Cliente, Serializable> {

	public ClienteDao() {
		super(Cliente.class);
	}

	public Cliente buscarClientePorCedula(String cedula) {
		Query query = em.createQuery("Select c from Cliente c where c.cedula=:cedula");
		query.setParameter("cedula", cedula);
		return (Cliente) query.getSingleResult();
	}

}
