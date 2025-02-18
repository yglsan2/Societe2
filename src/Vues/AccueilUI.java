package Vues;

import com.benja2.entites.Adresse;
import com.benja2.entites.Client;
import com.benja2.utilitaires.PersoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AccueilUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox<Client> clientComboBox;
    private JButton showContratsButton;

    public AccueilUI(List<Client> clients) {
        initComponents(clients);
        configureUI();
    }

    private void initComponents(List<Client> clients) {
        System.out.println("Initialisation des composants...");

        // Initialisation manuelle des composants
        mainPanel = new JPanel(new BorderLayout());
        clientComboBox = new JComboBox<>();
        showContratsButton = new JButton("Afficher les contrats");

        // Ajout des composants au panel
        mainPanel.add(clientComboBox, BorderLayout.NORTH);
        mainPanel.add(showContratsButton, BorderLayout.SOUTH);

        // Configuration du modèle du JComboBox
        clientComboBox.setModel(new DefaultComboBoxModel<>(clients.toArray(new Client[0])));
        showContratsButton.addActionListener(this::onShowContratsButtonClick);

        System.out.println("Composants initialisés et ajoutés au panel.");
    }

    private void configureUI() {
        setContentPane(mainPanel);
        setTitle("Accueil");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void onShowContratsButtonClick(ActionEvent e) {
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        if (selectedClient != null) {
            System.out.println("Client sélectionné : " + selectedClient.getRaisonSociale());
            ClientContratUI contratUI = new ClientContratUI(selectedClient.getContrats());
            contratUI.setVisible(true);
        } else {
            System.out.println("Aucun client sélectionné.");
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();
        try {
            clients.add(new Client("Client 1", new Adresse(), "0123456789", "client1@example.com", 100000L, 50, "Commentaire 1"));
            clients.add(new Client("Client 2", new Adresse(), "0987654321", "client2@example.com", 200000L, 100, "Commentaire 2"));
        } catch (PersoException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            AccueilUI ui = new AccueilUI(clients);
            ui.setVisible(true);
        });
    }
}