package ru.appline.core.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Neverov Evgeny
 * Класс описывающий страницу Командировки
 */
public class BusinessTripPage extends BasePage {

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;

    @FindBy(xpath = "//a[@title='Создать командировку']")
    private WebElement createBusinessTripBtn;

    @FindBy(xpath = "//div[@class='loader-mask']")
    private WebElement loader;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage checkOpenBusinessTripPage() {
        Assertions.assertEquals("Все Командировки", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }
    public CreateBusinessTripPage selectCreateBusinessTrip() {
        waitUtilElementToBeClickable(createBusinessTripBtn).click();
        wait.until(ExpectedConditions.invisibilityOf(loader));
        return pageManager.getCreateBusinessTripPage().checkCreateBusinessTripPage();
    }
}
