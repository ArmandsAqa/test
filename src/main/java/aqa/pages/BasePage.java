package aqa.pages;

import com.codeborne.selenide.Configuration;

public class BasePage {

    protected BasePage() {
        Configuration.baseUrl = "https://www.expresscredit.co.zm";
        Configuration.startMaximized = true;
    }
}
