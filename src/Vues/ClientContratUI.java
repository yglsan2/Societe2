package Vues;

import com.benja2.entites.Contrat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientContratUI extends JFrame {

    public ClientContratUI(List<Contrat> contrats) {
        setTitle("Liste des Contrats");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Référence", "Date Début", "Date Fin", "Montant"};
        Object[][] data = new Object[contrats.size()][5];

        for (int i = 0; i < contrats.size(); i++) {
            Contrat contrat = contrats.get(i);
            data[i][0] = contrat.getId();
            data[i][1] = contrat.getReference();
            data[i][2] = contrat.getDateDebut();
            data[i][3] = contrat.getDateFin();
            data[i][4] = contrat.getMontant();
        }

        JTable contratTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(contratTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Exemple de données
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(new Contrat(1, "REF001", (java.sql.Date) new Date(), (java.sql.Date) new Date(), 1000.0));
        contrats.add(new Contrat(2, "REF002", (java.sql.Date) new Date(), (java.sql.Date) new Date(), 2000.0));

        SwingUtilities.invokeLater(() -> {
            ClientContratUI ui = new ClientContratUI(contrats);
            ui.setVisible(true);
        });
    }
}