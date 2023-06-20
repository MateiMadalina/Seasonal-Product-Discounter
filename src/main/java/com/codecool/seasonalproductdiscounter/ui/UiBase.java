package com.codecool.seasonalproductdiscounter.ui;

public abstract class UiBase {
    private final String title;
    private final boolean requireAuthentication;

    protected UiBase(String title, boolean requireAuthentication) {
        this.title = title;
        this.requireAuthentication = requireAuthentication;
    }

    public boolean isRequireAuthentication() {
        return requireAuthentication;
    }

    public void displayTitle() {
        System.out.println(title);
    }

    public abstract void run();
}

