package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import helpers.PageObjectUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    /**
     * Окно регистрации
     */
    private final SelenideElement registrationForm = $(byClassName("ajaxsubmit"));

    /**
     * Элемент с ссылкой на окно авторизации
     */
    private final SelenideElement signInHref = registrationForm.find("div>div>p>a");

    /**
     * Окно авторизации
     */
    private final SelenideElement loginForm = $("form[class='ajaxlogin']");

    /**
     * Поле ввода имени пользователя
     */
    private final SelenideElement nameLoginInput = loginForm.find("fieldset input");

    /**
     * Поле ввода пароля пользователя
     */
    private final SelenideElement passwordLoginInput = loginForm.find("fieldset:nth-child(6) input");

    /**
     * Кнопка подверждения введенных данных для авторизации
     */
    private final SelenideElement submitButton = loginForm.find("div > div.span_1_of_4 > input");

    /**
     * Элемент с ссылкой на переход к странице алертов.
     */
    private final SelenideElement alertFrameHref = $("div.container.margin-top-20 > div.row > " +
            "div:nth-child(6) > ul > li > a");

    /**
     * Лого страницы.
     */
    private final SelenideElement logo = $("div[class='logo']");

    /**
     * Проверка, что страница открыта, по лого и окну регистрации
     */
    public MainPage checkOpen() {
        Assert.assertTrue(logo.isDisplayed(),"Page logo not displayed");
        Assert.assertTrue(registrationForm.isDisplayed());
        return this;
    }

    /**
     * Переход к форме входа по ссылке 'Sign in'.
     * Заполнение формы входа и нажатие кнопки подтверждения.
     *
     * @param name имя пользователя
     * @param password пароль пользователя
     */
    @Step("Вход на сайт по имени и паролю")
    public final void signIn(final String name, final String password){
        signInHref.shouldBe(Condition.visible).click();
        nameLoginInput.shouldBe(Condition.visible).setValue(name);
        removeFocusFromElement(nameLoginInput);
        passwordLoginInput.shouldBe(Condition.visible).setValue(password);
        submitButton.shouldBe(Condition.enabled).click();
        PageObjectUtils.waitForElementToDisappear(loginForm);
    }

    /**
     * Пролистать страницу к выбранному элементу
     *
     * @param element элемент, к которому необходимо пролистать страницу
     */
    @Step("Пролистать страницу к выбранному элементу")
    public final void scrollToElement(final SelenideElement element){
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Убрать фокус с выбранного элемента
     *
     * @param element элемент, с которого необходимо убрать фокус
     */
    @Step("Убрать фокус с элемента")
    public final void removeFocusFromElement(final SelenideElement element){
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("arguments[0].blur()", element);
    }

    /**
     * Открыть alert frame
     */
    @Step("Пролистать страницу к выбранному элементу")
    public final AlertFramePage openAlertFrame(){
        scrollToElement(alertFrameHref);
        alertFrameHref.click();
        return new AlertFramePage();
    }
}
