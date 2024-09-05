package TestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Basic.BasisKB;
import Class.EnterPage;
import Class.Login;

public class TC_02_Enterpage extends BasisKB{
	@BeforeTest
	public void setup() {
		testName = "TC_01_Login";
		testDescription = "Navigating to the SWAG LAB website ";
		testCategory = "Functional";
		testAuthor = "Kavin Vikram ";
		
	}
	@Test(priority = 2)
	public void TC_02_Enterpage() throws Exception {
		Login LG= new Login(driver);
		LG.EnterDetails("standard_user", "secret_sauce");
		EnterPage Ep =new EnterPage(driver);
		Ep.AddingProducttoCart();
		Ep.RemovingProduct();
		Ep.checkout();
	}
	

}
