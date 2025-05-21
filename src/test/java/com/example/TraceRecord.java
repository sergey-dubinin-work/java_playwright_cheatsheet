package com.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class TraceRecord {

    private Playwright playwright = Playwright.create();
    private BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setSlowMo(1000);
    private Browser browser = playwright.chromium().launch(options);
    private BrowserContext context;

    @BeforeEach
    void setUp() {

        context = browser.newContext();

        context.tracing().start(               // Включение трассировки
                new Tracing.StartOptions()      // Задание настроек трассировки
                        .setScreenshots(true)   // Сохранение скриншотов при каждом действии (click, fill)
                        .setSnapshots(true)     // Сохранение состояний DOM дерева до и после каждого действия
                        .setSources(true)       // Сохранение загруженных JS и CSS файлов
        );

    }

    @Test
    public void recordTrace(){

        Page page = context.newPage();
        page.navigate("https://playwright.dev/");

        page.getByText("Get started").click();

        page.getByText("Installing Playwright").last().click();

    }

    @AfterEach
    void tearDown() {

        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));

        context.close();
        playwright.close();

        // Выполнить в консоли: npx playwright show-trace trace.zip
    }

}
