package reUseables;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class reuseable_Method {
	WebDriver driver;
	WebElement element;

	public void launchBrowser() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		screenCapture();
	}

	public void openURL(String URL) throws IOException {
		driver.get(URL);
		driver.manage().window().maximize();
		screenCapture();
	}

	protected WebElement findElement(By bylocator) {
		element = driver.findElement(bylocator);
		return element;
	}

	public boolean Waituntil(By bylocator) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void click(By bylocator) throws Exception {

		element = driver.findElement(bylocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Waituntil(bylocator);
		if (element.isDisplayed() && element.isEnabled()) {
			screenCapture();
			findElement(bylocator).click();
		}
	}

	public void type(By bylocator, String text) throws Exception {
		try {
			element = findElement(bylocator);
			element.sendKeys(text);
			screenCapture();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void titleChk(By bylocator, String textToValidate) throws IOException {
		String titleFromApp = findElement(bylocator).getText();
		Assert.assertEquals(titleFromApp, textToValidate);
		screenCapture();
	}

	public void screenCapture() throws IOException {
		File rawImg = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot";
		FileUtils.copyFile(rawImg, new File(path + ".png"));
	}

	public void QuitBrowser() {
		driver.quit();
	}
}
