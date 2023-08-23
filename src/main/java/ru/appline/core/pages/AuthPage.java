package ru.appline.core.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.core.managers.TestPropManager;

import static ru.appline.core.utils.PropConst.*;

/**
 * @author Neverov Evgeny
 * Страница авторизации
 */
public class AuthPage extends BasePage {

    @FindBy(xpath = "//input[@id='prependedInput']")
    private WebElement loginFiled;

    @FindBy(xpath = "//input[@id='prependedInput2']")
    private WebElement passwordFiled;

    @FindBy(xpath = "//*[@name='_submit']")
    private WebElement submitButton;

    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Заполнения поля логина
     *
     * @return AuthPage - т.е. остаемся на этой странице
     */
    public AuthPage fillLoginFiled() {
        fillInputField(loginFiled, props.getProperty(LOGIN));
        return this;
    }
    /**
     * Заполнения поля пароля
     *
     * @return AuthPage - т.е. остаемся на этой странице
     */
    public AuthPage fillPasswordFiled() {
        fillInputField(passwordFiled, props.getProperty(PASSWORD));
        return this;
    }

    /**
     * Клик по кнопке "Войти"
     *
     * @return AuthPage - т.е. остаемся на этой странице
     */
    public HomePage clickSubmitButton() {
        waitUtilElementToBeClickable(submitButton).click();
        return pageManager.getHomePage().checkOpenHomePage();
    }
}
