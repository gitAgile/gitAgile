package com.aerolineasagiles.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "PARAMETRO_GENERAL")
@Entity
public class ParametroGeneral implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8058810502376034480L;

	@Id
	@Column(name = "COD_PARAMETRO")
	private Long codigoParametro;

	@Column(name = "NOMBRE_PARAMETRO")
	private String nombreParametro;

	@Column(name = "VALOR_PARAMETRO")
	private String valorParametro;

	/**
	 * @return the codigoParametro
	 */
	public Long getCodigoParametro() {
		return codigoParametro;
	}

	/**
	 * @param codigoParametro
	 *            the codigoParametro to set
	 */
	public void setCodigoParametro(Long codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	/**
	 * @return the nombreParametro
	 */
	public String getNombreParametro() {
		return nombreParametro;
	}

	/**
	 * @param nombreParametro
	 *            the nombreParametro to set
	 */
	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param valorParametro
	 *            the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
}
