package com.aerolineasagiles.selenium.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MostrarOpcionesDeViajeSeleniumTest extends SeleniumTest {

	@Test
	public void debeMostrarPuntosAcumuladosDeUnClienteConsultado() throws InterruptedException {
		WebElement txtCedulaCliente = driver.findElement(By.id("reg:txtCedulaCliente"));
		txtCedulaCliente.clear();
		WebElement btnConsultar = driver.findElement(By.id("reg:btnConsultar"));

		txtCedulaCliente.sendKeys("1721222088");
		btnConsultar.click();
		Thread.sleep(2000L);

		assertEquals("New York", driver.findElement(By.xpath("//table[@id='reg:chkBoxDestinosSeleccionados']/tbody/tr/td[2]/label")).getText());
		assertEquals("Bogota", driver.findElement(By.xpath("//table[@id='reg:chkBoxDestinosSeleccionados']/tbody/tr/td[4]/label")).getText());
	}

}
