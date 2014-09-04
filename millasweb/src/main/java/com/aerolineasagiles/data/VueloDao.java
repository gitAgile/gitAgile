package com.aerolineasagiles.data;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.aerolineasagiles.model.Vuelo;

@Stateless
public class VueloDao extends GenericDaoImpl<Vuelo, Serializable> {

	public VueloDao() {
		super(Vuelo.class);
	}

}
