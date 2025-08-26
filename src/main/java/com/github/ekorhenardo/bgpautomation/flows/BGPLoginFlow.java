package com.github.ekorhenardo.bgpautomation.flows;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class BGPLoginFlow {
    private final Page page;

    public BGPLoginFlow(Page page) {
        this.page = page;
    }

    public void loginToBGP(String uen, String userId, String role, String name) {
        page.locator("a").filter(new Locator.FilterOptions().setHasText("LOG IN")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Entity ID (UEN)")).fill(uen);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("User ID (NRIC, Passport")).fill(userId);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("User Role (Acceptor, Viewer,")).fill(role);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("User Full Name")).fill(name);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In")).click();
    }
}
