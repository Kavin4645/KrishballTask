package Basic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utiles.UtilesKB;

public class BasisKB extends UtilesKB {
	@BeforeSuite
	public void StartReport() {
		int randomnum = (int) (Math.random() * 10 + 1000);
		String path = System.getProperty("user.dir") + "/Reports/" + randomnum + "index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation HyperLink Result");
		reporter.config().setDocumentTitle("Test Result");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Kavin Vikram");

	}

	@BeforeClass
	public void testDetails() {
		test = extent.createTest(testName, testDescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
	}

	@BeforeMethod
	public void start() throws Exception {
		launchbrowser(readproperty("brouser"));
		getapplication("https://www.saucedemo.com/");// "url"

	}

	@AfterMethod
	public void close() {
		driver.close();
	}

	@DataProvider
	public Object[][] getFromExcel() throws Exception {
		Object[][] data = dataReader();
		return data;
	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}

}
