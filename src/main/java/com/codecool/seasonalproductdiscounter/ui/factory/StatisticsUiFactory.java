package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.ui.StatisticsUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public class StatisticsUiFactory extends UiFactoryBase {
    private final ProductStatistics productStatistics;

    public StatisticsUiFactory(ProductStatistics productStatistics) {
        this.productStatistics = productStatistics;
    }

    @Override
    public String getUiName() {
        return "Statistics";
    }

    @Override
    public UiBase create() {
        return new StatisticsUi(productStatistics, getUiName(), true);
    }
}

