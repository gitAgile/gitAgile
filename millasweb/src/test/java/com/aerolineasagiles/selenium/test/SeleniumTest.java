package com.aerolineasagiles.selenium.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		baseUrl = "http://localhost:8081/millasweb";
		driver.get(baseUrl);
		driver.findElement(By.id("reg:txtCedulaCliente"));
		driver.findElement(By.id("reg:btnConsultar"));
		driver.findElement(By.id("reg:lblPuntosAcumulados"));
		driver.findElement(By.id("reg:lblPuntosAcumuladosResultado"));
		driver.findElement(By.id("reg:lblDestinosCanjeables"));
		driver.findElement(By.id("reg:chkBoxDestinosSeleccionados"));
		driver.findElement(By.id("reg:btnCanjearDestinos"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

}
