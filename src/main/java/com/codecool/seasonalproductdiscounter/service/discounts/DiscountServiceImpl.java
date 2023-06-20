package com.codecool.seasonalproductdiscounter.service.discounts;

import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountProvider discountProvider;

    public DiscountServiceImpl(DiscountProvider discountProvider) {
        this.discountProvider = discountProvider;
    }

    public Offer getOffer(Product product, LocalDate date) {
        List<Discount> applicable = findApplicableDiscounts(product, date);
        double price = calculateFinalPrice(product, applicable);
        return new Offer(product, date, applicable, price);
    }

    private List<Discount> findApplicableDiscounts(Product product, LocalDate date) {
        List<Discount> accepted = new ArrayList<>();

        for (Discount discount : discountProvider.getDiscounts()) {
            if (discount.accepts(product, date)) {
                accepted.add(discount);
            }
        }

        return accepted;
    }

    private double calculateFinalPrice(Product product, List<Discount> discounts) {
        double discountSum = sumDiscounts(discounts);

        double rate = (100 - discountSum) / 100;
        return discountSum == 0.0 ? product.price() : product.price() * rate;
    }

    private double sumDiscounts(List<Discount> discounts) {
        double discountSum = 0;
        for (Discount discount : discounts) {
            discountSum += discount.rate();
        }

        return discountSum;
    }
}
