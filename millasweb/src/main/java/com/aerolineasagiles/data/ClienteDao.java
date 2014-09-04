package com.aerolineasagiles.data;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.aerolineasagiles.model.Cliente;

@Stateless
public class ClienteDao extends GenericDaoImpl<Cliente, Serializable> {

	public ClienteDao() {
		super(Cliente.class);
	}

}
