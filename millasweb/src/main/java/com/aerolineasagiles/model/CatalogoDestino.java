package com.aerolineasagiles.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CATALOGO_DESTINO")
@Entity
public class CatalogoDestino implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3681375339266015753L;

	@Id
	@Column(name = "COD_CATALOGO_DESTINO")
	private Long codigoCatalogoDestino;

	@Column(name = "NOMBRE_DESTINO")
	private String nombreDestino;

	@Column(name = "DISTANCIA_EN_MILLAS")
	private Double distanciaEnMillas;

	@Column(name = "EQUIVALENCIA_EN_PUNTOS")
	private Long equivalenciaEnPuntos;

	@Column(name = "DESTINO_CANJEABLE")
	private boolean destinoCanjeable;

	/**
	 * @return the codigoCatalogoDestino
	 */
	public Long getCodigoCatalogoDestino() {
		return codigoCatalogoDestino;
	}

	/**
	 * @param codigoCatalogoDestino
	 *            the codigoCatalogoDestino to set
	 */
	public void setCodigoCatalogoDestino(Long codigoCatalogoDestino) {
		this.codigoCatalogoDestino = codigoCatalogoDestino;
	}

	/**
	 * @return the nombreDestino
	 */
	public String getNombreDestino() {
		return nombreDestino;
	}

	/**
	 * @param nombreDestino
	 *            the nombreDestino to set
	 */
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}

	/**
	 * @return the distanciaEnMillas
	 */
	public Double getDistanciaEnMillas() {
		return distanciaEnMillas;
	}

	/**
	 * @param distanciaEnMillas
	 *            the distanciaEnMillas to set
	 */
	public void setDistanciaEnMillas(Double distanciaEnMillas) {
		this.distanciaEnMillas = distanciaEnMillas;
	}

	/**
	 * @return the equivalenciaEnPuntos
	 */
	public Long getEquivalenciaEnPuntos() {
		return equivalenciaEnPuntos;
	}

	/**
	 * @param equivalenciaEnPuntos
	 *            the equivalenciaEnPuntos to set
	 */
	public void setEquivalenciaEnPuntos(Long equivalenciaEnPuntos) {
		this.equivalenciaEnPuntos = equivalenciaEnPuntos;
	}

	/**
	 * @return the destinoCanjeable
	 */
	public boolean isDestinoCanjeable() {
		return destinoCanjeable;
	}

	/**
	 * @param destinoCanjeable
	 *            the destinoCanjeable to set
	 */
	public void setDestinoCanjeable(boolean destinoCanjeable) {
		this.destinoCanjeable = destinoCanjeable;
	}
}
