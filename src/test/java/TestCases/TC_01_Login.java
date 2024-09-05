package TestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Basic.BasisKB;
import Class.Login;


public class TC_01_Login extends BasisKB{
	@BeforeTest
	public void setup() {
		testName = "TC_01_Login";
		testDescription = "Navigating to the SWAG LAB website ";
		testCategory = "Functional";
		testAuthor = "Kavin Vikram ";
		
	}
	@Test(dataProvider = "getFromExcel",priority = 1)
	public void TC_01_Login(String Username, String Password) throws Exception {
		Login LG= new Login(driver);
		LG.EnterDetails(Username, Password);
	}
	

}
