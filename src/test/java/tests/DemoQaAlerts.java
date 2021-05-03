package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("Demo QA - demoqa.com")
@Feature("Demo QA - demoqa.com :: Alerts")
@Story("Automated tests for Alerts")
@Tag("alerts_tests")
class DemoQaAlerts extends TestBase {
    @Test
    @AllureId("3")
    @DisplayName("integration with pycharm")
    @Description("Open Alerts page, make tests for alerts processing")
    void fromTest() {
    }

}

