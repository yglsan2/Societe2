package Vues;

import javax.swing.*;
import java.awt.*;

public class VueAccueil extends JFrame {

    public VueAccueil() {
        // Paramètres de la fenêtre
        setTitle("Page d'Accueil");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacements autour des composants
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titre ou description
        JLabel lblDescription = new JLabel("<html><h2>Bienvenue sur la gestion des sociétés !</h2><p>Vous pouvez consulter, créer ou modifier les informations des sociétés.</p></html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblDescription, gbc);

        // Bouton pour afficher les sociétés
        JButton btnAfficherClients = new JButton("Afficher les Clients");
        btnAfficherClients.addActionListener(e -> {
            new VueAffichage("Clients");
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(btnAfficherClients, gbc);

        // Bouton pour afficher les prospects
        JButton btnAfficherProspects = new JButton("Afficher les Prospects");
        btnAfficherProspects.addActionListener(e -> {
            new VueAffichage("Prospects");
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnAfficherProspects, gbc);

        // Bouton pour créer une société
        JButton btnCreateSociety = new JButton("Créer une Société");
        btnCreateSociety.addActionListener(e -> {
            new VueGestion("Société", "Création", "", "", "", "", "");
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnCreateSociety, gbc);

        // Bouton pour modifier une société existante
        JButton btnModifierSociete = new JButton("Modifier une Société");
        btnModifierSociete.addActionListener(e -> {
            new VueGestion("Société", "Modification", "Société A", "Adresse A", "0123456789", "contact@a.com", "Commentaires A");
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnModifierSociete, gbc);

        // Bouton pour supprimer une société
        JButton btnSupprimerSociete = new JButton("Supprimer une Société");
        btnSupprimerSociete.addActionListener(e -> {
            new VueGestion("Société", "Suppression", "Société A", "Adresse A", "0123456789", "contact@a.com", "Commentaires A");
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnSupprimerSociete, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VueAccueil::new);
    }
}
