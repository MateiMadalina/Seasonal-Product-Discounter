package com.codecool.seasonalproductdiscounter.service.persistence;

import com.codecool.seasonalproductdiscounter.service.logger.Logger;

public abstract class RepositoryBase {

    protected final String tableName;
    protected final SqliteConnector sqliteConnector;
    protected final Logger logger;

    public RepositoryBase(String tableName, SqliteConnector sqliteConnector, Logger logger) {
        this.tableName = tableName;
        this.sqliteConnector = sqliteConnector;
        this.logger = logger;
    }
}
