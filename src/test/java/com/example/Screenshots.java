package com.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class Screenshots {

    private Page page;
    private Locator locator;

    public void screenshots(){

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot1.png")));


        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot2.png"))
                .setFullPage(true));

        locator
                .screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

    }

}
