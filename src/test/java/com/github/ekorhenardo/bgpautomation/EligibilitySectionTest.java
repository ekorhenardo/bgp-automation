package com.github.ekorhenardo.bgpautomation;

import org.junit.jupiter.api.*;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class EligibilitySectionTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void createPage() {
        page = browser.newPage();
        page.navigate("https://qa-internet.bgp.onl/");
        
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("temp-govtech");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("bgPB3Aw3SomeGvtF@lk!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("submit")).click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText("LOG IN")).click();

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Entity ID (UEN)")).fill("BGPQETECH6");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("User ID (NRIC, Passport")).fill("S1234567D");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("User Role (Acceptor, Viewer,")).fill("Acceptor");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("User Full Name")).fill("Tan Ah Kow");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In")).click();

        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Get new grant")).click();
        page.getByText("IT", new Page.GetByTextOptions().setExact(true)).click();
        page.getByText("Bring my business overseas or").click();
        page.getByText("Market Readiness Assistance or Business Adaptation Grant").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void testLoginPage() {

    }
}
