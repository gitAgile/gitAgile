/**
 * 
 */
package com.aerolineasagiles.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "VUELO")
@Entity
public class Vuelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3890912370240313920L;

	@Id
	@Column(name = "COD_VUELO")
	private Long codigoVuelo;

	@Column(name = "DISTANCIA_RECORRIDA")
	private Double distanciaRecorrida;

	@Column(name = "CODIGO_DESTINO")
	private CatalogoDestino destino;

	@Column(name = "CLASE_VUELO")
	private String claseVuelo;

	@Column(name = "PUNTOS_OBTENIDOS")
	private Long puntosObtenidos;

	@Column(name = "ESTADO_VUELO")
	private EstadoEnum estado;

	@ManyToOne
	@JoinColumn(name = "COD_CLIENTE")
	private Cliente cliente;

	/**
	 * @return the codigoVuelo
	 */
	public Long getCodigoVuelo() {
		return codigoVuelo;
	}

	/**
	 * @param codigoVuelo
	 *            the codigoVuelo to set
	 */
	public void setCodigoVuelo(Long codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}

	/**
	 * @return the distanciaRecorrida
	 */
	public Double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	/**
	 * @param distanciaRecorrida
	 *            the distanciaRecorrida to set
	 */
	public void setDistanciaRecorrida(Double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}

	/**
	 * @return the destino
	 */
	public CatalogoDestino getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 *            the destino to set
	 */
	public void setDestino(CatalogoDestino destino) {
		this.destino = destino;
	}

	/**
	 * @return the claseVuelo
	 */
	public String getClaseVuelo() {
		return claseVuelo;
	}

	/**
	 * @param claseVuelo
	 *            the claseVuelo to set
	 */
	public void setClaseVuelo(String claseVuelo) {
		this.claseVuelo = claseVuelo;
	}

	/**
	 * @return the puntosObtenidos
	 */
	public Long getPuntosObtenidos() {
		return puntosObtenidos;
	}

	/**
	 * @param puntosObtenidos
	 *            the puntosObtenidos to set
	 */
	public void setPuntosObtenidos(Long puntosObtenidos) {
		this.puntosObtenidos = puntosObtenidos;
	}

	/**
	 * @return the estado
	 */
	public EstadoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

}
