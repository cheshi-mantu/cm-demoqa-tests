package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
    @DisplayName("Alerts tests")
    @Description("Open Alerts page, make tests for alerts processing")
    void fromTest() {
        step ("Open froms page", () -> {
            open("https://demoqa.com/alertsWindows");
        });
    }

}

