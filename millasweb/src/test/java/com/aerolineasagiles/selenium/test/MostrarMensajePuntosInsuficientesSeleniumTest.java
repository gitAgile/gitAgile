package com.aerolineasagiles.selenium.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MostrarMensajePuntosInsuficientesSeleniumTest extends SeleniumTest {

	@Test
	public void debeMostrarMensajePuntosInsuficientes() {
		driver.findElement(By.id("reg:txtCedulaCliente")).clear();
		driver.findElement(By.id("reg:txtCedulaCliente")).sendKeys("1721222088");
		driver.findElement(By.id("reg:btnConsultar")).click();
		driver.findElement(By.xpath("//table[@id='reg:chkBoxDestinosSeleccionados']/tbody/tr/td/div/div[2]")).click();
		driver.findElement(By.xpath("//table[@id='reg:chkBoxDestinosSeleccionados']/tbody/tr/td[3]/div/div[2]")).click();
		driver.findElement(By.id("reg:btnCanjearDestinos")).click();
		Assert.assertEquals("Los suma de puntos de los destinos escogido exceden el de los puntos acumulados.",
				driver.findElement(By.cssSelector("span.ui-messages-error-detail")).getText());
	}

}
