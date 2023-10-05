package ru.appline.core.pages;


import org.junit.jupiter.api.Assertions;
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
     * @return HomePage - т.е. остаемся на этой странице
     */
    public HomePage checkOpenHomePage() {
        Assertions.assertEquals("Панель быстрого запуска", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }
    /**
     * Выбор меню
     * @param nameMenu - название меню
     */
    public HomePage selectMenu(String nameMenu) {
        for (WebElement menuItem : listMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Меню '" + nameMenu + "' не было найдено на домашней странице!");
        return this;
    }
    /**
     * Выбор подменю
     * @param nameSubMenu - название подменю
     */
    public BusinessTripPage selectSubMenu(String nameSubMenu) {
        for (WebElement subMenuItem : listSubMenu) {
            if (subMenuItem.getAttribute("textContent").equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(subMenuItem).click();
                wait.until(ExpectedConditions.invisibilityOf(loader));
                return pageManager.getBusinessTripPage().checkOpenBusinessTripPage();
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на домашней странице!");
        return pageManager.getBusinessTripPage();
    }

}
