package tests;

import helpers.ParametersProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertFramePage;
import pages.MainPage;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

/**
 * Тест сьют для страницы алертов
 */
public class AlertFrameTests extends TestsBase {

    /**
     * Страница с алертами.
     */
    AlertFramePage alertPage;

    /**
     * Авторизация и открытие страницы с алертами
     */
    @BeforeMethod
    public void doPreconditions() {
        String name = ParametersProvider.getProperty("name");
        String password = ParametersProvider.getProperty("password");
        MainPage mainPage = open(webUrl, MainPage.class);
        mainPage.signIn(name, password);
        alertPage = mainPage.openAlertFrame().checkOpen();
    }

    @Test
    @Title("Проверка алерта с инпутом")
    public final void checkAlertWithInput() {
        String text = "TestText";
        alertPage.chooseInputAlert().inputAlertButtonClick();
        alertPage.fillAlertForm(text);
        Assert.assertTrue(alertPage.getAlertMessage().contains(text));
    }

    @Test
    @Title("Проверка алерта без инпута")
    public final void checkSimpleAlert() {
        alertPage.chooseSimpleAlert().simpleAlertButtonClick();
        Assert.assertTrue(alertPage.getAlertText().equals("I am an alert box!"));
    }

    @AfterMethod
    public void doPostconditions() {
        closeWebDriver();
    }
}
