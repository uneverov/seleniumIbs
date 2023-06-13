import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TrainingApplineTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "http://training.appline.ru/user/login";
    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 2000);
        driver.get(baseUrl);
    }
    @Test
    public void trainingApplineTest(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"prependedInput\"]")));

    }
    @After
    public void after(){
        driver.quit();
    }
}