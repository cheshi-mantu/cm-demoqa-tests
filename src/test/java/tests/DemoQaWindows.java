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
@Feature("Demo QA - demoqa.com :: Windows")
@Story("Automated tests for windows (web)")
@Tag("windows_tests")
class DemoQaWindows extends TestBase {
    @Test
    @DisplayName("Windows tests (web)")
    @Description("Open Alerts and windows page, make tests for windows (web)")
    void webWindowsTest() {
        step ("Open Alerts and windows page", () -> {
            open("https://demoqa.com/alertsWindows");
        });
    }

}

