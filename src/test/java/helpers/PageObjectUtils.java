package helpers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;

/**
 * Helper class for page objects.
 */
public final class PageObjectUtils {
    /**
     * Not called.
     */
    private PageObjectUtils() {
    }

    /**
     * Wait for the page to open by URL.
     *
     * @param element browser driver
     */
    public static void waitForElement(final SelenideElement element) {
        int timeout = Integer.parseInt(ParametersProvider
                .getProperty("page.load.timeout"));
        element.waitUntil(exist, timeout);
    }

    public static void waitForElementToDisappear(final SelenideElement element) {
        int timeout = Integer.parseInt(ParametersProvider
                .getProperty("page.load.timeout"));
        element.waitUntil(disappear, timeout);
    }
}

