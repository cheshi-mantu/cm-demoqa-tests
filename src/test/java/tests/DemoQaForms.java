package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.AttachmentsHelper.attachScreenshot;
import static helpers.Environment.demoqaUrl;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Epic("demoqa.com tests")
@Feature("Elements")
@Owner("egorivanov")
@Tag("form_tests")

class DemoQaForms extends TestBase {

    String firstName = "";
    String lastName = "";
    String email = "";
    String address = "";
    String addressExt = "";

    @Test
    @DisplayName("Forms tests")
    @Description("Open forms page, make tests for filling forms and checks")
    void formTest() {
        step ("Open form training page", () -> {
            open(demoqaUrl + "/automation-practice-form");
            parameter("Page URL", demoqaUrl + "/automation-practice-form");
        });
        step ("Fill name and email fields", () -> {
            firstName = RandomUtils.getRandomString(7);
            lastName = RandomUtils.getRandomString(10);
            email = RandomUtils.getRandomEmail();

            Allure.addAttachment("First Name", firstName);
            Allure.addAttachment("Last Name", lastName);
            Allure.addAttachment("Email address", email);

            $("#firstName").val(firstName);
            $(":focus").pressTab();
            $(":focus").val(lastName);
            $(":focus").pressTab();
            $("#userEmail").val(email);
        });
//        step ("Fill name fields", () -> {
//            firstName = RandomUtils.getRandomString(7);
//            lastName = RandomUtils.getRandomString(10);
//            Allure.addAttachment("First Name", firstName);
//            Allure.addAttachment("Last Name", lastName);
//            $("#firstName").val(firstName);
//            $(":focus").pressTab();
//            $(":focus").val(lastName);
//        });

    }

}

