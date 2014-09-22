package com.aerolineasagiles.selenium.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class SeleniumTest {

	protected WebDriver driver;
	protected String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		baseUrl = "http://localhost:8081/millasweb";
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

}
