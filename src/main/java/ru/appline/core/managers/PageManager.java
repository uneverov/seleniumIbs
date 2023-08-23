package ru.appline.core.managers;

import ru.appline.core.pages.AuthPage;
import ru.appline.core.pages.BusinessTripPage;
import ru.appline.core.pages.CreateBusinessTripPage;
import ru.appline.core.pages.HomePage;

/**
 * @author Neverov Evgeny
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Cтраница авторизации
     */
    private AuthPage authPage;

    /**
     * Стартовая страница
     */
    private HomePage homePage;

    /**
     * Страница командировки
     */
    private BusinessTripPage businessTripPage;

    /**
     * Страница создания командировки
     */
    private CreateBusinessTripPage createBusinessTripPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link AuthPage}
     *
     * @return AuthPage
     */
    public AuthPage getAuthPage() {
        if (authPage == null) {
            authPage = new AuthPage();
        }
        return authPage;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return HomePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link BusinessTripPage}
     *
     * @return BusinessTripPage
     */
    public BusinessTripPage getBusinessTripPage() {
        if (businessTripPage == null) {
            businessTripPage = new BusinessTripPage();
        }
        return businessTripPage;
    }

    /**
     * Ленивая инициализация {@link BusinessTripPage}
     *
     * @return BusinessTripPage
     */
    public CreateBusinessTripPage getCreateBusinessTripPage() {
        if (createBusinessTripPage == null) {
            createBusinessTripPage = new CreateBusinessTripPage();
        }
        return createBusinessTripPage;
    }
}