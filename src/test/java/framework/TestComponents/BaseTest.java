package framework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage Login;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties(); // Properties is a Java class used to store key-value pairs from a//
											// .properties file.

		// File is a Java class in java.io package.
		// It represents a file or directory path in the file system.
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\framework\\resources\\GlobalData.properties");
		// \src\main\java\framework\resources\GlobalData.properties
		// FileInputStream fis = new FileInputStream(file); // ✅Reads the file content
		// as an input stream.
		prop.load(fis); // Loads key-value pairs from the file into the prop object.
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		//Comment
		// String browserName = prop.getProperty("browser"); // Gets the value
		// associated with the key "browser" in the

		// properties file.

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");

			}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1400,900));


		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		Login = new LandingPage(driver);
		Login.goTo();
		return Login;
	}

	@AfterMethod(alwaysRun = true)
	public void quit() {

		driver.quit();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
