package com.example;

import com.microsoft.playwright.Page;

public class Dialog {

    private Page page;

    public void dialog(){

        page.onDialog(dialog -> {
            System.out.println("Текст диалога: " + dialog.message());
            dialog.accept(); // Нажать "OK"
            dialog.dismiss(); // Нажать "Cancel"
            dialog.accept("Введённый текст"); // Ввести текст и нажать OK
        });

    }

}
