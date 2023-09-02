package ru.appline.core.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
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
     * @return CreateBusinessTripPage - т.е. остаемся на этой странице
     */
    @Step("Проверка открытия страницы 'Создания командировки'")
    public CreateBusinessTripPage checkCreateBusinessTripPage() {
        Assertions.assertEquals("Создать командировку", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Выбор подразделения
     * @param businessUnitName - название подразделения
     */
    @Step("Выбор подразделения '{businessUnitName}'")
    public CreateBusinessTripPage selectBusinessUnit(String businessUnitName) {
        Select dropdown = new Select(businessUnitDropDownMenu);
        dropdown.selectByVisibleText(businessUnitName);
        return this;
    }

    /**
     * Выбор компании
     * @param companyName - имя компании
     */
    @Step("Выбор компании '{companyName}'")
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
    @Step("Клип по чекбоксу 'Задачи'")
    public CreateBusinessTripPage selectTask() {
        waitUtilElementToBeClickable(ticketsOrdercheckBox).click();
        return this;
    }

    /**
     * Заполнить поле города выбытия
     * @param departureCity - города выбытия
     */
    @Step("Заполнить поле города выбытия - '{departureCity}'")
    public CreateBusinessTripPage addDepartureCity(String departureCity) {
        fillInputField(departureCityField, departureCity);
        return this;
    }

    /**
     * Заполнить поле города прибытия
     * @param arrivalCity - город прибытия
     */
    @Step("Заполнить поле города прибытия - '{arrivalCity}'")
    public CreateBusinessTripPage addArrivalCity(String arrivalCity) {
        fillInputField(arrivalCityField, arrivalCity);
        return this;
    }

    /**
     * Заполнить поле Планируемая дата выезда
     * @param departureDate - дата выезда
     */
    @Step("Заполнить поле Планируемая дата выезда - '{departureDate}'")
    public CreateBusinessTripPage addDepartureDate(String departureDate) {
        fillInputField(departureDatePlan, departureDate);
        activeDate.click();
        return this;
    }

    /**
     * Заполнить поле Планируемая дата приезда
     * @param arrivalDate - дата прибытия
     */
    @Step("Заполнить поле Планируемая дата приезда - '{arrivalDate}'")
    public CreateBusinessTripPage addArrivalDate(String arrivalDate) {
        fillInputField(arrivalDatePlan, arrivalDate);
        activeDate.click();
        return this;
    }

    /**
     * Нажать на кнопку "Сохранит и закрыть"
     */
    @Step("Нажать на кнопку 'Сохранит и закрыть'")
    public CreateBusinessTripPage saveAndCloseBusinessTrip() {
        saveAndCloseButton.click();
        wait.until(ExpectedConditions.invisibilityOf(loader));
        return this;
    }

    /**
     * Проверка заполненных значений
     *
     * @param nameField - веб-элемент поле ввода
     * @param expValue - значение вводимое в поле
     */
    @Step("Проверка заполненных значений")
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
                Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Создания командировки'");
        }
        String value = "";
        if (Objects.equals(attr, "Text")) {
            value = element.getText();
        } else {
            value = element.getAttribute("Value");
        }
        Assertions.assertEquals(expValue, value, "Поле " + nameField + " заполнено неверно");
        return this;
    }
    /**
     * Проверка выбранного чек-бокса
     */
    @Step("Проверка выбранного чек-бокса")
    public CreateBusinessTripPage assertCheckBoxIsSelected() {
        Assertions.assertTrue(ticketsOrdercheckBox.isSelected(), "Чекбокс 'Заказ билетов' не активен");
        return this;
    }
    /**
     * Проверка сообщения об ошибке
     * @param errorMessage - Ожидаемое сообщение об ошибке
     */
    @Step("Проверка сообщения об ошибке")
    public CreateBusinessTripPage assertErrorMessage(String errorMessage) {
        Assertions.assertEquals(errorMessage, validationError.getText(),
                "Сообщение об ошибке " + errorMessage + " отсутствует");
        return this;
    }
}