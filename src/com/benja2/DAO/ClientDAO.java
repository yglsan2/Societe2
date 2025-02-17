package com.benja2.DAO;

import com.benja2.entites.Adresse;
import com.benja2.entites.Client;
import com.benja2.entites.Contrat;
import com.benja2.utilitaires.PersoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientDAO {

    private final Connection connection;

    // Constructeur
    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour trouver un client par son ID
    public Client find(int id) throws SQLException, PersoException {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client(
                            resultSet.getString("nom_societe"),
                            new Adresse(),
                            resultSet.getString("telephone"),
                            resultSet.getString("email"),
                            resultSet.getLong("chiffre_affaires"),
                            resultSet.getInt("nb_employes"),
                            resultSet.getString("commentaire")
                    );
                    client.setContrats(getContratsByClientId(id)); // Récupérer les contrats associés
                    return client;
                }
            }
        }
        return null; // Retourne null si aucun client n'est trouvé
    }

    // Méthode pour récupérer tous les clients
    public List<Client> findAll() throws SQLException, PersoException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("nom_societe"),
                        new Adresse(),
                        resultSet.getString("telephone"),
                        resultSet.getString("email"),
                        resultSet.getLong("chiffre_affaires"),
                        resultSet.getInt("nb_employes"),
                        resultSet.getString("commentaire")
                );
                client.setContrats(getContratsByClientId(resultSet.getInt("id"))); // Récupérer les contrats associés
                clients.add(client);
            }
        }
        return clients;
    }

    // Méthode pour insérer un nouveau client
    public void insert(Client client) throws SQLException {
        String sql = "INSERT INTO client (nom_societe, adresse, telephone, email, chiffre_affaires, nb_employes, commentaire) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getRaisonSociale());
            statement.setString(2, client.getAdresse().toString()); // Convertir l'adresse en String
            statement.setString(3, client.getTelephone());
            statement.setString(4, client.getEmail());
            statement.setLong(5, client.getChiffreAffaires());
            statement.setInt(6, client.getNombreEmployes());
            statement.setString(7, client.getCommentaire());
            statement.executeUpdate();

            // Récupérer l'ID généré
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Méthode pour mettre à jour un client existant
    public void update(Client client) throws SQLException {
        String sql = "UPDATE client SET nom_societe = ?, adresse = ?, telephone = ?, email = ?, chiffre_affaires = ?, nb_employes = ?, commentaire = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getRaisonSociale());
            statement.setString(2, client.getAdresse().toString()); // Convertir l'adresse en String
            statement.setString(3, client.getTelephone());
            statement.setString(4, client.getEmail());
            statement.setLong(5, client.getChiffreAffaires());
            statement.setInt(6, client.getNombreEmployes());
            statement.setString(7, client.getCommentaire());
            statement.setInt(8, client.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un client par son ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer les contrats d'un client par son ID
    private List<Contrat> getContratsByClientId(int clientId) throws SQLException {
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrat WHERE client_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Contrat contrat = new Contrat(
                            resultSet.getInt("id"),
                            resultSet.getString("reference"),
                            resultSet.getDate("date_debut"),
                            resultSet.getDate("date_fin"),
                            resultSet.getDouble("montant")
                    );
                    contrats.add(contrat);
                }
            }
        }
        return contrats;
    }
}