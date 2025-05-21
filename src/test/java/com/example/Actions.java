package com.example;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Actions {

    private Page page;
    private Locator locator;

    public void actionsText(){

        locator.fill("ABC");
        locator.fill("ABC", new Locator.FillOptions()
                .setTimeout(1000)
        );

        locator.pressSequentially("ABC");
        locator.pressSequentially("ABC", new Locator.PressSequentiallyOptions()
                .setTimeout(2000)
                .setDelay(300)
        );

        locator.clear();
        locator.clear(new Locator.ClearOptions()
                .setTimeout(1000)
        );

    }

    public void actionsCheckBoxOrRadioButton(){

        locator.setChecked(true);

        locator.check();

        locator.uncheck();

    }

    public  void actionsDropDownList(){

        locator.selectOption("blue");

        locator.selectOption(new SelectOption().setLabel("Blue"));

    }

    public void actionsMouse(){

        locator.click();

        locator.dblclick();

        locator.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));

        locator.click(new Locator.ClickOptions().setModifiers(Arrays.asList(KeyboardModifier.SHIFT)));

        locator.hover();

        locator.click(new Locator.ClickOptions().setPosition(0, 0));

        locator.click(new Locator.ClickOptions().setForce(true));

    }

    public void actionsKeysCombination(){

        locator.press("Enter");

        locator.press("Control+ArrowRight");

        locator.press("$");

    }

    public void actionsDownloadFile(){

        locator.setInputFiles(Paths.get("myfile.pdf"));

        locator.setInputFiles(new Path[] {Paths.get("file1.txt"), Paths.get("file2.txt")});

        locator.setInputFiles(new Path[0]);



        // Устанавливаем обработчик file chooser заранее
        page.onFileChooser(fileChooser -> {
            // Указываем путь к файлу для загрузки
            fileChooser.setFiles(Paths.get("C:/files/test-file.txt"));
        });

        // Действие, которое приводит к появлению <input type="file"> (например, клик по кнопке "Загрузить файл")
        page.click("text=Upload a file");



        FileChooser fileChooser = page.waitForFileChooser(() -> {
            page.getByLabel("Upload file").click();
        });
        fileChooser.setFiles(Paths.get("myfile.pdf"));


    }

    public void actionsDragAndDrop(){
        page.locator("#item-to-be-dragged").dragTo(page.locator("#item-to-drop-at"));
    }

    public void actionsScrolling(){
        locator.scrollIntoViewIfNeeded();
        page.mouse().wheel(0, 10);
    }

}
