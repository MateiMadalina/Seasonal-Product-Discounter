package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;

import java.util.Collection;
import java.util.List;

public class ProductsUi extends UiBase {
    private final ProductBrowser productBrowser;

    public ProductsUi(ProductBrowser productBrowser, String title, boolean requireAuthentication) {
        super(title, requireAuthentication);
        this.productBrowser = productBrowser;
    }

    @Override
    public void run() {
        printProducts("All products:", productBrowser.getAll());
    }

    private static void printProducts(String text, List<Product> products) {
        System.out.println(text + ": ");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}


