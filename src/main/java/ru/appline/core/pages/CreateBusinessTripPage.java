package ru.appline.core.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.appline.core.data.FiledCreateBusinessTrip;

import java.util.Objects;


/**
 * @author Neverov Evgeny
 * Класс описывающий страницу создания командировки
 */
public class CreateBusinessTripPage extends BasePage {

    @FindBy(xpath = "//div[@class='loader-mask']")
    private WebElement loader;

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement title;

    @FindBy(xpath = "//select[@name='crm_business_trip[businessUnit]']")
    private WebElement businessUnitDropDownMenu;

    @FindBy(xpath = "//a[@id='company-selector-show']")
    private WebElement companySelector;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    private WebElement companySelectorDropdownMenu;

    @FindBy(xpath = "//input[@class='select2-input select2-focused']")
    private WebElement companySelectorDropdownMenuFocused;

    @FindBy(xpath = "//span[@class='select2-match' and text()='1234test']")
    private WebElement selectCompany;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_tasks_1')]")
    private WebElement ticketsOrdercheckBox;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_departureCity')]")
    private WebElement departureCityField;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_arrivalCity')]")
    private WebElement arrivalCityField;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement departureDatePlan;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement arrivalDatePlan;

    @FindBy(xpath = "//a[contains(@class, 'ui-state-active')]")
    private WebElement activeDate;

    @FindBy(xpath = "//button[@class='btn btn-success action-button']")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "//input[@name='crm_business_trip[company]']")
    private WebElement companyNameField;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureDatePlan]']")
    private WebElement departureDate;

    @FindBy(xpath = "//input[@name='crm_business_trip[returnDatePlan]']")
    private WebElement arrivalDate;

    @FindBy(xpath = "//span[@class='validation-failed']")
    private WebElement validationError;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     */
    public CreateBusinessTripPage checkCreateBusinessTripPage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Создать командировку", title.getText());
        return this;
    }

    /**
     * Выбор подразделения
     */
    public CreateBusinessTripPage selectBusinessUnit() {
        Select dropdown = new Select(businessUnitDropDownMenu);
        dropdown.selectByVisibleText("Отдел внутренней разработки");
        return this;
    }

    /**
     * Выбор компании
     */
    public CreateBusinessTripPage selectCompany(String companyName) {
        waitUtilElementToBeClickable(companySelector).click();
        waitUtilElementToBeClickable(companySelectorDropdownMenu).click();
        companySelectorDropdownMenuFocused.sendKeys(companyName);
        waitUtilElementToBeVisible(selectCompany);
        companySelectorDropdownMenuFocused.sendKeys(Keys.RETURN);
        return this;
    }

    /**
     * Выбор задачи
     */
    public CreateBusinessTripPage selectTask() {
        waitUtilElementToBeClickable(ticketsOrdercheckBox).click();
        return this;
    }

    /**
     * Заполнить поле города выбытия
     */
    public CreateBusinessTripPage addDepartureCity(String departureCity) {
        fillInputField(departureCityField, departureCity);
        return this;
    }

    /**
     * Заполнить поле города прибытия
     */
    public CreateBusinessTripPage addArrivalCity(String arrivalCity) {
        fillInputField(arrivalCityField, arrivalCity);
        return this;
    }

    /**
     * Заполнить поле Планируемая дата выезда
     */
    public CreateBusinessTripPage addDepartureDate(String departureDate) {
        fillInputField(departureDatePlan, departureDate);
        activeDate.click();
        return this;
    }

    /**
     * Заполнить поле Планируемая дата приезда
     */
    public CreateBusinessTripPage addArrivalDate(String arrivalDate) {
        fillInputField(arrivalDatePlan, arrivalDate);
        activeDate.click();
        return this;
    }

    /**
     * Нажать на кнопку "Сохранит и закрыть"
     */
    public CreateBusinessTripPage saveAndCloseBusinessTrip() {
        saveAndCloseButton.click();
        wait.until(ExpectedConditions.invisibilityOf(loader));
        return this;
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param nameField - веб-элемент поле ввода
     * @param expValue - значение вводимое в поле
     */
    public CreateBusinessTripPage assertTextField(FiledCreateBusinessTrip nameField, String expValue) {
        WebElement element = null;
        String attr = "";
        switch (nameField) {
            case BUSINESSUNIT:
                Select dropdown = new Select(businessUnitDropDownMenu);
                element = dropdown.getFirstSelectedOption();
                attr = "Text";
                break;
            case COMPANYNAME:
                element = companyNameField;
                attr = "Value";
                break;
            case DEPARTURECITY:
                element = departureCityField;
                attr = "Value";
                break;
            case ARRIVALCITY:
                element = arrivalCityField;
                attr = "Value";
                break;
            case DEPARTUREDATE:
                element = departureDate;
                attr = "Value";
                break;
            case ARRIVALDATE:
                element = arrivalDate;
                attr = "Value";
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Создания командировки'");
        }
        String value = "";
        if (Objects.equals(attr, "Text")) {
            value = element.getText();
        } else {
            value = element.getAttribute("Value");
        }
        Assert.assertEquals("Поле " + nameField + " заполнено неверно", value, expValue);
        return this;
    }
    public CreateBusinessTripPage assertCheckBoxIsSelected() {
        Assert.assertTrue("Чекбокс 'Заказ билетов' не активен", ticketsOrdercheckBox.isSelected());
        return this;
    }

    public CreateBusinessTripPage assertErrorMessage(String errorMessage) {
        Assert.assertEquals("Сообщение об ошибке " + errorMessage + " отсутствует", errorMessage,
                validationError.getText());
        return this;
    }
}