package com.codecool.seasonalproductdiscounter;

import com.codecool.seasonalproductdiscounter.model.transactions.Transaction;
import com.codecool.seasonalproductdiscounter.model.transactions.TransactionsSimulatorSettings;
import com.codecool.seasonalproductdiscounter.service.authentication.AuthenticationService;
import com.codecool.seasonalproductdiscounter.service.authentication.AuthenticationServiceImpl;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountProvider;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountProviderImpl;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountServiceImpl;
import com.codecool.seasonalproductdiscounter.service.logger.ConsoleLogger;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.persistence.DatabaseManager;
import com.codecool.seasonalproductdiscounter.service.persistence.DatabaseManagerImpl;
import com.codecool.seasonalproductdiscounter.service.persistence.SqliteConnector;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.repository.ProductRepository;
import com.codecool.seasonalproductdiscounter.service.products.repository.ProductRepositoryImpl;
import com.codecool.seasonalproductdiscounter.service.transactions.repository.TransactionRepository;
import com.codecool.seasonalproductdiscounter.service.transactions.repository.TransactionRepositoryImpl;
import com.codecool.seasonalproductdiscounter.service.transactions.simulator.TransactionsSimulator;
import com.codecool.seasonalproductdiscounter.service.users.UserRepositoryImpl;
import com.codecool.seasonalproductdiscounter.service.users.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        String dbFile = "src/main/resources/SeasonalProductDiscounter2.db";

        SqliteConnector sqliteConnector = new SqliteConnector(dbFile, logger);
        DatabaseManager dbManager = new DatabaseManagerImpl(sqliteConnector, logger);
        DiscountProvider discountProvider = new DiscountProviderImpl();
        DiscountService discounterService = new DiscountServiceImpl(discountProvider);
        ProductRepository productRepository = new ProductRepositoryImpl(sqliteConnector,logger);
        UserRepository userRepository = new UserRepositoryImpl(sqliteConnector,logger);
        TransactionRepository transactionRepository = new TransactionRepositoryImpl(logger,sqliteConnector);
        AuthenticationService authenticationService = new AuthenticationServiceImpl(userRepository);

        dbManager.createTables();
        initializeDatabase(productRepository);

        TransactionsSimulator simulator = new TransactionsSimulator(logger, userRepository, productRepository,
                authenticationService, discounterService, transactionRepository);

        RunSimulation(simulator, productRepository, transactionRepository);

        System.out.println("Press any key to exit.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void initializeDatabase(ProductRepository productRepository) {
        if (productRepository.getAvailableProducts().isEmpty()) {
            RandomProductGenerator randomProductGenerator = new RandomProductGenerator(1000, 20, 80);
            productRepository.addProducts(randomProductGenerator.getProducts());
        }

    }

    private static void RunSimulation(TransactionsSimulator simulator, ProductRepository productRepository,
                                      TransactionRepository transactionRepository) {
            LocalDate date = LocalDate.now();
            System.out.println("Starting simulation...");
            simulator.run(new TransactionsSimulatorSettings(date, 100, 70));
            List<Transaction> transactions = transactionRepository.getAll();
            System.out.println(transactions);
            System.out.println(date + " ended, total transactions: " + transactions.size() + ", total income: "
                    + transactions.stream().mapToDouble(Transaction::pricePaid).sum());
            System.out.println("Products left to sell: " + productRepository.getAvailableProducts().size());
    }
}
