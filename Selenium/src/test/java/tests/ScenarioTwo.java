package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ScenarioTwo {
	@Test
	public void drag() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(
				"https://nam12.safelinks.protection.outlook.com/?url=http%3A%2F%2Fjqueryui.com%2Fdroppable%2F&data=05%7C01%7CTalari.Mohan%40qualitestgroup.com%7Cb0082244ba654e3997be08da58fea8bd%7C6be5b754cbd243939dc2d7050d353c69%7C0%7C0%7C637920148033493987%7CUnknown%7CTWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLCJBTiI6Ik1haWwiLCJXVCI6Mn0%3D%7C3000%7C%7C%7C&sdata=Pb4dfGrY%2B1J7yxlUxV%2BjXEnonz8Xu1aoPWpqIYSGFfY%3D&reserved=0");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		Actions act = new Actions(driver);
		WebElement fromEle = driver.findElement(By.id("draggable"));
		WebElement toEle = driver.findElement(By.id("droppable"));
		String textBefore = toEle.getText().toString();
		String backGroundBefore = toEle.getCssValue("background");
		String colorBefore = toEle.getCssValue("color");
		act.dragAndDrop(fromEle, toEle).build().perform();
		Thread.sleep(2000);
		String backGroundAfter = toEle.getCssValue("background").toString();
		String colorAfter = toEle.getCssValue("color");
		String textAfter = toEle.getText().toString();
		assertNotEquals(backGroundAfter, backGroundBefore, "Css value for background matches even after drag");
		assertNotEquals(colorAfter, colorBefore, "Css value for color matches even after drag");
		assertNotEquals(textAfter, textBefore, "Text doesn't change even after drag");
		driver.quit();
	}
}
