package com.codecool.seasonalproductdiscounter.model.transactions;

import java.time.LocalDate;

public record TransactionsSimulatorSettings(LocalDate date, int transactionsCount, int usersCount) {}

