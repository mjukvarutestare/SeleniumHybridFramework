package se.selenium_hybrid_framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	//Locators
	private static final By BY_LNK_DEST_WEEK = By.cssSelector("a[href='vacation.html']");
	private static final By BY_BTN_FIND_FLIGHTS = By.cssSelector("button.btn, input[type='submit'].btn");
	private static final By BY_SELECT_FROM_PORT = By.name("fromPort");
	private static final By BY_SELECT_TO_PORT = By.name("toPort");
	
	//Link to "Destination of the Week"
	public static WebElement lnk_DestWeek(WebDriver driver){
		return driver.findElement(BY_LNK_DEST_WEEK);
	}
	
	//Find flights button
	public static WebElement btn_FindFlights(WebDriver driver){
		return driver.findElement(BY_BTN_FIND_FLIGHTS);
	}
	
	public static WebElement select_FromPort(WebDriver driver){
		return driver.findElement(BY_SELECT_FROM_PORT);
	}
	
	public static WebElement select_ToPort(WebDriver driver){
		return driver.findElement(BY_SELECT_TO_PORT);
	}
	
	
	
}
