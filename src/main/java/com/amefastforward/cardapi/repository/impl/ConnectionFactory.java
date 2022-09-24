package com.amefastforward.cardapi.repository.impl;

import com.amefastforward.cardapi.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionFactory {

    private final DatabaseConfig databaseConfig;

    @Autowired
    public ConnectionFactory(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public Connection getConnection() throws SQLException {
        var connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword());

        connection.setAutoCommit(true);

        return connection;
    }
}
