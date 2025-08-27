package com.github.ekorhenardo.bgpautomation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.ekorhenardo.bgpautomation.utils.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ContactDetailsSectionTest extends BaseTest {
    @BeforeEach
    void navigateToContactTab() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Details")).click();
    }

    @Test
    void testAC1_mainContactPersonSubsection() {
        // Verify the presence of all fields in the Main Contact Person subsection
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Main Contact Person"))).isVisible();
        assertThat(page.locator("#react-contact_info-name-label")).containsText("Name");;
        assertThat(page.locator("#react-contact_info-designation-label")).containsText("Job Title");
        assertThat(page.getByText("Contact No")).isVisible();
        assertThat(page.locator("#react-contact_info-primary_email-label")).containsText("Email");
        assertThat(page.getByText("Alternate Contact Person's")).isVisible();
        assertThat(page.getByText("Mailing Address")).isVisible();

        // Fill in the fields with sample data
        page.locator("#react-contact_info-name").fill("Eko Rhenardo");
        page.locator("#react-contact_info-designation").fill("QA Engineer");
        page.getByTestId("number-field").fill("88991234");
        page.locator("#react-contact_info-primary_email").fill("example@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Alternate Contact Person's")).fill("example1@gmail.com");
    }

    @Test
    void testAC2_mailingAddressPostalCode() {
        // Fill in the Postal Code field and verify that Block/House No. and Street fields are auto-populated
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Postal Code *")).fill("120352");
        page.locator("#react-contact_info-correspondence_address-postal-postal").click();

        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Block/House No."))).hasValue("352");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Street"))).hasValue("CLEMENTI AVENUE 2");
    }

    @Test
    void testAC3_mailingAddressPostalCode() {
        // Check the "Same as registered address in Company Profile" checkbox, and verify that all address fields are auto-populated
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Same as registered address in")).check();

        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Postal Code"))).hasValue("117438");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Block/House No."))).hasValue("10");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Street"))).hasValue("Pasir Panjang Road");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Level"))).hasValue("10");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Unit"))).hasValue("01");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Building Name"))).hasValue("Mapletree Business City");
    }

    @Test
    void testAC4_letterOfOfferAddresseeSubsection() {
        // Verify the presence of all fields in the Letter Of Offer Addressee subsection and fill in the fields with sample data
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Letter Of Offer Addressee"))).isVisible();
        assertThat(page.locator("#react-contact_info-offeree_name-label")).containsText("Name");
        assertThat(page.locator("#react-contact_info-offeree_designation-label")).containsText("Job Title");
        assertThat(page.locator("#react-contact_info-offeree_email-label")).containsText("Email");

        page.locator("#react-contact_info-offeree_name").fill("Eko Rhenardo");
        page.locator("#react-contact_info-offeree_designation").fill("QA Engineer");
        page.locator("#react-contact_info-offeree_email").fill("example@gmail.com");
    }

    @Test
    void testAC5_sameAsMainContactPerson() {
        // Check the "Same as main contact person" checkbox and verify that all fields are auto-populated
        page.locator("#react-contact_info-name").fill("Eko Rhenardo");
        page.locator("#react-contact_info-designation").fill("QA Engineer");
        page.locator("#react-contact_info-primary_email").fill("example@gmail.com");
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Same as main contact person")).check();

        assertThat(page.locator("#react-contact_info-offeree_name")).hasValue("Eko Rhenardo");
        assertThat(page.locator("#react-contact_info-offeree_designation")).hasValue("QA Engineer");
        assertThat(page.locator("#react-contact_info-offeree_email")).hasValue("example@gmail.com");
    }

    @Test
    void testAC6_saveAndReloadPage() {
        // Fill in all fields with sample data, save the form, reload the page, and verify that all data persists
        page.locator("#react-contact_info-name").fill("Eko Rhenardo");
        page.locator("#react-contact_info-designation").fill("QA Engineer");
        page.getByTestId("number-field").fill("88991234");
        page.locator("#react-contact_info-primary_email").fill("example@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Alternate Contact Person's")).fill("example1@gmail.com");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        page.getByText("Draft Saved").isVisible();
        page.reload();

        assertThat(page.locator("#react-contact_info-name")).hasValue("Eko Rhenardo");
        assertThat(page.locator("#react-contact_info-designation")).hasValue("QA Engineer");
        assertThat(page.getByTestId("number-field")).hasValue("88991234");
        assertThat(page.locator("#react-contact_info-primary_email")).hasValue("example@gmail.com");
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Alternate Contact Person's"))).hasValue("example1@gmail.com");
    }
}
