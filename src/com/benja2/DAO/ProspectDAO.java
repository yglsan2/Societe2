package com.benja2.DAO;

import com.benja2.entites.Prospect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProspectDAO {
    private static final Logger LOGGER = Logger.getLogger(ProspectDAO.class.getName());
    private static Connection connection;

    // Constructeur avec injection de la connexion
    public ProspectDAO(Connection connection) {
        this.connection = connection;
    }

    public ProspectDAO() {
        // Initialisation de la connexion si nécessaire
    }

    // Méthode pour vérifier l'unicité de la raison sociale
    public boolean isRaisonSocialeExist(String raisonSociale) throws SQLException {
        String query = "SELECT COUNT(*) FROM prospect WHERE nomSociete = ?";
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

    // Méthode pour trouver un prospect par raison sociale
    public Prospect findByRaisonSociale(String raisonSociale) throws SQLException {
        Prospect prospect = null;
        String query = "SELECT * FROM prospect WHERE nomSociete = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, raisonSociale);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    prospect = new Prospect(
                            rs.getInt("id"),
                            rs.getString("nomSociete"),
                            rs.getString("adresse"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getString("commentaire"),
                            rs.getDate("dateProspection").toLocalDate(),
                            rs.getBoolean("interesse")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la recherche du prospect avec la raison sociale " + raisonSociale + " : " + e.getMessage());
            throw e;
        }
        return prospect;
    }

    public List<Prospect> findAll() throws SQLException {
        List<Prospect> prospects = new ArrayList<>();
        String query = "SELECT * FROM prospect";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Prospect prospect = new Prospect(
                        rs.getInt("id"),
                        rs.getString("nomSociete"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("commentaire"),
                        rs.getDate("dateProspection").toLocalDate(),
                        rs.getBoolean("interesse")
                );
                prospects.add(prospect);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la lecture de la table prospect : " + e.getMessage());
            throw e;
        }
        return prospects;
    }

    public Prospect find(int id) throws SQLException {
        Prospect prospect = null;
        String query = "SELECT * FROM prospect WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    prospect = new Prospect(
                            rs.getInt("id"),
                            rs.getString("nomSociete"),
                            rs.getString("adresse"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getString("commentaire"),
                            rs.getDate("dateProspection").toLocalDate(),
                            rs.getBoolean("interesse")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la lecture du prospect avec l'id " + id + " : " + e.getMessage());
            throw e;
        }
        return prospect;
    }

    public void save(Prospect prospect) throws SQLException {
        if (prospect.getId() == null) {
            // Insertion d'un nouveau prospect
            String query = "INSERT INTO prospect (nomSociete, adresse, telephone, email, commentaire, dateProspection, interesse) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, prospect.getRaisonSociale());
                stmt.setString(2, prospect.getAdresse().toString());
                stmt.setString(3, prospect.getTelephone());
                stmt.setString(4, prospect.getEmail());
                stmt.setString(5, prospect.getCommentaire());
                stmt.setDate(6, Date.valueOf(prospect.getDateProspection()));
                stmt.setBoolean(7, prospect.getInteresse());
                stmt.executeUpdate();

                // Récupérer l'ID généré
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        prospect.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                LOGGER.severe("Erreur lors de la création du prospect : " + e.getMessage());
                throw e;
            }
        } else {
            // Mise à jour d'un prospect existant
            String query = "UPDATE prospect SET nomSociete = ?, adresse = ?, telephone = ?, email = ?, commentaire = ?, dateProspection = ?, interesse = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, prospect.getRaisonSociale());
                stmt.setString(2, prospect.getAdresse().toString());
                stmt.setString(3, prospect.getTelephone());
                stmt.setString(4, prospect.getEmail());
                stmt.setString(5, prospect.getCommentaire());
                stmt.setDate(6, Date.valueOf(prospect.getDateProspection()));
                stmt.setBoolean(7, prospect.getInteresse());
                stmt.setInt(8, prospect.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                LOGGER.severe("Erreur lors de la mise à jour du prospect : " + e.getMessage());
                throw e;
            }
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM prospect WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la suppression du prospect avec l'id " + id + " : " + e.getMessage());
            throw e;
        }
    }
}

