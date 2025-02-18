import com.benja2.entites.*;
import Vues.*;

import static com.benja2.logging.Logs.*;
import com.benja2.DAO.ClientDAO;
import com.benja2.DAO.ProspectDAO;

/**
 * La classe principale de l'application qui démarre l'interface utilisateur
 * et gère l'ajout des clients et prospects dans les gestionnaires respectifs.
 */
public class Main {

    /**
     * Méthode principale pour démarrer l'application.
     * Elle initialise le logger, démarre l'interface graphique et ajoute des clients et prospects.
     */
    public static void main(String[] args) {
        // Initialisation du logger pour le programme
        try {
            // Initialisation du logger
            initialiserLogger();
            LOGGER.info("Logger initialisé avec succès.");

            // Affichage de la fenêtre principale
            new VueAccueil().setVisible(true);
            LOGGER.info("Application démarrée avec succès.");

            // Remplissage des données client et prospect
            ajouterClientsEtProspects();

        } catch (Exception e) {
            // Capturer toutes les autres exceptions et les loguer de manière légère
            LOGGER.info("Erreur inattendue mais l'application continue : " + e.getMessage());
        }
    }

    /**
     * Méthode qui ajoute des clients et prospects à leurs gestionnaires respectifs.
     * Cette méthode est utilisée dans la méthode main pour remplir les données.
     */
    public static void ajouterClientsEtProspects() {
        try {
            // Création des adresses
            Adresse adresse1 = new Adresse("34", "rue de Laxou", "54000", "Nancy");
            Adresse adresse2 = new Adresse("56", "Boulevard de la Frite", "23323", "Airfryer");

            // Création de clients
            Client client1 = new Client("Société A", adresse1, "0603920392", "client1@hotmail.com", 2500L, 25, "Aucun commentaire");
            Client client2 = new Client("Société B", adresse2, "0693849381", "client2@gmail.com", 3000L, 30, "Aucun commentaire");

            // Création de prospects
            Prospect prospect1 = new Prospect("Société X", adresse1, "0601303928", "prospect1@hotmail.com", "Aucun commentaire", "01/02/2023", true);
            Prospect prospect2 = new Prospect("Société Y", adresse2, "0603902920", "prospect2@aol.com", "Aucun commentaire", "25/12/2022", true);

            // Utilisation des DAO pour ajouter les clients et prospects
            ClientDAO clientDAO = new ClientDAO();
            ProspectDAO prospectDAO = new ProspectDAO();

            LOGGER.info("Clients et prospects ajoutés avec succès.");

        } catch (Exception e) {
            // Si une erreur inconnue survient, traiter comme une exception modérée
            LOGGER.info("Erreur inattendue mais l'application continue : " + e.getMessage());
            System.out.println("Erreur inattendue, mais l'application continue : " + e.getMessage());
        } finally {
            LOGGER.info("Fin de l'ajout des clients et prospects.");
        }
    }
}
