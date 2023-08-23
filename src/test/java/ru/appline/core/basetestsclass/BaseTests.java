package ru.appline.core.basetestsclass;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

//    @AfterClass
//    public static void afterAll() {
//        InitManager.quitFramework();
//    }
}
