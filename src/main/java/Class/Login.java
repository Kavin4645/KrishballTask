package Class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Basic.BasisKB;
import io.reactivex.rxjava3.functions.Action;

public class Login extends BasisKB {
	@FindBy(id = "user-name")
	WebElement Login;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement LoginButton;

	

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void EnterDetails(String Username, String Password) throws Exception {
		enter(Login, Username);
		enter(password, Password);
		clickOn(LoginButton);
		String expectedTitle ="Single ";
		if (driver.getTitle().startsWith(expectedTitle)) {
			screenShot(getPageTitle());
			
		} else {
			screenShot(getPageTitle());
		}
		

	}
}
