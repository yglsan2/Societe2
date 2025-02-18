package com.benja2.DAO;

import com.benja2.entites.Client;
import com.benja2.utilitaires.PersoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientDAO {
    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static Connection connection;

    // Constructeur avec injection de la connexion
    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public ClientDAO() {
        // Initialisation de la connexion si nécessaire
    }

    // Méthode pour vérifier l'unicité de la raison sociale
    public boolean isRaisonSocialeExist(String raisonSociale) throws SQLException {
        String query = "SELECT COUNT(*) FROM client WHERE nomSociete = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, raisonSociale);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Si le compte est supérieur à 0, la raison sociale existe
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la vérification de l'unicité de la raison sociale : " + e.getMessage());
            throw e;
        }
        return false;
    }

    // Méthode pour trouver un client par raison sociale
    public Client findByRaisonSociale(String raisonSociale) throws SQLException, PersoException {
        Client client = null;
        String query = "SELECT * FROM client WHERE nomSociete = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, raisonSociale);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client(
                            rs.getInt("id"),
                            rs.getString("nomSociete"),
                            rs.getString("adresse"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getString("commentaire"),
                            rs.getLong("capital"),
                            rs.getInt("nbEmployes")
                    );
                }
            }
        } catch (SQLException | PersoException e) {
            LOGGER.severe("Erreur lors de la recherche du client avec la raison sociale " + raisonSociale + " : " + e.getMessage());
            throw e;
        }
        return client;
    }

    // Méthode pour obtenir tous les clients
    public List<Client> findAll() throws SQLException, PersoException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("nomSociete"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("commentaire"),
                        rs.getLong("capital"),
                        rs.getInt("nbEmployes")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la lecture de la table client : " + e.getMessage());
            throw e;
        }
        return clients;
    }

    // Méthode pour trouver un client par ID
    public Client find(int id) throws SQLException, PersoException {
        Client client = null;
        String query = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client(
                            rs.getInt("id"),
                            rs.getString("nomSociete"),
                            rs.getString("adresse"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getString("commentaire"),
                            rs.getLong("capital"),
                            rs.getInt("nbEmployes")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la lecture du client avec l'id " + id + " : " + e.getMessage());
            throw e;
        }
        return client;
    }

    // Méthode pour sauvegarder un client (insérer ou mettre à jour)
    public void save(Client client) throws SQLException {
        if (client.getId() == null) {
            // Insertion d'un nouveau client
            String query = "INSERT INTO client (nomSociete, adresse, telephone, email, commentaire, capital, nbEmployes) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, client.getRaisonSociale());
                stmt.setString(2, client.getAdresse().toString());
                stmt.setString(3, client.getTelephone());
                stmt.setString(4, client.getEmail());
                stmt.setString(5, client.getCommentaire());
                stmt.setLong(6, client.getCapital());
                stmt.setInt(7, client.getNombreEmployes());
                stmt.executeUpdate();

                // Récupérer l'ID généré
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        client.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                LOGGER.severe("Erreur lors de la création du client : " + e.getMessage());
                throw e;
            }
        } else {
            // Mise à jour d'un client existant
            String query = "UPDATE client SET nomSociete = ?, adresse = ?, telephone = ?, email = ?, commentaire = ?, capital = ?, nbEmployes = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, client.getRaisonSociale());
                stmt.setString(2, client.getAdresse().toString());
                stmt.setString(3, client.getTelephone());
                stmt.setString(4, client.getEmail());
                stmt.setString(5, client.getCommentaire());
                stmt.setLong(6, client.getCapital());
                stmt.setInt(7, client.getNombreEmployes());
                stmt.setInt(8, client.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                LOGGER.severe("Erreur lors de la mise à jour du client : " + e.getMessage());
                throw e;
            }
        }
    }

    // Méthode pour supprimer un client par ID
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la suppression du client avec l'id " + id + " : " + e.getMessage());
            throw e;
        }
    }
}
