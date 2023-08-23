package ru.appline.core.tests;

import org.junit.Test;
import ru.appline.core.basetestsclass.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {
        app.getAuthPage()
                .fillLoginFiled()
                .fillPasswordFiled()
                .clickSubmitButton()
                .selectMenu("Расходы")
                .selectSubMenu("Командировки")
                .selectCreateBusinessTrip()
                .selectBusinessUnit()
                .selectCompany("1234test")
                .selectTask()
                .addDepartureCity("Россия, Хабаровск")
                .addArrivalCity("Россия, Москва")
                .addDepartureDate("14.06.2023")
                .addArrivalDate("21.06.2023")
                .saveAndCloseBusinessTrip()
                .assertTextField("Подразделение", "Отдел внутренней разработки")
                .assertTextField("Принимающая организация", "1234test")
                .assertTextField("Город выбытия", "Россия, Хабаровск")
                .assertTextField("Город прибытия", "Россия, Москва")
                .assertTextField("Планируемая дата выезда", "2023-06-14")
                .assertTextField("Планируемая дата возвращения", "2023-06-21")
                .assertCheckBoxIsSelected()
                .assertErrorMessage("Список командируемых сотрудников не может быть пустым");
    }
}
