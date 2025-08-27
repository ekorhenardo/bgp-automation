package com.github.ekorhenardo.bgpautomation;

import com.github.ekorhenardo.bgpautomation.utils.BaseTest;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.regex.Pattern;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class EligibilitySectionTest extends BaseTest {
    @Test
    void testAC1_sectionContainsFiveQuestions() {
        assertThat(page.getByText("Is the applicant registered in Singapore?")).isVisible();
        assertThat(page.getByText("Is the applicant's group sales turnover less than or equal to S$100m or is the applicant's group employment size less than or equal to 200?")).isVisible();
        assertThat(page.getByText("Does the applicant have at least 30% local equity?")).isVisible();
        assertThat(page.getByText("Are the target market(s) that you are applying for a new market? A market is considered new if your company's revenue from there has not exceeded $100,000 in any of the last 3 years.")).isVisible();
        assertThat(page.getByText("Are all the following statements true for this project?")).isVisible();
        assertThat(page.getByText("The applicant has not started work on this project")).isVisible();
        assertThat(page.getByText("The applicant has not made any payment to any supplier, vendor, or third party prior to applying for this grant")).isVisible();
        assertThat(page.getByText("The applicant has not signed any contractual agreement with any supplier, vendor, or third party prior to applying for this grant")).isVisible();
    }

    @Test
    void testAC2_questionsRadioButtons() {
        for (int i = 0; i < 5; i++) {
            Locator yesRadioButton = page.locator("label").filter(new Locator.FilterOptions().setHasText("Yes")).nth(i);
            yesRadioButton.click();
            assertThat(yesRadioButton).isChecked();
        }
    }

    @Test
    void testAC3_warningMessage() {
        for (int i = 0; i < 5; i++) {
            Locator noRadioButton = page.locator("label").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^No$"))).nth(i);
            noRadioButton.click();
            assertThat(noRadioButton).isChecked();

            Locator warningMessage = page.locator(".field-warning-text").nth(i);
            assertThat(warningMessage).containsText("The applicant may not meet the eligibility criteria for this grant.");
            assertThat(warningMessage.locator("a")).hasText("FAQ");
            assertThat(warningMessage).containsText(" page for more information on other government grants.");
        }
    }

    @Test
    void testAC4_faqURL() {
        page.locator("label").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^No$"))).nth(0).click();

        Page popup = page.waitForPopup(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("FAQ")).nth(1).click();
        });

        assertThat(popup).hasURL("https://www.gobusiness.gov.sg/business-grants-portal-faq/get-a-grant/");
    }

    @Test
    void testAC5_reloadPersistence() {
        Locator yesRadioButton = page.locator("label").filter(new Locator.FilterOptions().setHasText("Yes"));

        for (int i = 0; i < 5; i++) {
            yesRadioButton.nth(i).click();
            assertThat(yesRadioButton.nth(i)).isChecked();
        }
        
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        page.getByText("Draft Saved").isVisible();
        page.reload();

        for (int i = 0; i < 5; i++) {
            assertThat(yesRadioButton.nth(i)).isChecked();
        }
    }
}
