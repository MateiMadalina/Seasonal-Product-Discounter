package products.statistics;


import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImpl;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatisticsImpl;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductStatisticsImplTest {

    private final RandomProductGenerator provider;
    private final ProductStatistics statistics;

    public ProductStatisticsImplTest() {
        provider = new RandomProductGenerator(50, 10, 70);
        var browser = new ProductBrowserImpl(provider);
        statistics = new ProductStatisticsImpl(browser);
    }


    @Test
    public void testMostExpensive() {
        Product expected = Collections.max(provider.getProducts(), Comparator.comparingDouble(Product::price));
        Optional<Product> opt = statistics.getMostExpensive();

        Product actual = opt.get();

        assertEquals(actual, expected);
    }
}

