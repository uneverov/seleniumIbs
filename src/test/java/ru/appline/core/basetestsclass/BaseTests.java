package ru.appline.core.basetestsclass;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import ru.appline.core.managers.DriverManager;
import ru.appline.core.managers.InitManager;
import ru.appline.core.managers.PageManager;
import ru.appline.core.managers.TestPropManager;

import static ru.appline.core.utils.PropConst.BASE_URL;

public class BaseTests {

    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
