package aqa.pages.loan;

import aqa.pages.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoanApplicationDetailsPage extends BasePage {

    private SelenideElement schedulePopUp = $(By.xpath(".//div[contains(@class, 'no_close _rel')]"));
    private SelenideElement extendPaymentScheduleButton = $(By.xpath(".//a[contains(@class, 'schedule_btn inst_schedule_btn')]"));
    private SelenideElement monthlyLoanRepaymentTextField = $(By.xpath(".//span[contains(@class, 'calc_result_pay_cycle next_rollover_fee')]"));

    public LoanApplicationDetailsPage() {
    }

    @Step("Extend full Payment schedule")
    public LoanApplicationDetailsPage extendFullPaymentSchedule() {
        extendPaymentScheduleButton.shouldBe(visible).click(1,1);
        schedulePopUp.waitUntil(Condition.visible, 3000L);
        schedulePopUp.shouldBe(visible);
        return page(this);
    }

    @Step("Check monthly pay")
    public LoanApplicationDetailsPage validateMonthlyPay(String amount) {
        monthlyLoanRepaymentTextField.shouldHave(Condition.text(amount));
        return page(this);
    }
}
