package ru.appline.core.steps;

import io.cucumber.java.ru.И;
import ru.appline.core.managers.PageManager;

public class BusinessTripPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Проверка открытия страницы 'Командировки'$")
    public void checkCreateBusinessTripPage() {
        pageManager.getBusinessTripPage().checkOpenBusinessTripPage();
    }

    @И("^Клип по кнопке 'Создать командировку'$")
    public void selectCreateBusinessTrip() {
        pageManager.getBusinessTripPage().selectCreateBusinessTrip();
    }
}