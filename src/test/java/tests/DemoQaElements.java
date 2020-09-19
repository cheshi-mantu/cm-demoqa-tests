package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import utils.RandomUtils;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.AttachmentsHelper.attachAsText;
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
    @DisplayName("Check if elements page can be opened")
    @Description("Open main page, click on Elements widget and check page is loaded by class .main-header")
    void elementsPageIsWorking() {
        step ("PREP: Open main page", () -> {
            open(demoqaUrl);
        });
        step ("ACT: proceed to /elements", () -> {
            $(".card-body").shouldHave(text("Elements")).click();
        });
        step ("CHECK: if Elements page is opened", () -> {
            $(".main-header").shouldHave(text("Elements"));
        });
    }

    @Test
    @DisplayName("Text box test")
    @Description("Open elements page, go to text box page click 1st item (home), check Home is checked")
    void textBoxTest() {
        step ("PREP: Open Elements page", () -> {
            open(demoqaUrl + "/elements");
        });
        step ("PREP: Open text-box page by id=item-0", () -> {
            $("#item-0").click();
        });
        step ("CHECK: text-box area is properly opened: " +
                "main header has text 'Text Box', label 'Full Name' is present", () -> {
            $(".main-header").shouldHave(text("Text Box"));
            $("#userName-label").should(exist);
        });
        step ("PREP: generate random data to fill the form", () -> {
            fullName = RandomUtils.getRandomString(5) + " " + RandomUtils.getRandomString(6);
            email = RandomUtils.getRandomEmail();
            address = RandomUtils.getRandomString(27);
            addressExt = RandomUtils.getRandomString(15);
            attachAsText("FullName", fullName);
            attachAsText("email", email);
            attachAsText("address", address);
            attachAsText("extended address", addressExt);
        });
        step ("ACT: fill the form with random data", () -> {
            $("#userName").setValue(fullName);
            $("#userEmail").setValue(email);
            $("#currentAddress").setValue(address);
            $("#permanentAddress").setValue(addressExt);
            $("#submit").click();
        });
        step ("CHECK: check submitted data within id=output", () -> {
            $("#output").shouldHave(text(fullName));
            $("#output").shouldHave(text(email));
            $("#output").shouldHave(text(address));
            $("#output").shouldHave(text(addressExt));
        });
    }

    @Test
    @DisplayName("Checkboxes test")
    @Description("Expand full tree and check Notes, Private and Excel file, " +
            "then assert that #result has these names in the text")
    void checkBoxSeveralElementsCheck() {
        step ("PREP: Open Check box page ", () -> {
            open(demoqaUrl + "/checkbox");
        });
        step ("CHECK: check box page is properly opened: " +
                "main header has text 'Check Box'", () -> {
            $(".main-header").shouldHave(text("Check Box"));
        });
        step ("ACT: Expand full tree for check boxes by clicking '+' sign on the top right", () -> {
            $("[aria-label='Expand all']").click();
        });
        step ("CHECK/ACT: if tree is expanded, searching for target check boxes labels: " +
                "Notes, Private and Excel file.doc", () -> {
            $$(".rct-title").findBy(text("Notes")).click();
            $$(".rct-title").findBy(text("Private")).click();
            $$(".rct-title").findBy(text("Excel file.doc")).click();
        });
        step ("CHECK: #result should contain Notes, Private and excelFile" , () -> {
            $("#result").shouldBe(visible);
            $("#result").shouldHave(text("Notes"));
            $("#result").shouldHave(text("Private"));
            $("#result").shouldHave(text("excelFile"));

        });
    }

    @Test
    @DisplayName("Radio buttons tests")
    @Description("Enable Yes, then Impressive, then check No is disabled")
    void radioButtonsTests() {
        step ("PREP: Open Radio button page ", () -> {
            open(demoqaUrl + "/radio-button");
        });
        step ("CHECK: if radio button page is properly opened: " +
                "main header has text 'Radio button'", () -> {
            $(".main-header").shouldHave(text("Radio button"));
        });
        step ("ACT: Click (enable) Yes option for radio button", () -> {
            $("#yesRadio").sibling(0).click();
        });
        step ("CHECK: .text-success area contains word Yes", () -> {
            $(".text-success").shouldBe(visible);
            $(".text-success").shouldHave(text("Yes"));
        });
        step ("ACT: Click (enable) Impressive option for radio button", () -> {
            $("#impressiveRadio").sibling(0).click();
        });
        step ("CHECK: .text-success area contains word Impressive", () -> {
            $(".text-success").shouldBe(visible);
            $(".text-success").shouldHave(text("Impressive"));
        });
        step ("CHECK: radio option No is disabled", () -> {
            $("#noRadio").shouldBe(disabled);
        });
    }
    @Test
    @DisplayName("Web tables line delete test")
    @Description("Delete line for the name from NAME_TO_DELETE variable")
    void webTablesDeleteTest() {
        final String NAME_TO_DELETE = "Cantrel";
        step ("PREP: Open 'Web tables' page ", () -> {
            open(demoqaUrl + "/webtables");
        });
        step ("CHECK: if Web tables page is properly opened: " +
                "main header has text 'Web tables'", () -> {
            $(".main-header").shouldHave(text("Web tables"));
        });
        step ("ACT: Find line with " + NAME_TO_DELETE + "Press delete icon for", () -> {
            //both next two lines work equally but let's keep the one that does not use indexes
//            $$(".rt-tr-group").findBy(text(NAME_TO_DELETE)).$("div div div div span",1).shouldHave(attribute("Title", "Delete")).click();
            $$(".rt-tr-group").findBy(text(NAME_TO_DELETE)).$$("div div div div span").findBy(attribute("Title", "Delete")).click();
        });
        step ("CHECK: table should not contain lines with " + NAME_TO_DELETE, () -> {
            $(".rt-table").shouldNotHave(text(NAME_TO_DELETE));
        });

    }

}

