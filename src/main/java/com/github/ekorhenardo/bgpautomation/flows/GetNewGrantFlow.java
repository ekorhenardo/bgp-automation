package com.github.ekorhenardo.bgpautomation.flows;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GetNewGrantFlow {
    private final Page page;

    public GetNewGrantFlow(Page page) {
        this.page = page;
    }

    public void applicationForm() {
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Get new grant")).click();
        page.getByText("IT", new Page.GetByTextOptions().setExact(true)).click();
        page.getByText("Bring my business overseas or").click();
        page.getByText("Market Readiness Assistance or Business Adaptation Grant").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
    }
}
