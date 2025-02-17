package com.benja2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {
    private static final Logger LOGGER = Logger.getLogger(Connexion.class.getName());
    private static Connexion instance;
    private Connection connection;

    private Connexion() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Erreur légère lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}