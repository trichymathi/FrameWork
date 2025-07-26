package step_Defenition_Class;

import java.io.IOException;

import org.testng.Assert;

import ReusableMethods.ReusableMethodsClass;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Step_Define {
	
	ReusableMethodsClass methods = new ReusableMethodsClass();
	
	
    
	@Given("^The User login into the Webpage$")
	public void the_user_login_into_the_webpage() throws IOException {
		methods.launchBrowser();
	}
	
	@Then("User launches the Url {string} in the browser")
	public void userLaunchesTheUrlInTheBrowser(String string) throws IOException {
		methods.launchUrl(string);
	}
	
	@And("^User clicks on the \"(.*)\" mobile in the screen$")
	public void userEntersTheTextAndSearch(String typevalue) throws IOException{
		methods.clickOnElement("//a[text()='"+typevalue+"']");
		methods.screenShot("Value");
		methods.clickOnElement("//a[@onclick='addToCart(1)']");
		methods.screenShot("Add Cart Button");
		methods.acceptAlert();
		methods.clickOnElement("//a[@id='cartur']");	
		methods.screenShot("Cart");
	}
	
	@And("^User checks the mobile model Enterd is \"(.*)\" is same added in cart page$")
	public void verificationOfProduct(String product) throws IOException {
	    methods.waitUntillElementVisible("//h2[text()='Products']",20);
		String name =methods.getText("(//tr[@class='success']//following::td)[1]").trim();
		Assert.assertEquals(product, name);
		methods.screenShot("Verification");
		System.out.println(name);
	}
	
	@And("User close the browser")
	public void tearDown(){
		methods.quitBrowser();
	}

}
