package Utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.dockerjava.api.model.Driver;

public class UtilesKB {
	public static WebDriver driver;
	public String testName;
	public String testDescription;
	public String testCategory;
	public String testAuthor;
	public static ExtentTest test;
	public static ExtentReports extent;
	public String sheetName;

	public static String readproperty(String Key) throws IOException {
		String projectpath = System.getProperty("user.dir");
		File file = new File(projectpath + "/Conifg.properties");
		FileInputStream FileInput = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(FileInput);
		return prop.get(Key).toString();
	}

	public static void launchbrowser(String brouser) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public static void ExplicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitExplicitUntilTitle(String titltToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
		wait.until(ExpectedConditions.titleIs(titltToWait));
	}

	public static void getapplication(String url) {
		driver.get(url);
	}

	public static String getPageTitle() {
		waitExplicitUntilTitle(driver.getTitle());
		return driver.getTitle();
	}

	public static void selectDropDown(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	public static void titleAssertion(String expTitle) {
		Assert.assertEquals(getPageTitle(), expTitle);
	}

	public static void jsScrollUntilElement(WebElement element) {
		ExplicitWait(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void jsClickOn(WebElement element) {
		ExplicitWait(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}

	public static String extractText(WebElement element) {
		return element.getText();
	}

	public static void clickOn(WebElement element) {
		ExplicitWait(element);
		element.click();
	}

	public void selectByIndex(WebElement ele, int value) {
        ele.click();
		Select sel = new Select(ele);
	
		sel.selectByIndex(value);
	}

	public static void enter(WebElement element, String text) {
		ExplicitWait(element);
		element.clear();
		element.sendKeys(text);
		element.click();
	}

	public void action() {
		driver.switchTo().alert().accept();
		
	}
	

	public void jsscrollDownBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public static boolean Clickboo(WebElement element) {
		element.getText();
		return true;

	}

	public static int screenShot(String testName) throws InterruptedException, Exception, Exception {
		int randomnum = (int) (Math.random() * 10 + 1000);
		Thread.sleep(1000);
		String projectpatth = System.getProperty("user.dir");
		FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
				new File(projectpatth + "/ScreenShot/" + testName + randomnum + ".png"));
		return randomnum;
	}

	public void reportStep(String stepDetails, String stepStatus, String testName) throws Exception, Exception {
		int randomnum = screenShot(testName);
		String projectPath = System.getProperty("user.dir");

		if (stepStatus.equalsIgnoreCase("pass")) {
			Assert.assertTrue(true, stepDetails);
			test.pass(stepDetails, MediaEntityBuilder
					.createScreenCaptureFromPath(projectPath + "/Screenshot/" + testName + randomnum + ".png").build());
		} else if (stepStatus.equalsIgnoreCase("fail")) {
			Assert.fail("Step Failed: " + stepDetails);
			test.fail(stepDetails, MediaEntityBuilder
					.createScreenCaptureFromPath(projectPath + "/Screenshot/" + testName + randomnum + ".png").build());
		}
	}

	public static Object[][] dataReader() throws IOException {

		XSSFWorkbook workBook = new XSSFWorkbook("D:/KrishballTask.xlsx");
		XSSFSheet sheet = workBook.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		System.out.println(rows);
		System.out.println(columns);
		Object[][] data = new Object[rows][columns];
		for (int r = 1; r <= rows; r++) {
			for (int c = 0; c < columns; c++) {
				data[r - 1][c] = sheet.getRow(r).getCell(c).toString();
				System.out.println(sheet.getRow(r).getCell(c).toString());
			}
		}
		workBook.close();
		return data;
	}

}
