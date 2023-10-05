package ru.appline.core.steps;

import io.cucumber.java.ru.И;
import ru.appline.core.managers.PageManager;

public class AuthPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Заполнения поля логина$")
    public void fillLoginFiled() {
        pageManager.getAuthPage().fillLoginFiled();
    }

    @И("^Заполнения поля пароля$")
    public void fillPasswordFiled() {
        pageManager.getAuthPage().fillPasswordFiled();
    }

    @И("^Нажать на кнопку 'Войти'$")
    public void clickSubmitButton() {
        pageManager.getAuthPage().clickSubmitButton();
    }
}