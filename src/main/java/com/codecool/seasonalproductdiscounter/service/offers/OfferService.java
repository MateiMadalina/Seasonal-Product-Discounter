package com.codecool.seasonalproductdiscounter.service.offers;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;

import java.time.LocalDate;
import java.util.List;

public interface OfferService {
    public List<Offer> getOffers(LocalDate date);
}

