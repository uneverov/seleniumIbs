package ru.appline.core.steps;

import io.cucumber.java.ru.И;
import io.cucumber.datatable.DataTable;
import ru.appline.core.managers.PageManager;

public class CreateBusinessTripPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Проверка открытия страницы 'Создания командировки'$")
    public void checkCreateBusinessTripPage() {
        pageManager.getCreateBusinessTripPage().checkCreateBusinessTripPage();
    }

    @И("^Выбор подразделения '(.+)'$")
    public void selectCreateBusinessTrip(String businessUnitName) {
        pageManager.getCreateBusinessTripPage().selectBusinessUnit(businessUnitName);
    }

    @И("^Выбор компании '(.+)'$")
    public void selectCompany(String companyName) {
        pageManager.getCreateBusinessTripPage().selectCompany(companyName);
    }

    @И("^Клип по чекбоксу 'Задачи'$")
    public void selectTask() {
        pageManager.getCreateBusinessTripPage().selectTask();
    }

    @И("^Заполнить поле города выбытия - '(.+)'$")
    public void addDepartureCity(String departureCity) {
        pageManager.getCreateBusinessTripPage().addDepartureCity(departureCity);
    }

    @И("^Заполнить поле города прибытия - '(.+)'$")
    public void addArrivalCity(String arrivalCity) {
        pageManager.getCreateBusinessTripPage().addArrivalCity(arrivalCity);
    }

    @И("^Заполнить поле Планируемая дата выезда - '(.+)'$")
    public void addDepartureDate(String departureDate) {
        pageManager.getCreateBusinessTripPage().addDepartureDate(departureDate);
    }

    @И("^Заполнить поле Планируемая дата приезда - '(.+)'$")
    public void addArrivalDate(String arrivalDate) {
        pageManager.getCreateBusinessTripPage().addArrivalDate(arrivalDate);
    }

    @И("^Нажать на кнопку 'Сохранит и закрыть'$")
    public void saveAndCloseBusinessTrip() {
        pageManager.getCreateBusinessTripPage().saveAndCloseBusinessTrip();
    }

    @И("^Проверка заполненных значений:$")
    public void assertTextField(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getCreateBusinessTripPage()
                        .assertTextField(((String) key), (String) value));
    }

    @И("^Проверка выбранного чек-бокса$")
    public void assertCheckBoxIsSelected() {
        pageManager.getCreateBusinessTripPage().assertCheckBoxIsSelected();
    }

    @И("^Проверка сообщения об ошибке: '(.+)'$")
    public void assertErrorMessage(String errorMessage) {
        pageManager.getCreateBusinessTripPage().assertErrorMessage(errorMessage);
    }
}
