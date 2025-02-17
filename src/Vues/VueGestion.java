package Vues;

import javax.swing.*;
import java.awt.*;

public class VueGestion extends JFrame {
    private final String action;
    private final JTextField tfRaisonSociale;
    private final JTextField tfAdresse;
    private final JTextField tfTelephone;
    private final JTextField tfEmail;
    private final JTextArea taCommentaires;

    public VueGestion(String type, String action, String societe, String adresse, String telephone, String email, String commentaires) {
        this.action = action;

        // Paramètres de la fenêtre
        setTitle(type + " - " + action);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());  // Utilisation de GridBagLayout pour une meilleure gestion de l'agencement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espacement autour des composants
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champs de texte pour la saisie des données
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Raison Sociale:"), gbc);

        tfRaisonSociale = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tfRaisonSociale, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Adresse:"), gbc);

        tfAdresse = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(tfAdresse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Téléphone:"), gbc);

        tfTelephone = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(tfTelephone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);

        tfEmail = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(tfEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Commentaires:"), gbc);

        taCommentaires = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(taCommentaires);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(scrollPane, gbc);

        // Pré-remplir les champs si modification ou suppression
        if ("Modification".equals(action) || "Suppression".equals(action)) {
            prefillFields(societe, adresse, telephone, email, commentaires);
        }

        // Bouton pour valider l'action
        JButton btnAction = new JButton(action);
        btnAction.addActionListener(e -> handleAction());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnAction, gbc);

        // Bouton pour revenir à la page d'accueil
        JButton btnRetour = new JButton("Retour à l'accueil");
        btnRetour.addActionListener(e -> {
            new VueAccueil();
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnRetour, gbc);

        setVisible(true);
    }

    private void prefillFields(String societe, String adresse, String telephone, String email, String commentaires) {
        tfRaisonSociale.setText(societe != null ? societe : "");
        tfAdresse.setText(adresse != null ? adresse : "");
        tfTelephone.setText(telephone != null ? telephone : "");
        tfEmail.setText(email != null ? email : "");
        taCommentaires.setText(commentaires != null ? commentaires : "");
    }

    private void handleAction() {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (action) {
            case "Création":
                createEntity();
                break;
            case "Modification":
                modifyEntity();
                break;
            case "Suppression":
                deleteEntity();
                break;
        }
    }

    private boolean validateFields() {
        return !tfRaisonSociale.getText().trim().isEmpty()
                && !tfAdresse.getText().trim().isEmpty()
                && !tfTelephone.getText().trim().isEmpty()
                && !tfEmail.getText().trim().isEmpty();
    }

    private void createEntity() {
        JOptionPane.showMessageDialog(this, "Entité créée avec succès !", "Création", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void modifyEntity() {
        JOptionPane.showMessageDialog(this, "Entité modifiée avec succès !", "Modification", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void deleteEntity() {
        int response = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cet élément ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Entité supprimée avec succès !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    public static void main(String[] args) {
        new VueGestion("Clients", "Création", "", "", "", "", "");
    }
}
