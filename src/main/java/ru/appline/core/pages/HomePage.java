package ru.appline.core.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author Neverov Evgeny
 * Класс описывающий домашнюю страницу
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;

    @FindBy(xpath = "//div[@class='loader-mask']")
    private WebElement loader;

    @FindBy(xpath = "//ul[@class='nav nav-multilevel main-menu']/li/a/span[@class='title']")
    private List<WebElement> listMenu;

    @FindBy(xpath = "//li[@data-route]/a/span")
    private List<WebElement> listSubMenu;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public HomePage checkOpenHomePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Панель быстрого запуска", title.getText());
        return this;
    }

    public HomePage selectMenu(String nameMenu) {
        for (WebElement menuItem : listMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Меню '" + nameMenu + "' не было найдено на домашней странице!");
        return this;
    }

    public BusinessTripPage selectSubMenu(String nameSubMenu) {
        for (WebElement subMenuItem : listSubMenu) {
            if (subMenuItem.getAttribute("textContent").equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(subMenuItem).click();
                wait.until(ExpectedConditions.invisibilityOf(loader));
                return pageManager.getBusinessTripPage().checkOpenBusinessTripPage();
            }
        }
        Assert.fail("Подменю '" + nameSubMenu + "' не было найдено на домашней странице!");
        return pageManager.getBusinessTripPage();
    }

}
