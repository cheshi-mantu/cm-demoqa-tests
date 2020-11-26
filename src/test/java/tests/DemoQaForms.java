package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static helpers.AttachmentsHelper.attachScreenshot;
import static helpers.Environment.demoqaUrl;
import static io.qameta.allure.Allure.step;

@Epic("Demo QA - demoqa.com")
@Feature("Demo QA - demoqa.com :: Forms")
@Story("Automated tests for Forms")
@Tag("forms_tests")
class DemoQaForms extends TestBase {
    @Test
    @DisplayName("Forms tests")
    @Description("Open forms page, make tests for filling forms and checks")
    void fromTest() {
        step ("Open froms page", () -> {
            open(demoqaUrl + "/automation-practice-form");
            attachScreenshot("Forms practice first screen");
        });
    }

}

