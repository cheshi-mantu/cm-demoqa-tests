package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.AttachmentsHelper.attachScreenshot;
import static helpers.Environment.demoqaUrl;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Epic("demoqa.com tests")
@Feature("Elements")
@Owner("egorivanov")
@Tag("form_tests")

class DemoQaForms extends TestBase {

    String firstName = RandomUtils.getRandomString(7);
    String lastName = RandomUtils.getRandomString(10);
    String email = firstName + "." + lastName + "@email.xyz";

    String gender = "Male";

    String phoneNumber = "1234567890";
    String birthDay = "16 April,1965";
    String subjectsField = "Computer Science";
    String address = "Some street, some house, some building, some appartment";
    String addressState = "Uttar Pradesh";
    String addressCity = "Agra";

    @Test
    @DisplayName("Forms tests")
    @Description("Open forms page, make tests for filling forms and checks")
    void formTest() {
        step ("Open form training page", () -> {
            open(demoqaUrl + "/automation-practice-form");
            parameter("Page URL", demoqaUrl + "/automation-practice-form");
        });
        step ("Fill name and email fields", () -> {

            Allure.addAttachment("First Name", firstName);
            Allure.addAttachment("Last Name", lastName);
            Allure.addAttachment("Email address", email);

            $("#firstName").val(firstName);
            $(":focus").pressTab();
            $(":focus").val(lastName);
            $(":focus").pressTab();
            $("#userEmail").val(email);
        });
        step ("Fill gender information", () -> {
            Allure.addAttachment("Selecting gender", "Male");
            $("#gender-radio-1").sibling(0).click();
        });
        step ("Fill phone number information", () -> {
                    Allure.addAttachment("Phone number", phoneNumber);
                    $("#userNumber").val(phoneNumber);
                    $(":focus").pressTab();
        });
        step ("Fill the birth date information", () -> {
            Allure.addAttachment("Birth date", birthDay);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").$(by("value", "1965")).click();
            $(".react-datepicker__month-select").$(byText("April")).click();
            $(".react-datepicker__day--016").click();
        });
        step ("Fill subjects", () -> {

            Allure.addAttachment("Subjects", subjectsField);
            $("#subjectsContainer").click();
            $(":focus").val("C");
            $(byText(subjectsField)).click();
        });
        step ("Fill hobbies", () -> {
            Allure.addAttachment("Hobby", "Reading");
            $("#hobbies-checkbox-2").sibling(0).click();
        });
        step ("File upload test", () -> {
            File uploadFile = new File("./src/test/resources/files/upload.png");
            $("#uploadPicture").uploadFile(uploadFile);
        });
        step ("Fill current address", () -> {
            Allure.addAttachment("Address", address);
            $("#currentAddress").val(address);
        });
        step ("Select the state", () -> {
            Allure.addAttachment("State", "Uttar Pradesh");
            $("#state").click();
            $(byText( "Uttar Pradesh")).click();
        });
        step ("Select the City", () -> {
            Allure.addAttachment("City", "Agra");
            $("#city").click();
            $(byText( "Agra")).click();
        });
        step ("Submit the form", () -> {
            $("#submit").click();
        });
        step ("[ASSERT] Modal table body contains name", () -> {
            $(".modal-body").shouldHave(text(firstName));
        });
        step ("[ASSERT] Modal table body contains name", () -> {
            $(".modal-body").shouldHave(text(lastName));
            $(".modal-body").shouldHave(text(email));
            $(".modal-body").shouldHave(text(phoneNumber));
            $(".modal-body").shouldHave(text(gender));
            $(".modal-body").shouldHave(text(birthDay));
            $(".modal-body").shouldHave(text(subjectsField));
            $(".modal-body").shouldHave(text(address));
            $(".modal-body").shouldHave(text(addressState));
            $(".modal-body").shouldHave(text(addressCity));
        });



        sleep(5000);
    }

}

