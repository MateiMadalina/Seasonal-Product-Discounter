package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.service.offers.OfferService;

import java.time.LocalDate;
import java.util.List;

public class OffersUi extends UiBase {
    private final OfferService offerService;

    public OffersUi(OfferService offerService, String title, boolean requireAuthentication) {
        super(title, requireAuthentication);
        this.offerService = offerService;
    }

    @Override
    public void run() {
        List<Offer> offers = offerService.getOffers(LocalDate.now());
        printOffers("Offers available on " + LocalDate.now(), offers);
    }

    private static void printOffers(String text, List<Offer> offers) {
        System.out.println(text + ": ");
        for (Offer offer : offers) {
            System.out.println(offer);
        }
    }
}

