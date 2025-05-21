package com.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    @BeforeAll
    public void beforeAll() {
        playwright = Playwright.create();                                               // Создание Playwright

        BrowserType.LaunchOptions browserOptions = new BrowserType.LaunchOptions()      // Создание настроек браузера
                .setHeadless(false)
                .setSlowMo(500);

        browser = playwright.chromium().launch(browserOptions);                         // Создание браузера с настройками
    }

    @BeforeEach
    public void setUp() {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()      // Создание настроек контекста браузера
                .setRecordVideoDir(Paths.get("/etc/video/"));

        browserContext = browser.newContext(contextOptions);                            // Создание контекста браузера
        page = browserContext.newPage();                                                // Создание страницы
    }

    @Test
    public void simpleTest(){
        page.navigate("https://playwright.dev/");                                   // Открытие URL

        assertThat(page).hasTitle(Pattern.compile("Playwright"));                 // Проверка (assert)
        assertThat(page).not().hasTitle(Pattern.compile("Java"));

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(".\\build\\screenshots\\screen1.png")));  // Создание скриншота

        Locator getStarted = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Get started"));                    // Получение локатора

        getStarted.click();                                                             // Клик по элементу

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(".\\build\\screenshots\\screen2.png")));  // Создание ещё одного скриншота

        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation")))
                .isVisible();
    }

    @AfterEach
    public void tearDown() {
        browserContext.close();
    }

    @AfterAll
    public void afterAll() {
        browser.close();
        playwright.close();
    }

}
