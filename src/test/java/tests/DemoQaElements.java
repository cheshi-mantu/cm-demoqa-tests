package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import utils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.AttachmentsHelper.attachAsText;
import static helpers.AttachmentsHelper.attachScreenshot;
import static helpers.Environment.*;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Epic("Demo QA - demoqa.com")
@Feature("Elements")
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
@Owner("egorivanov")
@Issue("DEM-1")
class DemoQaElements extends TestBase {
    String fullName = "";
    String email = "";
    String address = "";
    String addressExt = "";

    @Test
    @DisplayName("Check if elements page can be opened")
    @Description("Open main page, click on Elements widget and check page is loaded by class .main-header")
    @Severity(SeverityLevel.TRIVIAL)
    void elementsPageIsWorking() {
        parameter("targetUrl", demoqaUrl+"/elements");
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
    @DisplayName("text box test")
    @Story("Automated tests for elementstext boxCheck boxRadio buttonWeb tablesButtonsLinksUpload and downloadDynamic properties")
    @Description("Open elements page, go to text box page click 1st item (home), check Home is checked")
    @Severity(SeverityLevel.NORMAL)
    void textBoxTest() {
        parameter("targetUrl", demoqaUrl+"/elements");

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
    @DisplayName("Checkbox test")
    @Story("Automated tests for elementstext boxCheck boxRadio buttonWeb tablesButtonsLinksUpload and downloadDynamic properties")
    @Description("Expand full tree and check Notes, Private and Excel file, " +
            "then assert that #result has these names in the text")
    @Severity(SeverityLevel.NORMAL)
    void checkBoxSeveralElementsCheck() {
        parameter("targetUrl", demoqaUrl+"/checkbox");
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
    @Severity(SeverityLevel.NORMAL)
    void radioButtonsTests() {
        parameter("targetUrl", demoqaUrl+"/radio-button");
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
    @Severity(SeverityLevel.CRITICAL)
    void webTablesDeleteTest() {
        parameter("targetUrl", demoqaUrl+"/webtables");
        final String NAME_TO_DELETE = "Cantrel"; // TODO: make available + NAME_TO_DELETE through System.Properties
        step ("PREP: Following string is used in the test", () -> {
            attachAsText("NAME_TO_DELETE", NAME_TO_DELETE);
        });
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

    @Test
    @DisplayName("Web tables search test")
    @Description("Filter web table by STRING_TO_FILTER_BY, check content by STRING_TO_CHECK")
    @Severity(SeverityLevel.CRITICAL)
    void webTablesSearchTest() {
        parameter("targetUrl", demoqaUrl+"/webtables");
        final String STRING_TO_FILTER_BY = "Legal"; // TODO: make available STRING_TO_FILTER_BY through System.Properties
        final String STRING_TO_CHECK = "Gentry"; // TODO: make available STRING_TO_CHECK through System.Properties
        final String STRING_TO_CHECK_IS_ABSENT = "Cantrell"; // TODO: make available STRING_TO_CHECK_IS_ABSENT through System.Properties
        step ("PREP: Following strings are used in the test", () -> {
            attachAsText("Filtering table by STRING_TO_FILTER_BY with value", STRING_TO_FILTER_BY);
            attachAsText("Checking result by STRING_TO_CHECK with value", STRING_TO_CHECK);
        });
        step ("PREP: Open 'Web tables' page ", () -> {
            open(demoqaUrl + "/webtables");
        });
        step ("CHECK: if Web tables page is properly opened: " +
                "main header has text 'Web tables'", () -> {
            $(".main-header").shouldHave(text("Web tables"));
        });

        step ("ACT: enter search string " + STRING_TO_FILTER_BY + " to dedicated field" , () -> {
            $("#searchBox").setValue(STRING_TO_FILTER_BY).pressEnter();
        });
        step ("CHECK: table should contain lines with " + STRING_TO_CHECK, () -> {
            $(".rt-table").shouldNotHave(text(STRING_TO_CHECK_IS_ABSENT));
            $(".rt-table").shouldHave(text(STRING_TO_CHECK));

        });

    }
    @Test
    @DisplayName("Web table add record")
    @Description("Invoke registration form modal dialogue, " +
            "fill the fields with randomly generated data" +
            "Submit form by pressing Submit button")
    @Severity(SeverityLevel.BLOCKER)
    void webTablesAddNewRecordTest() {
        parameter("targetUrl", demoqaUrl+"/webtables");
        final String FIRST_NAME = RandomUtils.getRandomString(5);
        final String LAST_NAME = RandomUtils.getRandomString(12);
        final String EMPLOYEE_EMAIL = FIRST_NAME + "." + LAST_NAME + "@maildomain.com";
        final String EMPLOYEE_AGE = String.valueOf(RandomUtils.getRandomInt(18, 90));
        final String EMPLOYEE_SALARY = String.valueOf(RandomUtils.getRandomInt(1000, 9000));
        final String EMPLOYEE_DEPARTMENT = "Cleaning";

        step ("PREP: Generating random data to fill the form for adding new record", () -> {
            attachAsText("First name", FIRST_NAME);
            attachAsText("Last name", LAST_NAME);
            attachAsText("email", EMPLOYEE_EMAIL);
            attachAsText("Employee's age", EMPLOYEE_AGE);
            attachAsText("Employee's salary", EMPLOYEE_SALARY);
            attachAsText("Employee's department", EMPLOYEE_DEPARTMENT);
        });
        step ("PREP: Open 'Web tables' page ", () -> {
            open(demoqaUrl + "/webtables");
        });
        step ("CHECK: if Web tables page is properly opened: " +
                "main header has text 'Web tables'", () -> {
            $(".main-header").shouldHave(text("Web tables"));
        });

        step ("ACT: press Add button to add new record" , () -> {
            $("#addNewRecordButton").click();
        });
        step ("CHECK: dialogue window (form) should be opened", () -> {
            $(".modal-content").shouldBe(visible);
            $(".modal-content").shouldHave(text("Registration form"));
        });
        step ("ACT: Filling registration form fields with randomly generated data and submit form" +
                "by pressing Submit button" , () -> {
            $("#firstName").setValue(FIRST_NAME);
            $("#lastName").setValue(LAST_NAME);
            $("#userEmail").setValue(EMPLOYEE_EMAIL);
            $("#age").setValue(EMPLOYEE_AGE);
            $("#salary").setValue(EMPLOYEE_SALARY);
            $("#department").setValue(EMPLOYEE_DEPARTMENT);
            attachScreenshot(FIRST_NAME + "_" + LAST_NAME);
            $("#submit").click();
        });
        step ("CHECK: Modal form disappeared, table is updated with new line", () -> {
            $(".modal-content").shouldNotBe(visible);
            $(".rt-table").shouldHave(text(FIRST_NAME));
            $(".rt-table").shouldHave(text(LAST_NAME));
            $(".rt-table").shouldHave(text(EMPLOYEE_EMAIL));
            $(".rt-table").shouldHave(text(EMPLOYEE_AGE));
            $(".rt-table").shouldHave(text(EMPLOYEE_SALARY));
            $(".rt-table").shouldHave(text(EMPLOYEE_DEPARTMENT));
        });
    }
    @Test
    @DisplayName("Buttons tests")
    @Description("Double left click, right click, single left click")
    @Severity(SeverityLevel.NORMAL)
    void buttonsClicksTests() {
        parameter("targetUrl", demoqaUrl+"/buttons");
        step ("PREP: Open Buttons page ", () -> {
            open(demoqaUrl + "/buttons");
        });
        step ("CHECK: if Buttons page is properly opened: " +
                "main header has text 'Buttons'", () -> {
            $(".main-header").shouldHave(text("Buttons"));
        });
        step ("ACT: Double click the button with text 'Double click me' by id", () -> {
            $("#doubleClickBtn").doubleClick();
        });
        step ("CHECK: message appeared and contains text 'You have done a double click'", () -> {
            $("#doubleClickMessage").shouldBe(visible);
            $("#doubleClickMessage").shouldHave(text("You have done a double click"));
        });
        step ("ACT: Perform context click for the button with text 'Right click me' by id", () -> {
            $("#rightClickBtn").contextClick();
        });
        step ("CHECK: message appeared and contains text 'You have done a right click'", () -> {
            $("#rightClickMessage").shouldBe(visible);
            $("#rightClickMessage").shouldHave(text("You have done a right click"));
        });
        step ("ACT: Perform left click for the button with text 'Click me' by text as id is dynamic", () -> {
            $$("button.btn.btn-primary").findBy(exactText("Click Me")).click();
        });
        step ("CHECK: message appeared and contains text 'You have done a dynamic click'", () -> {
            $("#dynamicClickMessage").shouldBe(visible);
            $("#dynamicClickMessage").shouldHave(text("You have done a dynamic click"));
        });
    }

}