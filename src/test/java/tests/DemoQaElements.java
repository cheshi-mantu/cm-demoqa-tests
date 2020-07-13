package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import utils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
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
    String fullName = "";
    String email = "";
    String address = "";
    String addressExt = "";
    @Test
    @DisplayName("Elements page can be opened")
    @Description("Open main page, click on Elements widget by class .main-header")
    void elementsPageIsWorking() {
        step ("PREP: Open main page and proceed to /elements", () -> {
            open(url);
            $(".card-body").shouldHave(text("Elements")).click();
        });
        step ("CHECK: if Elements page is opened", () -> {
            $(".main-header").shouldHave(text("Elements"));
        });
    }
    @Test
    @DisplayName("Testbox tests")
    @Description("Open elements page")
    void textBoxTest() {
        step ("PREP: Open Elements page and proceed to text-box by id=item-0", () -> {
            open(url + "/elements");
        });
        step ("ACT: Open text-box page by id=item-0", () -> {
            $("#item-0").click();
        });
        step ("CHECK: text-box area is properly opened: " +
                "main header has text \'Text Box\', label \'Full Name\' is present", () -> {
            $(".main-header").shouldHave(text("Text Box"));
            $("#userName-label").should(exist);
        });
        step ("PREP: generate random data to fill the form", () -> {
            fullName = RandomUtils.getRandomString(5) + " " + RandomUtils.getRandomString(6);
            email = RandomUtils.getRandomEmail();
            address = RandomUtils.getRandomString(27);
            addressExt = RandomUtils.getRandomString(15);
        });
        step ("ACT: fill the form with random data", () -> {
            $("#userName").setValue(fullName);
            $("#userEmail").setValue(email);
            $("#currentAddress").setValue(address);
            $("#permanentAddress").setValue(addressExt);
            $("#submit").click();
        });
        step ("CHECK: check submited data", () -> {
            $("#output").shouldHave(text(fullName));
            $("#output").shouldHave(text(email));
            $("#output").shouldHave(text(address));
            $("#output").shouldHave(text(addressExt));
        });
    }

}

