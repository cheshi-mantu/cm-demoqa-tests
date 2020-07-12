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
@Feature("Demo QA - demoqa.com :: Frames")
@Story("Automated tests for Frames")
@Tag("frames_tests")
class DemoQaFrame extends TestBase {
    @Test
    @DisplayName("Frames tests")
    @Description("Open frames and windows page, make tests for filling forms and checks")
    void fromTest() {
        step ("Open frames and windows page", () -> {
            open("https://demoqa.com/alertsWindows");
        });
    }

}

