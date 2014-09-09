package com.aerolineasagiles.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.aerolineasagiles.exception.DestinosExcedenPuntosException;
import com.aerolineasagiles.exception.NoPuntosAcumuladosException;
import com.aerolineasagiles.exception.PuntosNoSuficientesException;
import com.aerolineasagiles.model.CatalogoDestino;
import com.aerolineasagiles.service.CanjeoPuntosService;

@ViewScoped
@ManagedBean(name = "canjeoMillasController")
public class CanjeoMillasController {

	@EJB
	private CanjeoPuntosService canjeoPuntosService;

	private String cedulaCliente;
	private Long puntosAcumulados;
	private List<CatalogoDestino> destinosCanjeables;
	private Long[] destinosSeleccionados;
	private boolean asignacionExitosa;

	@PostConstruct
	public void postConstruct() {
		puntosAcumulados = 0l;
		destinosCanjeables = new ArrayList<CatalogoDestino>();

	}

	public void consultarListener() {
		consultarPuntosAcumuladosPorCliente();
		consultarDestinosCanjeables();
	}

	public void consultarDestinosCanjeables() {
		try {
			destinosCanjeables = canjeoPuntosService.obtenerDestinosCanjeablesPorPuntosAcumulados(puntosAcumulados);
		} catch (PuntosNoSuficientesException e) {
			FacesContext.getCurrentInstance().addMessage("txtCedulaCliente", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getMessage()));
		}
	}

	public void consultarPuntosAcumuladosPorCliente() {
		try {
			if (cedulaCliente != null && !cedulaCliente.isEmpty()) {
				puntosAcumulados = canjeoPuntosService.obtenerPuntosAcumulados(cedulaCliente);
			}
		} catch (NoPuntosAcumuladosException e) {
			FacesContext.getCurrentInstance().addMessage("txtCedulaCliente", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getMessage()));
		}
	}

	public void asignarDestinosSeleccionados() {
		List<CatalogoDestino> catalogoDestinosSeleccionados = obtenerCatalogosDestinosSeleccionados(destinosSeleccionados);
		try {
			puntosAcumulados = canjeoPuntosService.canjearDestinosConPuntosAcumulados(catalogoDestinosSeleccionados, puntosAcumulados);
			asignacionExitosa = true;
			// FacesContext.getCurrentInstance().addMessage("txtCedulaCliente",
			// new FacesMessage(FacesMessage.SEVERITY_INFO, null,
			// "Se han asignado correctamente los destinos seleccionados"));
		} catch (DestinosExcedenPuntosException e) {
			FacesContext.getCurrentInstance().addMessage("txtCedulaCliente", new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getMessage()));
		}
	}

	private List<CatalogoDestino> obtenerCatalogosDestinosSeleccionados(Long[] destinos) {
		List<CatalogoDestino> catalogoDestinoSeleccionado = new ArrayList<CatalogoDestino>();
		for (int indice = 0; indice < destinos.length; indice++) {
			CatalogoDestino catalogoDestino = canjeoPuntosService.obtenerDestinoPorCodigo(destinos[indice]);
			catalogoDestinoSeleccionado.add(catalogoDestino);
		}
		return catalogoDestinoSeleccionado;
	}

	/**
	 * @return the puntosAcumulados
	 */
	public Long getPuntosAcumulados() {
		return puntosAcumulados;
	}

	/**
	 * @param puntosAcumulados
	 *            the puntosAcumulados to set
	 */
	public void setPuntosAcumulados(Long puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

	/**
	 * @return the cedulaCliente
	 */
	public String getCedulaCliente() {
		return cedulaCliente;
	}

	/**
	 * @param cedulaCliente
	 *            the cedulaCliente to set
	 */
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	/**
	 * @return the destinosCanjeables
	 */
	public List<CatalogoDestino> getDestinosCanjeables() {
		return destinosCanjeables;
	}

	/**
	 * @param destinosCanjeables
	 *            the destinosCanjeables to set
	 */
	public void setDestinosCanjeables(List<CatalogoDestino> destinosCanjeables) {
		this.destinosCanjeables = destinosCanjeables;
	}

	/**
	 * @return the destinosSeleccionados
	 */
	public Long[] getDestinosSeleccionados() {
		return destinosSeleccionados;
	}

	/**
	 * @param destinosSeleccionados
	 *            the destinosSeleccionados to set
	 */
	public void setDestinosSeleccionados(Long[] destinosSeleccionados) {
		this.destinosSeleccionados = destinosSeleccionados;
	}

	/**
	 * @param canjeoPuntosService
	 *            the canjeoPuntosService to set
	 */
	public void setCanjeoPuntosService(CanjeoPuntosService canjeoPuntosService) {
		this.canjeoPuntosService = canjeoPuntosService;
	}

	/**
	 * @return the asignacionExitosa
	 */
	public boolean isAsignacionExitosa() {
		return asignacionExitosa;
	}

	/**
	 * @param asignacionExitosa
	 *            the asignacionExitosa to set
	 */
	public void setAsignacionExitosa(boolean asignacionExitosa) {
		this.asignacionExitosa = asignacionExitosa;
	}

}
