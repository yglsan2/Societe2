package com.benja2.DAO;

import com.benja2.entites.Contrat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ContratDAO {
    private static final Logger LOGGER = Logger.getLogger(ContratDAO.class.getName());
    private final Connection connection;

    public ContratDAO() throws SQLException {
        this.connection = Connexion.getInstance().getConnection();
    }

    public List<Contrat> findByIdClient(int idClient) throws SQLException {
        List<Contrat> contrats = new ArrayList<>();
        String query = "SELECT * FROM contrat WHERE idClient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Contrat contrat = new Contrat(
                            rs.getInt("id"),
                            rs.getInt("idClient"),
                            rs.getString("libelle"),
                            rs.getDouble("montant")
                    );
                    contrats.add(contrat);
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la lecture des contrats pour le client avec l'id " + idClient + " : " + e.getMessage());
            throw e;
        }
        return contrats;
    }
}
