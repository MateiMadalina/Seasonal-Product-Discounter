package com.codecool.seasonalproductdiscounter.service.discounts;

import com.codecool.seasonalproductdiscounter.model.discounts.ColorDiscount;
import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.discounts.MonthlyDiscount;
import com.codecool.seasonalproductdiscounter.model.discounts.SeasonalDiscount;
import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;

import java.time.Month;
import java.util.List;
import java.util.Set;

public class DiscountProviderImpl implements DiscountProvider {

    private final List<Discount> discounts;

    public DiscountProviderImpl() {
        discounts = generateDiscounts();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    private static List<Discount> generateDiscounts() {
        return List.of(
                new MonthlyDiscount("Summer Kick-off", 10, Set.of(Month.JUNE, Month.JULY)),
                new MonthlyDiscount("Winter Sale", 10, Set.of(Month.FEBRUARY)),
                new ColorDiscount("Blue Winter", 5, Color.BLUE, Season.WINTER),
                new ColorDiscount("Green Spring", 5, Color.GREEN, Season.SPRING),
                new ColorDiscount("Yellow Summer", 5, Color.YELLOW, Season.SUMMER),
                new ColorDiscount("Brown Autumn", 5, Color.BROWN, Season.AUTUMN),
                new SeasonalDiscount("Sale Discount", 10, 21),
                new SeasonalDiscount("Outlet Discount", 20, 2)
        );
    }
}


