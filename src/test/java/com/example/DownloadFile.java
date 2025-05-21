package com.example;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class DownloadFile {

    Playwright playwright;
    Page page;

    public void setDownloadFolder() {

        // Настройка папки для скачивания по умолчанию
        playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setDownloadsPath(Paths.get("/etc/"))
        );

        playwright.close();
    }

    public void downloadWithKnownDownloadAction(){

        // Создание перехватчика события
        Download download = page.waitForDownload(() -> {
            // Сделать действие, приводящее к инициации загрузки
            page.getByText("Download file").click();
        });

        // Можем пересохранить файл из временной папки, указанной в setDownloadsPath() в указанную папку
        download.saveAs(Paths.get("/path/to/save/at/", download.suggestedFilename()));

    }

    public void downloadWithUnknownDownloadAction(){

        page.onDownload(download -> download.saveAs(Paths.get("/path/")));

    }



}
