package tests;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ScenarioOne {
	@Test
	public void oneway() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(
				"https://nam12.safelinks.protection.outlook.com/?url=https%3A%2F%2Fwww.makemytrip.com%2F&data=05%7C01%7CTalari.Mohan%40qualitestgroup.com%7Cb0082244ba654e3997be08da58fea8bd%7C6be5b754cbd243939dc2d7050d353c69%7C0%7C0%7C637920148033493987%7CUnknown%7CTWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLCJBTiI6Ik1haWwiLCJXVCI6Mn0%3D%7C3000%7C%7C%7C&sdata=59gzxOtpFF5BsHy2t5eZDkQXZGRHayJXqpaba9aqTTY%3D&reserved=0");
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.id("fromCity")).click();
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='From']")));
		driver.findElement(By.xpath("//*[@placeholder='From']")).sendKeys("Delhi");
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='New Delhi, India']")));
		driver.findElement(By.xpath("//*[text()='New Delhi, India']")).click();
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='To']")));
		driver.findElement(By.xpath("//*[@placeholder='To']")).sendKeys("Mumbai");
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Mumbai, India']")));
		driver.findElement(By.xpath("//*[text()='Mumbai, India']")).click();
		wb.until(ExpectedConditions.elementToBeClickable(By.className("langCardClose")));
		driver.findElement(By.className("langCardClose")).click();
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Search']")));
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[text()='Search']")))
				.click(driver.findElement(By.xpath("//*[text()='Search']"))).build().perform();
		driver.findElement(By.xpath("//*[text()='Search']")).click();
		Thread.sleep(2000);
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='OKAY, GOT IT!']")));
		driver.findElement(By.xpath("//*[text()='OKAY, GOT IT!']")).click();
		wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='priceSection']//p")));
		List<WebElement> prices = driver.findElements(By.xpath("//*[@class='priceSection']//p"));
		List<WebElement> viewPrices = driver
				.findElements(By.xpath("//div[@class='listingCard']//*[text()='View Prices']"));
		System.out.println(prices.size());
		List<Integer> actualPrices = new ArrayList<Integer>();
		for (int i = 0; i < prices.size(); i++) {
			int temp = Integer.parseInt(prices.get(i).getText().replace("₹", "").replace(",", "").trim());
			actualPrices.add(temp);
		}
		System.out.println(actualPrices.size());
		Collections.sort(actualPrices);
		int lowest = actualPrices.get(0);
		System.out.println(actualPrices.get(0));
		for (int i = 0; i < prices.size(); i++) {
			int temp = Integer.parseInt(prices.get(i).getText().replace("₹", "").replace(",", "").trim());
			if (temp == lowest) {
				viewPrices.get(i).click();
				Thread.sleep(2000);
				wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='viewFareBtnCol ']/p[text()='"
						+ prices.get(i).getText() + "']/parent::div/button[text()='Book Now']")));
				driver.findElement(By.xpath("//*[@class='viewFareBtnCol ']/p[text()='" + prices.get(i).getText()
						+ "']/parent::div/button[text()='Book Now']")).click();

			}
		}
driver.quit();
	}

}
