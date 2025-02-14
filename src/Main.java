import com.benja2.entites.Adresse;
import com.benja2.entites.Client;
import com.benja2.entites.Prospect;
import com.benja2.vues.*;

import static com.benja2.entites.GestionClient.getGestClient;
import static com.benja2.entites.GestionProspect.getGestProspect;
import static Logs.LOGGER;

import java.io.IOException;

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
            Logs.initialiserLogger();
            LOGGER.info("Logger initialisé avec succès.");

            // Affichage de la fenêtre principale
            new Accueil().setVisible(true);
            LOGGER.info("Application démarrée avec succès.");

            // Remplissage des données client et prospect
            ajouterClientsEtProspects();

        } catch (IOException e) {
            // Si une erreur survient lors de l'initialisation du logger
            LOGGER.warning("Erreur lors de l'initialisation du logger : " + e.getMessage());
            System.out.println("Erreur lors de l'initialisation du logger : " + e.getMessage());
            System.exit(1); // Quitter l'application si le logger ne s'initialise pas
        } catch (PersoException e) {
            // Si une exception personnalisée PersoException est lancée
            LOGGER.info("Problème rencontré mais l'application continue : " + e.getMessage());
            System.out.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            // Capturer toutes les autres exceptions et les loguer de manière légère
            LOGGER.info("Erreur inattendue mais l'application continue : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui ajoute des clients et prospects à leurs gestionnaires respectifs.
     * Cette méthode est utilisée dans la méthode main pour remplir les données.
     */
    public static void ajouterClientsEtProspects() {
        try {
            // Création des adresses
            Adresse adresse1 = new Adresse("34", "rue de Laxou", "Nancy", "54000");
            Adresse adresse2 = new Adresse("56", " Boulevard de la Frite", "Airfryer", "23323");

            // Création de clients
            Client client1 = new Client("Société A", "0603920392", "client1@hotmail.com", "Aucun commentaire", adresse1, 2500, 25);
            Client client2 = new Client("Société B", "0693849381", "client2@gmail.com", "Aucun commentaire", adresse2, 3000, 30);

            // Création de prospects
            Prospect prospect1 = new Prospect("Société X", "0601303928", "prospect1@hotmail.com", "Aucun commentaire", adresse1, "01/02/2023", true);
            Prospect prospect2 = new Prospect("Société Y", "0603902920", "prospect2@aol.com", "Aucun commentaire", adresse2, "25/12/2022", true);

            // Ajout des clients et prospects aux gestionnaires
            getGestClient().add(new Client(client1));
            getGestClient().add(new Client(client2));
            getGestProspect().add(new Prospect(prospect1));
            getGestProspect().add(new Prospect(prospect2));

            LOGGER.info("Clients et prospects ajoutés avec succès.");

        } catch (PersoException e) {
            // Gestion des exceptions personnalisées de manière légère
            LOGGER.info("Problème rencontré lors de l'ajout des clients et prospects : " + e.getMessage());
            System.out.println("Problème rencontré, mais l'application continue : " + e.getMessage());
            // L'exception n'est pas relancée car c'est une erreur légère.
        } catch (Exception e) {
            // Si une erreur inconnue survient, traiter comme une exception modérée
            LOGGER.info("Erreur inattendue mais l'application continue : " + e.getMessage());
            System.out.println("Erreur inattendue, mais l'application continue : " + e.getMessage());
        } finally {

            LOGGER.info("Fin de l'ajout des clients et prospects.");
        }

    }
}
}
