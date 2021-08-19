package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;
import static helpers.Environment.demoqaUrl;
import static helpers.Environment.isVideoOn;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        step("Maximized window, added listener for selenide", () -> {
            Configuration.startMaximized = true;
            Configuration.baseUrl = demoqaUrl;
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
            configureSelenide();
        });
    }

    @BeforeEach
    public void BeforeEachAndEveryTest() {
        step("Emppty step - 5", () -> {
            attachAsText("fooText", "@BeforeEach completed successfully");
        });step("Setting up all the tests", () -> {
            System.out.println("This is running before all the tests");
        });
        step("Empty step - 6", () -> {
            attachAsText("fooText", "@BeforeEach completed successfully");
        });

    }


    @AfterEach
    public void afterEach(){
        String sessionId = getSessionId();
        attachScreenshot("Last screenshot");
        attachPageSource();
//        attachAsText("Browser console logs", getConsoleLogs());
        closeWebDriver();
        if (isVideoOn) attachVideo(sessionId);
    }
}