package com.github.ekorhenardo.bgpautomation.flows;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class QAEnvAuthFlow {
    private final Page page;

    public QAEnvAuthFlow(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill(username);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("submit")).click();
    }
}
