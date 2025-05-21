package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

public class Locators {

    private Page page;

    public void locators() {

        // .getByRole()

        Locator heading = page.getByRole(AriaRole.HEADING,
                        new Page.GetByRoleOptions().setName("Sign up"));

        Locator link = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Get started"));

        Locator checkbox = page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Subscribe"));

        Locator button = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(
                        Pattern.compile("submit", Pattern.CASE_INSENSITIVE)));

        //.getByText()

        Locator byText = page.getByText("Welcome",
                new Page.GetByTextOptions().setExact(true));

        // .getByLabel()

        Locator byLabel = page.getByLabel("Password");

        //.getByPlaceholder()

        Locator byPlaceHolder = page.getByPlaceholder("name@example.com");

        // .getByAltText()

        Locator byAltText = page.getByAltText("playwright logo");

        // .getByTitle()

        Locator locator = page.getByTitle("Issues count");

        // .getByTestId()

        Locator byTestId = page.getByTestId("directions");

        // CSS Локаторы

        Locator cssLocator1 = page.locator("css=button");
        Locator cssLocator2 = page.locator("button");

        // XPATH Локаторы

        Locator xpathLocator1 = page.locator("xpath=//button");
        Locator xpathLocator2 = page.locator("//button");

    }

    public void locatorFilters(){
        // По тексту

        page.getByRole(AriaRole.LISTITEM)                                        // Находим все элементы <li>
                .filter(new Locator.FilterOptions().setHasText("Product 2"))     // Устанавливаем фильтр, чтобы найти элемент(ы) с текстом (можно использовать поиск по regex)
                .getByRole(AriaRole.BUTTON,                                      // Находим кнопку <button> внутри отфильтрованного <li>
                        new Locator.GetByRoleOptions().setName("Add to cart"))
                .click();

        // По потомку

        page.getByRole(AriaRole.LISTITEM)                                                       // Находим все элементы <li>
                .filter(new Locator.FilterOptions()                                             // Устанавливаем фильтр, чтобы найти элемент <li>, имеющий потомка:
                        .setHas(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions()    // с ролью Heading
                                .setName("Product 2"))))                                        // и тестов заголовка;
                .getByRole(AriaRole.BUTTON,                                                     // Находим кнопку внутри обнаруженного элемента <li>
                        new Locator.GetByRoleOptions().setName("Add to cart"))
                .click();

        /*
        * Операторы локатора
        * */

        // Сопоставление внутри локатора

        Locator product = page
                .getByRole(AriaRole.LISTITEM)                                       // Находим элементы <li>
                .filter(new Locator.FilterOptions().setHasText("Product 2"));       // Фильтруем полученные элементы по тексту

        product
                .getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Add to cart"))      // Внутри отфильтрованного элемента находим кнопку
                .click();

        // Можно объединить два локатора вместе, например, чтобы найти кнопку «Сохранить» внутри определенного диалогового окна:

        Locator saveButton = page.getByRole(AriaRole.BUTTON,                                // Находим локатор кнопки с текстом "Save"
                new Page.GetByRoleOptions().setName("Save"));

        Locator dialog = page.getByTestId("settings-dialog");                               // Находим диалоговое окно
        dialog.locator(saveButton).click();                                                 // В найденном диалоговом окне находим кнопку "Save" и кликаем на неё

        // Одновременное сопоставление двух локаторов .and()

        Locator button = page.getByRole(AriaRole.BUTTON).and(page.getByTitle("Subscribe"));

        // Сопоставление одного из двух локаторов .or()

        Locator newEmailEng = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New"));
        Locator newEmailRus = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Новый"));
        newEmailEng.or(newEmailRus).click();

        // Получение элементов из списка

        Locator listItems = page.getByRole(AriaRole.LISTITEM);
        Locator firstLI = listItems.first();
        Locator firstLI_ = listItems.nth(0);

        Locator secondLI = listItems.nth(1);
        Locator lastLI = listItems.last();

    }

}
