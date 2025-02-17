package Vues;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VueAffichage extends JFrame {
    private static final Logger logger = Logger.getLogger(VueAffichage.class.getName());
    private final String type;
    private final DefaultListModel<String> listModel;

    static {
        // Configuration du logger
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
    }

    public VueAffichage(String type) {
        this.type = type;

        // Paramètres de la fenêtre
        setTitle("Affichage des " + type);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());  // Utilisation de GridBagLayout pour un meilleur contrôle de l'agencement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espacements autour des composants
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Liste des sociétés
        listModel = new DefaultListModel<>();
        JList<String> listSocietes = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listSocietes);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // La liste s'étend sur deux colonnes
        gbc.weightx = 1.0;  // Donne à la liste plus d'espace horizontal
        gbc.weighty = 1.0;  // Permet à la liste de grandir verticalement
        add(scrollPane, gbc);

        // Bouton de retour vers l'accueil
        JButton btnRetour = new JButton("Retour à l'accueil");
        btnRetour.addActionListener(e -> {
            logger.info("Retour à l'accueil");
            new VueAccueil();
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // Centrer le bouton sous la liste
        gbc.weightx = 0;  // Réinitialise le poids pour le bouton
        gbc.weighty = 0;  // Réinitialise le poids pour le bouton
        add(btnRetour, gbc);

        // Charger les données
        loadSocietes();

        setVisible(true);
    }

    private void loadSocietes() {
        List<String> societes = getSocietes(type);
        listModel.clear();
        for (String societe : societes) {
            listModel.addElement(societe);
        }
        logger.info("Données chargées pour le type : " + type);
    }

    private List<String> getSocietes(String type) {
        // Exemple de données, remplacez-le par une récupération dynamique
        return List.of("Société A", "Société B", "Société C");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VueAffichage("Clients"));
    }
}
