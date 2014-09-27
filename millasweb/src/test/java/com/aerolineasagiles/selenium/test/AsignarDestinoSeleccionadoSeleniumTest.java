package com.aerolineasagiles.selenium.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class AsignarDestinoSeleccionadoSeleniumTest extends SeleniumTest {

	@Test
	public void debeAsignarDestinoSeleccionado() throws InterruptedException {

		driver.findElement(By.id("reg:txtCedulaCliente")).clear();
		driver.findElement(By.id("reg:txtCedulaCliente")).sendKeys("1721222088");
		driver.findElement(By.id("reg:btnConsultar")).click();
		// Thread.sleep(2000L);
		driver.findElement(By.xpath("//table[@id='reg:chkBoxDestinosSeleccionados']/tbody/tr/td/div/div[2]")).click();
		driver.findElement(By.id("reg:btnCanjearDestinos")).click();
		// Thread.sleep(2000L);
		Assert.assertEquals("Destino(s) asignado(s) sin problemas!!!", driver.findElement(By.id("reg:msjAsignacionExitosa")).getText());
	}

}
