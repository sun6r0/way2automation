package tests;

import com.codeborne.selenide.WebDriverRunner;
import helpers.CookieProvider;
import helpers.ParametersProvider;
import org.testng.Assert;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import pages.MainPage;

import java.io.IOException;
import java.util.Set;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * Тест сьют для главной страницы
 */
public class MainPageTests extends TestsBase{

    @Test
    @Title("Авторизация с сохранением куки в файл")
    public final void loginAndSaveCookie() throws IOException {
        String name = ParametersProvider.getProperty("name");
        String password = ParametersProvider.getProperty("password");
        MainPage mainpage = open(webUrl, MainPage.class).checkOpen();
        mainpage.signIn(name, password);
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        Assert.assertFalse(cookies.isEmpty());
        CookieProvider.saveCookieInFile();
        closeWebDriver();
    }

//    @Test
//    public final void loginWithCookie() throws IOException {
//        Selenide.open(webUrl);
//        CookieProvider.addCookieFromFile();
//    }
}
