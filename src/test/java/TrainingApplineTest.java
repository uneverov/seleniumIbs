import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TrainingApplineTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "http://training.appline.ru/user/login";
    String login = "Taraskina Valeriya";
    String password = "testing";
    String organizationName = "1234test";
    String departureCity = "Россия, Хабаровск";
    String arrivalCity = "Россия, Москва";
    String departureDate = "14.06.2023";
    String arrivalDate = "21.06.2023";
    String departureDateExp = "2023-06-21";
    String arrivalDateExp = "2023-06-14";
    String businessUnitExp = "Отдел внутренней разработки";
    String errorMessage = "Список командируемых сотрудников не может быть пустым";

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
        wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//h1[@class=\"oro-subtitle\" and text()=\"Панель быстрого запуска\"]")))));
        WebElement outlaysMenu = driver.findElement(By.xpath("//span[@class=\"title\" and text()=\"Расходы\"]"));
        Actions builder = new Actions(driver);
        builder.moveToElement(outlaysMenu).build().perform();
        WebElement businessTrips = driver.findElement(By.xpath("//span[@class=\"title\" and text()=\"Командировки\"]"));
        businessTrips.click();
        WebElement createBusinessTripBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title=\"Создать командировку\"]")));
        createBusinessTripBtn.click();
        wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//h1[@class=\"user-name\"]")))));
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name=\"crm_business_trip[businessUnit]\"]")));
        dropdown.selectByVisibleText("Отдел внутренней разработки");
        WebElement openList = driver.findElement(By.xpath("//a[@id=\"company-selector-show\"]"));
        openList.click();
        WebElement selectOrganization = driver.findElement(By.xpath("//span[@class=\"select2-chosen\"]"));
        selectOrganization.click();
        WebElement selectOrganizationInput = wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//input[@class=\"select2-input select2-focused\"]")))));
        selectOrganizationInput.sendKeys(organizationName);
        wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath("//span[@class=\"select2-match\" and text()='"+organizationName+"']")))));
        selectOrganizationInput.sendKeys(Keys.RETURN);
        WebElement ticketsOrdercheckBox = driver.findElement(By.xpath("//input[contains(@id, \"crm_business_trip_tasks_1\")]"));
        ticketsOrdercheckBox.click();
        WebElement departureCityField = driver.findElement(By.xpath("//input[contains(@id, \"crm_business_trip_departureCity\")]"));
        departureCityField.clear();
        departureCityField.sendKeys(departureCity);
        WebElement arrivalCityField = driver.findElement(By.xpath("//input[contains(@id, \"crm_business_trip_arrivalCity\")]"));
        arrivalCityField.clear();
        arrivalCityField.sendKeys(arrivalCity);
        WebElement departureDatePlan = driver.findElement(By.xpath("//input[contains(@id, \"date_selector_crm_business_trip_departureDatePlan\")]"));
        departureDatePlan.sendKeys(departureDate);
        WebElement departureDateElement = driver.findElement(By.xpath("//a[contains(@class, \"ui-state-active\")]"));
        departureDateElement.click();
        WebElement arrivalDatePlan = driver.findElement(By.xpath("//input[contains(@id, \"date_selector_crm_business_trip_returnDatePlan\")]"));
        arrivalDatePlan.sendKeys(arrivalDate);
        WebElement arrivalElement = driver.findElement(By.xpath("//a[contains(@class, \"ui-state-active\")]"));
        arrivalElement.click();
        WebElement saveAndCloseButton = driver.findElement(By.xpath("//button[@class=\"btn btn-success action-button\"]"));
        saveAndCloseButton.click();
        Assert.assertEquals("Поле \"Подразделение\" заполнено неверно", businessUnitExp, dropdown.getFirstSelectedOption().getText());
        Assert.assertEquals("Поле \"Принимающая организация\" заполнено неверно", organizationName, driver.findElement(By.xpath("//input[contains(@id, \"crm_business_trip_company\")]")).getAttribute("value"));
        Assert.assertTrue("Чекбокс \"Заказ билетов\" не активен", ticketsOrdercheckBox.isSelected());
        Assert.assertEquals("Поле \"Город выбытия\" заполнено неверно", departureCity, departureCityField.getAttribute("value"));
        Assert.assertEquals("Поле \"Город прибытия\" заполнено неверно", arrivalCity, arrivalCityField.getAttribute("value"));
        Assert.assertEquals("Поле \"Планируемая дата приезда\" заполнено неверно", arrivalDateExp, driver.findElement(By.xpath("//input[@name=\"crm_business_trip[departureDatePlan]\"]")).getAttribute("value"));
        Assert.assertEquals("Поле \"Планируемая дата возвращения\" заполнено неверно", departureDateExp, driver.findElement(By.xpath("//input[@name=\"crm_business_trip[returnDatePlan]\"]")).getAttribute("value"));
        Assert.assertEquals("Сообщение об ошибке \"Список командируемых сотрудников не может быть пустым\" отсутствует", errorMessage, driver.findElement(By.xpath("//span[@class=\"validation-failed\"]")).getText());

    }

      @After
      public void after(){
         driver.quit();
     }
}