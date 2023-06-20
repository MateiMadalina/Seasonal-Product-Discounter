package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.ui.ProductsUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public class ProductsUiFactory extends UiFactoryBase {
    private final ProductBrowser productBrowser;

    public ProductsUiFactory(ProductBrowser productBrowser) {
        this.productBrowser = productBrowser;
    }

    @Override
    public String getUiName() {
        return "Products";
    }

    @Override
    public UiBase create() {
        return new ProductsUi(productBrowser, getUiName(), true);
    }
}

