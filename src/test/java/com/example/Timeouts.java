package com.example;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Timeouts {

    private BrowserContext browserContext;
    private Page page;
    private Locator locator;

    public void commonTimeout(){

        browserContext.setDefaultTimeout(10000);
        page.setDefaultTimeout(10000);

    }

    public void navigationTimeout(){

        browserContext.setDefaultNavigationTimeout(20000);
        page.setDefaultNavigationTimeout(20000);

    }

    public void certainActionTimeout(){

        locator.click(new Locator.ClickOptions().setTimeout(5000));
        page.click("text=Submit", new Page.ClickOptions().setTimeout(5000));

    }

    public void elementWaitTimeout(){

        locator.waitFor(new Locator.WaitForOptions().setTimeout(8000));

    }

    public void assertTimeout(){

        assertThat(locator)
                .isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(5000));

    }

}
