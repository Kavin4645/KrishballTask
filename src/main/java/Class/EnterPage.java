package Class;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Basic.BasisKB;

public class EnterPage extends BasisKB{
	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	WebElement BagAdd;

	@FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
	WebElement TSertAdd;

	@FindBy(id = "shopping_cart_container")
	WebElement Cartbutton;
	
	@FindBy(id = "remove-sauce-labs-backpack")
	WebElement removeBag;
	
	@FindBy(className = "shopping_cart_badge")
	static WebElement numberOfProductsInCart;
	
	@FindBy(id = "checkout")
	WebElement CheckoutButton;
	
	@FindBy(id = "first-name")
	WebElement FirstName;
	
	@FindBy(id = "last-name")
	WebElement LastName;
	
	@FindBy(id = "postal-code")
	WebElement ZipCode;
	
	@FindBy(id = "continue")
	WebElement ContinueButton;

	@FindBy(id = "finish")
	WebElement FinishButton;
	
	@FindBy(linkText  = "Thank you for your order!")
	WebElement CompleteBooking;
	
	@FindBy(id = "back-to-products")
	WebElement BackToHome;

	public EnterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void AddingProducttoCart() throws InterruptedException, Exception {
		clickOn(BagAdd);
		clickOn(TSertAdd);
		clickOn(Cartbutton);
				
	}

	public void RemovingProduct() {
		clickOn(removeBag);	
	}
	
	public static int numberOfProductsInCart(){
        String number =  numberOfProductsInCart.getText();
        return Integer.parseInt(number);
    }
	public void checkout() throws InterruptedException, Exception {
		clickOn(CheckoutButton);
		enter(FirstName, "Kevin");
		enter(LastName, "Leven");
		enter(ZipCode, "123456");
		clickOn(ContinueButton);
		screenShot(getPageTitle());
		clickOn(FinishButton);
		clickOn(BackToHome);
		if (driver.equals(BackToHome)) {
			screenShot(getPageTitle());
		} else {
			screenShot(getPageTitle());

		}
	}
}


