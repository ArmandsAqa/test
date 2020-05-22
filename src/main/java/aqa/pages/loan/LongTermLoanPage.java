package aqa.pages.loan;

import aqa.pages.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;

public class LongTermLoanPage extends BasePage {

    private SelenideElement loanAmountTextField = $(By.id("calc_newuser_amount_input_inst_promo"));
    private SelenideElement termLengthTextField = $(By.id("calc_duration_input_inst_promo"));
    private SelenideElement monthlyLoanRepaymentTextField = $(By.xpath(".//span[contains(@class, 'calc_result_pay_cycle next_rollover_fee')]"));
    private SelenideElement applyButton = $(By.id("apply_bnt_inst_regular"));
    private SelenideElement monthsSlider = $(By.xpath(".//div[contains(@class, 'sp1 psp2 msp3')]/following-sibling::div//div[contains(@class, 'ui-slider-horizontal ui-widget')]"));
    private SelenideElement monthsSliderButton = $(By.xpath(".//div[contains(@class, 'sp1 psp2 msp3')]/following-sibling::div//span[contains(@class, 'ui-slider-handle')]"));
    private SelenideElement amountSlider = $(By.xpath(".//div[contains(@class, 'sp1 psp10 msp9')]/following-sibling::div//div[contains(@class, 'ui-slider-horizontal ui-widget')]"));
    private SelenideElement amountSliderButton = $(By.xpath(".//div[contains(@class, 'sp1 psp10 msp9')]/following-sibling::div//span[contains(@class, 'ui-slider-handle')]"));
    private SelenideElement extendPaymentScheduleButton = $(By.xpath(".//a[contains(@class, 'schedule_btn inst_schedule_btn')]"));
    private SelenideElement schedulePopUp = $(By.xpath(".//div[contains(@class, 'no_close _rel')]"));

    public LongTermLoanPage() {
        super();
        open("/long-term-loan");
    }

    @Step("Input amount: {amount}")
    public LongTermLoanPage inputAmount(String amount) {
        loanAmountTextField.clear();
        loanAmountTextField.setValue(amount);
        loanAmountTextField.pressEnter();
        loanAmountTextField.waitUntil(Condition.value(amount), 4000L);
        return page(this);
    }

    @Step("Input amount: {amount}")
    public LongTermLoanPage inputAmountUsingSlider(String amount, int xOffset) {
        int amountSliderWidth = amountSlider.getSize().width;
        actions().dragAndDropBy(amountSliderButton, amountSliderWidth - xOffset, 0)
                .release().build().perform();
        loanAmountTextField.waitUntil(Condition.value(amount), 4000L);
        return page(this);
    }

    @Step("Input months: {months}")
    public LongTermLoanPage inputTermLength(String months) {
        termLengthTextField.click();
        termLengthTextField.clear();
        termLengthTextField.setValue(months);
        termLengthTextField.pressEnter();
        termLengthTextField.waitUntil(Condition.value(months), 4000L);
        return page(this);
    }

    @Step("Input months: {months}")
    public LongTermLoanPage inputTermLengthUsingSlider(String months, int xOffset) {
        int monthSliderWidth = monthsSlider.getSize().width;
        actions().dragAndDropBy(monthsSliderButton, monthSliderWidth - xOffset, 0)
                .release().build().perform();
        termLengthTextField.waitUntil(Condition.value(months), 4000L);
        return page(this);
    }

    @Step("Input months: {months}")
    public LongTermLoanPage inputTermAboveAllowedLength(String months) {
        termLengthTextField.click();
        termLengthTextField.clear();
        termLengthTextField.sendKeys(months);
        termLengthTextField.pressEnter();
        termLengthTextField.waitUntil(Condition.visible, 2000L);
        return page(this);
    }

    @Step("Input amount: {amount}")
    public LongTermLoanPage inputAmountOutOfAllowedRange(String amount) {
        loanAmountTextField.clear();
        loanAmountTextField.sendKeys(amount);
        loanAmountTextField.pressEnter();
        return page(this);
    }

    @Step("Extend full Payment schedule")
    public LongTermLoanPage extendFullPaymentSchedule() {
        extendPaymentScheduleButton.click();
        schedulePopUp.waitUntil(Condition.visible, 3000L);
        schedulePopUp.shouldBe(visible);
        return page(this);
    }

    @Step("Check monthly pay")
    public LongTermLoanPage validateMonthlyPay(String amount) {
        monthlyLoanRepaymentTextField.shouldHave(Condition.text(amount));
        return page(this);
    }

    @Step("Check minimum amount")
    public LongTermLoanPage validateThatAmountInputFieldContains(String amount) {
        loanAmountTextField.waitUntil(Condition.value(amount), 2000L);
        return page(this);
    }

    @Step("Validate term length")
    public LongTermLoanPage validateThatTermLengthContains(String months) {
        termLengthTextField.waitUntil(Condition.value(months), 2000L);
        return page(this);
    }

    public void applyButton() {
        applyButton.click();
    }
}
