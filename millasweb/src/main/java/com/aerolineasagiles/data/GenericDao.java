package com.aerolineasagiles.data;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, I extends Serializable> {

	T obtenerPorCodigo(I codigo);

	List<T> obtenerTodos();

}
