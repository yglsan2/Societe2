package com.benja2.logging;
import com.benja2.entites.*;
import com.benja2.utilitaires.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * La classe Log permet d'initialiser un logger avec un FileHandler pour écrire les logs dans un fichier spécifique.
 * Elle fournit des méthodes pour configurer le logger, y compris la personnalisation du fichier de log,
 * du format des logs, et le mode d'ajout au fichier (append).
 */
public class Logs {

    /** Instance statique du logger utilisée pour l'écriture des logs. */
    public static final Logger LOGGER = Logger.getLogger(Logs.class.getName());

    /** Le FileHandler utilisé pour écrire les logs dans un fichier. */
    private static FileHandler fileHandler = null;

    /**
     * Initialise le logger avec un FileHandler qui écrit dans le fichier spécifié.
     * Ce handler peut être configuré pour ajouter les logs au fichier ou écraser le contenu existant.
     *
     * @param fileName Le nom du fichier de log où les messages seront enregistrés.
     * @param append Indique si le contenu doit être ajouté au fichier existant (true) ou écrasé (false).
     * @throws PersoException Si une erreur survient lors de l'initialisation du FileHandler ou du traitement des paramètres.
     */
    public static void initializeFileHandler(String fileName, boolean append) throws PersoException {

        // Vérification du nom du fichier
        if (fileName == null || fileName.isEmpty()) {
            throw new PersoException("Le nom du fichier ne peut pas être null ou vide.");
        }

        try {
            // Vérifie si le fichier existe déjà et si le mode append est activé
            fileHandler = new FileHandler(fileName, append);

            // Désactive les handlers parents pour ne pas afficher les logs sur la console
            LOGGER.setUseParentHandlers(false);

            // Configure le FileHandler avec un format simple
            fileHandler.setFormatter(new FormatterLog());

            // Ajoute le FileHandler au LOGGER
            LOGGER.addHandler(fileHandler);

            LOGGER.info("FileHandler initialisé avec succès.");

        } catch (IOException e) {
            // Lancer une exception personnalisée en cas d'erreur lors de l'initialisation du FileHandler
            throw new PersoException("Erreur lors de l'initialisation du FileHandler pour le fichier : " + fileName, e);
        }
    }

    /**
     * Fermeture du FileHandler et libération des ressources associées.
     * Cette méthode est utilisée pour s'assurer que le FileHandler est correctement fermé lors de l'arrêt de l'application.
     *
     * @throws PersoException Si une erreur se produit lors de la fermeture du FileHandler.
     */
    public static void closeLogger() throws PersoException {
        if (fileHandler != null) {
            try {
                fileHandler.close();
                LOGGER.info("Le FileHandler a été fermé avec succès.");
            } catch (SecurityException e) {
                throw new PersoException("Erreur lors de la fermeture du FileHandler.", e);
            }
        }
    }

    /**
     * Exemple d'utilisation de la classe Log.
     * Cette méthode montre comment initialiser le logger et enregistrer un message de log.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        try {
            // Initialisation du logger avec un fichier de log et en mode ajout
            Logs.initializeFileHandler("log/application.log", true);

            // Enregistrement d'un message de log
            LOGGER.info("Application démarrée avec succès.");

            // Enregistrement d'un message d'erreur
            LOGGER.severe("Une erreur critique est survenue.");

        } catch (PersoException e) {
            LOGGER.info("Erreur dans l'initialisation des logs ou dans leur gestion : " + e.getMessage());
        } finally {
            try {
                // Fermeture du logger pour libérer les ressources
                Logs.closeLogger();
            } catch (PersoException e) {
                LOGGER.info("Erreur lors de la fermeture du logger : " + e.getMessage());
            }
        }
    }

    public static void initialiserLogger() {
    }
}

