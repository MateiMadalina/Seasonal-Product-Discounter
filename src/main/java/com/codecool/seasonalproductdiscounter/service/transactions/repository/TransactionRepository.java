package com.codecool.seasonalproductdiscounter.service.transactions.repository;

import com.codecool.seasonalproductdiscounter.model.transactions.Transaction;

import java.util.List;

public interface TransactionRepository {
    boolean add(Transaction transaction);
    List<Transaction> getAll();
}

