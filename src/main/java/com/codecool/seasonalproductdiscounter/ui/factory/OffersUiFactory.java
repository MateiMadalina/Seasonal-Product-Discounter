package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.ui.OffersUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public class OffersUiFactory extends UiFactoryBase {
    private final OfferService offerService;

    public OffersUiFactory(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public String getUiName() {
        return "Offers";
    }

    @Override
    public UiBase create() {
        return new OffersUi(offerService, getUiName(), false);
    }
}

