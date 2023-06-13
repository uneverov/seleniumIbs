import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TrainingApplineTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "http://training.appline.ru/user/login";
    String login = "Секретарь";
    String password = "testing";
    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 20, 2000);
        driver.get(baseUrl);
    }
    @Test
    public void trainingApplineTest(){
        WebElement loginField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"prependedInput\"]")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"prependedInput2\"]")));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("//*[@name=\"_submit\"]"));
        submitButton.click();
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException ignored){
        }
    }
    @After
    public void after(){
        driver.quit();
    }
}