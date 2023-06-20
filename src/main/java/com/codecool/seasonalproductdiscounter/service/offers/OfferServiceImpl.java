package com.codecool.seasonalproductdiscounter.service.offers;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OfferServiceImpl implements OfferService {
    private final ProductBrowser productBrowser;
    private final DiscountService discounterService;

    public OfferServiceImpl(ProductBrowser productBrowser, DiscountService discounterService) {
        this.productBrowser = productBrowser;
        this.discounterService = discounterService;
    }

    @Override
    public List<Offer> getOffers(LocalDate date) {
        return productBrowser.getAll()
                .stream()
                .map(p -> discounterService.getOffer(p, date))
                .filter(o -> !o.discounts().isEmpty())
                .collect(Collectors.toList());
    }
}
