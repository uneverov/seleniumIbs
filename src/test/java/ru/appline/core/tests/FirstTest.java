package ru.appline.core.tests;

import org.junit.Test;
import ru.appline.core.basetestsclass.BaseTests;
import ru.appline.core.data.FiledCreateBusinessTrip;

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
                .assertTextField(FiledCreateBusinessTrip.BUSINESSUNIT, "Отдел внутренней разработки")
                .assertTextField(FiledCreateBusinessTrip.COMPANYNAME, "1234test")
                .assertTextField(FiledCreateBusinessTrip.DEPARTURECITY, "Россия, Хабаровск")
                .assertTextField(FiledCreateBusinessTrip.ARRIVALCITY, "Россия, Москва")
                .assertTextField(FiledCreateBusinessTrip.DEPARTUREDATE, "2023-06-14")
                .assertTextField(FiledCreateBusinessTrip.ARRIVALDATE, "2023-06-21")
                .assertCheckBoxIsSelected()
                .assertErrorMessage("Список командируемых сотрудников не может быть пустым");
    }
}
