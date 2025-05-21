package com.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Assertions {

    private Page page;
    private Locator locator;

    public void assertions(){

        assertThat(locator).isChecked();

        assertThat(locator).not().isChecked();

        assertThat(locator).hasValue("abc");

        assertThat(locator).not().hasValue("abc");

        assertThat(locator).isDisabled();

        assertThat(locator).isEnabled();

        assertThat(locator).isHidden();

        assertThat(locator).isVisible();

        assertThat(locator).isEditable();

        assertThat(locator).containsText("abc");

        assertThat(locator).hasText("abc");

        assertThat(locator).hasValue("abc");

        assertThat(locator).hasAttribute("text", "abc");

        assertThat(locator).hasClass("clazz");

        assertThat(locator).hasCSS("color", "red");

        assertThat(locator).hasId("abc");

        assertThat(locator).hasCount(5);

        assertThat(locator).hasJSProperty("abc", true);

        assertThat(locator).hasRole(AriaRole.LINK);

        assertThat(locator).isAttached();

        assertThat(locator).isEmpty();

        assertThat(locator).isFocused();

        assertThat(locator).isInViewport();

        assertThat(page).hasTitle("Main page");

        assertThat(page).hasURL("http://example.com/");

    }

}
