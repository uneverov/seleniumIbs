package ru.appline.core.steps;

import io.cucumber.java.ru.И;
import ru.appline.core.managers.PageManager;

public class HomePageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Проверка открытия страницы 'Домашняя'$")
    public void checkOpenHomePage() {
        pageManager.getHomePage().checkOpenHomePage();
    }

    @И("^Выбор меню '(.+)'$")
    public void selectMenu(String nameMenu) {
        pageManager.getHomePage().selectMenu(nameMenu);
    }

    @И("^Выбор подменю '(.+)'$")
    public void selectSubMenu(String nameSubMenu) {
        pageManager.getHomePage().selectSubMenu(nameSubMenu);
    }
}
