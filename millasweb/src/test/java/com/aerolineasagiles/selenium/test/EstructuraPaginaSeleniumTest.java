package com.aerolineasagiles.selenium.test;

import org.junit.Test;
import org.openqa.selenium.By;

public class EstructuraPaginaSeleniumTest extends SeleniumTest {

	@Test
	public void debeEncontrarTodosLosElementosDeLaPagina() throws Exception {

		driver.findElement(By.id("reg:txtCedulaCliente"));
		driver.findElement(By.id("reg:btnConsultar"));
		driver.findElement(By.id("reg:lblPuntosAcumulados"));
		driver.findElement(By.id("reg:lblPuntosAcumuladosResultado"));
		driver.findElement(By.id("reg:lblDestinosCanjeables"));
		driver.findElement(By.id("reg:chkBoxDestinosSeleccionados"));
		driver.findElement(By.id("reg:btnCanjearDestinos"));
	}

}
