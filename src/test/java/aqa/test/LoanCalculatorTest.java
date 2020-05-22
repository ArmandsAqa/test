package aqa.test;

import aqa.pages.loan.LoanApplicationDetailsPage;
import aqa.pages.loan.LongTermLoanPage;
import aqa.pages.loan.PaymentSchedulePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoanCalculatorTest extends BaseTest {

    @DataProvider(name = "credit-conditions")
    private Object[][] creditConditions() {
        return new Object[][]{
                {"3600", "24", "380"},
                {"9000", "20", "1014"},
                {"9000", "12", "1358"},
                {"10500", "3", "5008"}
        };
    }

    @DataProvider(name = "credit-conditions-using-slider")
    private Object[][] creditConditionsUsingSlider() {
        return new Object[][]{
                {"69800", "35", "6399", 100},
                {"53100", "29", "5072", 150},
                {"36400", "23", "3888", 200},
                {"19700", "17", "2528", 250},
                {"1500", "5", "475", 350}
        };
    }

    @Test(dataProvider = "credit-conditions", description = "Calculate monthly pay for amount in period")
    @Parameters({"Amount", "Term length", "Expected monthly pay"})
    public void longTermLoanCalculation(String amount, String termLength, String expectedMonthlyPay) {
        new LongTermLoanPage()
                .inputAmount(amount)
                .inputTermLength(termLength)
                .validateMonthlyPay(expectedMonthlyPay)
                .applyButton();
        new LoanApplicationDetailsPage()
                .validateMonthlyPay(expectedMonthlyPay);
    }

    @Test(dataProvider = "credit-conditions-using-slider", description = "Calculate monthly pay for amount in period")
    @Parameters({"Amount", "Term length", "Expected monthly pay", "Moves the mouse to X offset from the right corner of the element to the left"})
    public void longTermLoanCalculationUsingSlider(String amount, String termLength, String expectedMonthlyPay, int xOffset) {
        new LongTermLoanPage()
                .inputAmountUsingSlider(amount, xOffset)
                .inputTermLengthUsingSlider(termLength, xOffset)
                .validateMonthlyPay(expectedMonthlyPay)
                .applyButton();
        new LoanApplicationDetailsPage()
                .validateMonthlyPay(expectedMonthlyPay);
    }

    @Test(description = "Reset amount if input is less than minimum amount")
    public void userInputsLessThanMinimumAmount() {
        new LongTermLoanPage()
                .inputAmountOutOfAllowedRange("100")
                .validateThatAmountInputFieldContains("1500");
    }

    @Test(description = "Reset amount if input is more than maximum amount")
    public void userInputsMoreThanMaximumAmount() {
        new LongTermLoanPage()
                .inputAmountOutOfAllowedRange("200000")
                .validateThatAmountInputFieldContains("100000");
    }

    @Test(description = "Reset term length if input is more than maximum allowed length")
    public void userInputsMoreThanMaximumAllowedLength() {
        new LongTermLoanPage()
                .inputTermAboveAllowedLength("37")
                .validateThatTermLengthContains("36");
    }

    @Test(description = "See payment schedule")
    public void openPaymentSchedule() {
        new LongTermLoanPage()
                .inputAmount("1500")
                .extendFullPaymentSchedule();
    }

    @Test(description = "See full payment schedule after click on Apply button")
    public void extendPaymentScheduleAfterApplyLongTermLoan() {
        new LongTermLoanPage()
                .inputAmount("2000")
                .applyButton();
        new LoanApplicationDetailsPage()
                .extendFullPaymentSchedule();
    }

    @Test(description = "Validate payment Schedule size")
    public void verifyPaymentScheduleSize() {
        new LongTermLoanPage()
                .inputTermLength("20")
                .inputAmount("2500")
                .extendFullPaymentSchedule();
        new PaymentSchedulePage()
                .validatePaymentScheduleSize(20);
    }

    @Test(description = "Open and close payment schedule")
    public void openAndCloseSchedule() {
        new LongTermLoanPage()
                .extendFullPaymentSchedule();
        new PaymentSchedulePage()
                .closePaymentSchedule();
    }
}
