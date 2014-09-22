package com.aerolineasagiles.selenium.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MostrarPuntosAcumuladosSeleniumTest extends SeleniumTest {

	@Test
	public void debeMostrarPuntosAcumuladosDeUnClienteConsultado() throws InterruptedException {
		WebElement txtCedulaCliente = driver.findElement(By.id("reg:txtCedulaCliente"));
		txtCedulaCliente.clear();
		WebElement btnConsultar = driver.findElement(By.id("reg:btnConsultar"));

		txtCedulaCliente.sendKeys("1721222088");
		btnConsultar.click();
		Thread.sleep(2000L);
		WebElement lblPuntosAcumuladosResultado = driver.findElement(By.id("reg:lblPuntosAcumuladosResultado"));
		String valor = lblPuntosAcumuladosResultado.getText();

		Assert.assertEquals("500", valor);
	}

}
