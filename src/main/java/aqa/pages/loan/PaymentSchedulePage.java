package aqa.pages.loan;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;

public class PaymentSchedulePage {

    private SelenideElement closeButton = $(By.cssSelector("div.popup_close.close"));
    private SelenideElement schedulePopUp = $(By.xpath(".//div[contains(@class, 'no_close _rel')]"));
    private ElementsCollection fullPaymentScheduleTable = $$(By.xpath(".//*[contains(@class, 'inst_holder')]//tr"));

    public PaymentSchedulePage() {
    }

    @Step("Validate if Payment schedule contains {months} months")
    public PaymentSchedulePage validatePaymentScheduleSize(int months) {
        fullPaymentScheduleTable.shouldHaveSize(months);
        return page(this);
    }

    @Step("Close Payment schedule")
    public PaymentSchedulePage closePaymentSchedule() {
        schedulePopUp.waitUntil(visible, 3000);
        sleep(1000);
        closeButton.click();
        schedulePopUp.waitUntil(disappears, 3000);
        schedulePopUp.shouldBe(not(visible));
        return page(this);
    }
}
