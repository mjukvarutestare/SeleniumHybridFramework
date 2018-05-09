package se.selenium_hybrid_framework.actionModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import se.selenium_hybrid_framework.pageObjects.*;

public class FindFlightsAction {
	public static void execute(WebDriver driver, String sFromPort, String sToPort){
		Select fromPort = new Select(HomePage.select_FromPort(driver));
		fromPort.selectByVisibleText(sFromPort);
		Select toPort = new Select(HomePage.select_ToPort(driver));
		toPort.selectByVisibleText(sToPort);
		HomePage.btn_FindFlights(driver).click();
	}
}
