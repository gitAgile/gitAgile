package com.aerolineasagiles.data;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.aerolineasagiles.model.CatalogoDestino;

@Stateless
public class CatalogoDestinoDao extends GenericDaoImpl<CatalogoDestino, Serializable> {

	public CatalogoDestinoDao() {
		super(CatalogoDestino.class);
	}

}
