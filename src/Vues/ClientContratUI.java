package Vues;

import com.benja2.entites.Contrat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.sql.Date;

public class ClientContratUI extends JFrame {
    private JPanel mainPanel;
    private JTable contratTable;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel boutonsPanel;
    private DefaultTableModel tableModel; // Modèle de données de la JTable

    public ClientContratUI(List<Contrat> contrats) {
        initComponents(); // Initialisation des composants
        setContentPane(mainPanel); // Définir mainPanel comme contenu de la fenêtre
        setTitle("Liste des Contrats");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Charger les contrats dans la table
        loadContrats(contrats);

        // Ajouter des actions aux boutons
        addButton.addActionListener(e -> ajouterContrat());
        editButton.addActionListener(e -> modifierContrat());
        deleteButton.addActionListener(e -> supprimerContrat());
    }

    private void initComponents() {
        System.out.println("Initialisation des composants...");

        // Initialisation du mainPanel
        mainPanel = new JPanel(new BorderLayout());
        System.out.println("mainPanel initialisé.");

        // Initialisation de la JTable
        contratTable = new JTable();
        tableModel = new DefaultTableModel();
        contratTable.setModel(tableModel);
        System.out.println("contratTable initialisée.");

        // Initialisation du boutonsPanel
        boutonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        System.out.println("boutonsPanel initialisé.");

        // Initialisation des boutons
        addButton = new JButton("Ajouter");
        editButton = new JButton("Éditer");
        deleteButton = new JButton("Supprimer");
        System.out.println("Boutons initialisés.");

        // Ajout des boutons au boutonsPanel
        boutonsPanel.add(addButton);
        boutonsPanel.add(editButton);
        boutonsPanel.add(deleteButton);
        System.out.println("Boutons ajoutés au boutonsPanel.");

        // Ajout des composants au mainPanel
        mainPanel.add(new JScrollPane(contratTable), BorderLayout.CENTER);
        mainPanel.add(boutonsPanel, BorderLayout.SOUTH);
        System.out.println("Composants ajoutés au mainPanel.");
    }

    private void loadContrats(List<Contrat> contrats) {
        System.out.println("Chargement des contrats dans la JTable...");

        // Définir les noms des colonnes
        String[] columnNames = {"ID", "Référence", "Date Début", "Date Fin", "Montant"};
        tableModel.setColumnIdentifiers(columnNames);

        // Remplir la matrice de données
        for (Contrat contrat : contrats) {
            Object[] rowData = {
                    contrat.getId(),
                    contrat.getReference(),
                    contrat.getDateDebut(),
                    contrat.getDateFin(),
                    contrat.getMontant()
            };
            tableModel.addRow(rowData);
            System.out.println("Contrat ajouté à la JTable : " + contrat.getReference()); // Log pour déboguer
        }

        System.out.println("JTable mise à jour avec " + contrats.size() + " contrats.");
    }

    private void ajouterContrat() {
        // Boîte de dialogue pour saisir les détails du contrat
        String reference = JOptionPane.showInputDialog(this, "Entrez la référence du contrat :");
        if (reference != null && !reference.isEmpty()) {
            // Créer un nouveau contrat (simulation)
            Contrat nouveauContrat = new Contrat(
                    tableModel.getRowCount() + 1, // ID (simulé)
                    1, // idClient (simulé)
                    reference,
                    new Date(System.currentTimeMillis()), // dateDebut (simulée)
                    new Date(System.currentTimeMillis() + 86400000), // dateFin (simulée)
                    1000.0 // montant (simulé)
            );

            // Ajouter le contrat à la JTable
            Object[] rowData = {
                    nouveauContrat.getId(),
                    nouveauContrat.getReference(),
                    nouveauContrat.getDateDebut(),
                    nouveauContrat.getDateFin(),
                    nouveauContrat.getMontant()
            };
            tableModel.addRow(rowData);
            System.out.println("Contrat ajouté : " + reference);
        }
    }

    private void modifierContrat() {
        // Récupérer la ligne sélectionnée dans la JTable
        int selectedRow = contratTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Récupérer la référence actuelle du contrat
            String reference = (String) tableModel.getValueAt(selectedRow, 1);

            // Boîte de dialogue pour modifier la référence
            String nouvelleReference = JOptionPane.showInputDialog(this, "Modifiez la référence du contrat :", reference);
            if (nouvelleReference != null && !nouvelleReference.isEmpty()) {
                // Mettre à jour la référence dans la JTable
                tableModel.setValueAt(nouvelleReference, selectedRow, 1);
                System.out.println("Contrat modifié : " + nouvelleReference);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contrat à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void supprimerContrat() {
        // Récupérer la ligne sélectionnée dans la JTable
        int selectedRow = contratTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Demander une confirmation
            int confirm = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce contrat ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Supprimer la ligne de la JTable
                tableModel.removeRow(selectedRow);
                System.out.println("Contrat supprimé.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contrat à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Exemple de données
        List<Contrat> contrats = List.of(
                new Contrat(1, "REF001", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 1000.0),
                new Contrat(2, "REF002", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 2000.0)
        );

        SwingUtilities.invokeLater(() -> {
            ClientContratUI ui = new ClientContratUI(contrats);
            ui.setVisible(true);
        });
    }
}