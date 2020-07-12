package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;
@Epic("Demo QA - demoqa.com")
@Feature("Demo QA - demoqa.com :: Elements")
@Story("Automated tests for elements" +
        "text box" +
        "Check box" +
        "Radio button" +
        "Web tables" +
        "Buttons" +
        "Links" +
        "Upload and download" +
        "Dynamic properties")
@Tag("elements_tests")
class DemoQaElements extends TestBase {
    @Test
    @DisplayName("Testbox tests")
    @Description("Open main page for elements and test textbox")
    void textBoxTest() {
        step ("Open elements page", () -> {
            open("https://demoqa.com/elements");
        });
    }

}

