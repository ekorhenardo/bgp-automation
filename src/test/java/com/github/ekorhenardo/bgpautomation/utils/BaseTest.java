package com.github.ekorhenardo.bgpautomation.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import com.github.ekorhenardo.bgpautomation.flows.QAEnvAuthFlow;
import com.github.ekorhenardo.bgpautomation.flows.BGPLoginFlow;
import com.github.ekorhenardo.bgpautomation.flows.GetNewGrantFlow;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void setup() {
        page = browser.newPage();
        page.navigate("https://qa-internet.bgp.onl/");

        new QAEnvAuthFlow(page).login("temp-govtech", "bgPB3Aw3SomeGvtF@lk!");
        new BGPLoginFlow(page).loginToBGP("BGPQETECH6", "S1234567D", "Acceptor", "Tan Ah Kow");
        new GetNewGrantFlow(page).applicationForm();
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }
}
