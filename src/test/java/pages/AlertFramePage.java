package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AlertFramePage {

    /**
     * Url страницы.
     */
    public static final String PAGE_URL = "/alert.php";

    /**
     * Панель вкладок с алертами
     */
    private final SelenideElement alertsTabs = $("div.container.responsive-tabs-default");

    /**
     * Вкладка с InputAlert.
     */
    private final SelenideElement inputAlertTab = alertsTabs.find("ul li:nth-child(2) a");

    /**
     * Вкладка с SimpleAlert.
     */
    private final SelenideElement simpleAlertTab = alertsTabs.find("ul li a");

    /**
     * Окно SimpleAlert.
     */
    private final SelenideElement simpleAlertFrame = $("div[id='example-1-tab-1'] iframe");

    /**
     * Окно InputAlert
     */
    private final SelenideElement inputAlertFrame = $("div[id='example-1-tab-2'] iframe");

    /**
     * Кнопка вызова алерта
     */
    private final SelenideElement alertButton = $("body > button");

    /**
     * Сообщение в окне InputAlert
     */
    private final SelenideElement alertResultMessage = $(byId("demo"));

    /**
     * Проверка, что страница открыта, по url и
     */
    public AlertFramePage checkOpen() {
        Assert.assertTrue(url().contains(PAGE_URL));
        Assert.assertTrue(inputAlertTab.isDisplayed());
        return this;
    }

    /**
     * Выбрать вкладку input alert
     */
    @Step("Выбрать вкладку input alert")
    public final AlertFramePage chooseInputAlert(){
        inputAlertTab.shouldBe(Condition.enabled).click();
        return this;
    }

    /**
     * Выбрать вкладку simple alert
     */
    @Step("Выбрать вкладку simple alert")
    public final AlertFramePage chooseSimpleAlert(){
        simpleAlertTab.shouldBe(Condition.enabled).click();
        return this;
    }

    /**
     * Нажать кнопку получения алерта вида InputAlert
     */
    @Step("Нажать кнопку получения алерта InputAlert")
    public final AlertFramePage inputAlertButtonClick(){
        switchTo().frame(inputAlertFrame);
        alertButton.shouldBe(Condition.enabled).click();
        return this;
    }

    /**
     * Нажать кнопку получения алерта вида SimpleAlert
     */
    @Step("Нажать кнопку получения алерта SimpleAlert")
    public final AlertFramePage simpleAlertButtonClick(){
        switchTo().frame(simpleAlertFrame);
        alertButton.shouldBe(Condition.enabled).click();
        return this;
    }

    /**
     * Получить текст алерта и нажать ОК
     */
    @Step("Получить текст алерта")
    public final String getAlertText() {
        Alert alert = switchTo().alert();
        String allertText = alert.getText();
        alert.accept();
        return allertText;
    }

    /**
     * Заполнить форму алерта и нажать ОК
     * @param text текст, введенный в инпут алерта
     */
    @Step("Заполнить форму алерта")
    public final void fillAlertForm(final String text){
        Alert alert = switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    /**
     * Получить выведенное после алерта сообщение
     */
    @Step("Получить выведенное после алерта сообщение")
    public final String getAlertMessage(){
        return alertResultMessage.getText();
    }
}
