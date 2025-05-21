package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DifferentBrowsersLaunch {

    private Playwright playwright;
    private BrowserType.LaunchOptions launchOptions;
    private Browser browser;
    private Page page;

    @BeforeAll
    void setUp() {
        playwright = Playwright.create();
        launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false);
    }

    @Test
    public void launchChromiumBrowser() {

        browser = playwright.chromium().launch(launchOptions);

    }

    @Test
    public void launchMicrosoftEdgeBrowser() {

        launchOptions = launchOptions
                .setChannel("msedge");

        browser = playwright.chromium().launch(launchOptions);

    }

    @Test
    public void launchGoogleChromeBrowser() {

        launchOptions = launchOptions
                .setChannel("chrome");

        browser = playwright.chromium().launch(launchOptions);

    }

    @Test
    public void launchMozillaBrowser() throws InterruptedException {

        browser = playwright.firefox().launch(launchOptions);

    }

    @Test
    public void launchSafariBrowser() throws InterruptedException {

        browser = playwright.webkit().launch(launchOptions);

    }

    @AfterEach
    void tearDown() throws InterruptedException {

        page = browser.newPage();

        Thread.sleep(3000);

        browser.close();

    }

    @AfterAll
    void afterAll() {
        playwright.close();
    }
}
