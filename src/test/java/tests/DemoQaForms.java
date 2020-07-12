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
@Feature("Demo QA - demoqa.com :: Forms")
@Story("Automated tests for Forms")
@Tag("forms_tests")
class DemoQaForms extends TestBase {
    @Test
    @Description("Open main page")
    @DisplayName("Navigate from main page to Vega 2.0 Brief")
    void fromTest() {
        step ("Open froms page", () -> {
            open("https://demoqa.com/forms");
        });
    }

}

