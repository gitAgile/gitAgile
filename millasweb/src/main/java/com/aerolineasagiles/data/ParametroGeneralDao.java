package com.aerolineasagiles.data;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.aerolineasagiles.model.ParametroGeneral;

@Stateless
public class ParametroGeneralDao extends GenericDaoImpl<ParametroGeneral, Serializable> {

	public ParametroGeneralDao() {
		super(ParametroGeneral.class);
	}

}
