package Vues;

import Vues.ClientContratUI;
import com.benja2.entites.Adresse;
import com.benja2.entites.Client;
import com.benja2.utilitaires.PersoException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AccueilUI extends JFrame {

    private final JComboBox<Client> clientComboBox;

    public AccueilUI(List<Client> clients) {
        setTitle("Accueil");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        clientComboBox = new JComboBox<>(clients.toArray(new Client[0]));
        JButton showContratsButton = new JButton("Afficher les Contrats");

        showContratsButton.addActionListener(e -> {
            Client selectedClient = (Client) clientComboBox.getSelectedItem();
            if (selectedClient != null) {
                ClientContratUI contratUI = new ClientContratUI(selectedClient.getContrats());
                contratUI.setVisible(true);
            }
        });

        panel.add(clientComboBox);
        panel.add(showContratsButton);
        add(panel);
    }

    public static void main(String[] args) throws PersoException {
        // Exemple de données
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Client 1", new Adresse(), "0123456789", "client1@example.com", 100000L, 50, "Commentaire 1"));
        clients.add(new Client("Client 2", new Adresse(), "0987654321", "client2@example.com", 200000L, 100, "Commentaire 2"));

        SwingUtilities.invokeLater(() -> {
            AccueilUI ui = new AccueilUI(clients);
            ui.setVisible(true);
        });
    }
}