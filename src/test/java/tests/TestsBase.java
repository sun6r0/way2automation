package tests;

import com.codeborne.selenide.Configuration;
import helpers.ParametersProvider;
import org.testng.annotations.BeforeClass;

public class TestsBase {

    protected String webUrl;

    @BeforeClass
    public final void setEnvironment() {
        webUrl = ParametersProvider.getProperty("web.url");
        Configuration.browser = ParametersProvider.getProperty("browser.name");
        Configuration.baseUrl = webUrl;
    }
}
