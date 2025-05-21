package com.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class VideoRecord {

    private Playwright playwright = Playwright.create();
    private BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setSlowMo(1000);
    private Browser browser = playwright.chromium().launch(options);
    private BrowserContext context;

    @BeforeEach
    void setUp() {
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get(".\\build\\videos"))
        );
    }

    @Test
    public void recordVideo(){

        Page page = context.newPage();
        page.navigate("https://playwright.dev/");

        page.getByText("Get started").click();

        page.getByText("Installing Playwright").last().click();

    }

    @AfterEach
    void tearDown() {
        context.close();
        playwright.close();
    }
}
