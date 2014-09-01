package com.aerolineasagiles.model;

public enum EstadoEnum {

	PENDIENTE("Pendiente"), CANCELADO("Cancelado"), CONSUMIDO("Consumido");

	private EstadoEnum(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	private String nombreEstado;

	/**
	 * @return the nombreEstado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}

	/**
	 * @param nombreEstado
	 *            the nombreEstado to set
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

}
