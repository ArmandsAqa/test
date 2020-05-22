package aqa.test;

import aqa.pages.configurations.SelenideConfiguration;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    protected void setup() {
        new SelenideConfiguration()
                .setAllureFilter();
    }
}
