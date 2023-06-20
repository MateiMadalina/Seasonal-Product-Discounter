package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.ui.UiBase;

public abstract class UiFactoryBase {
    public abstract String getUiName();
    public abstract UiBase create();
}

