package com.benja2.entites;



import utilitaires.RegexPattern;
import java.util.logging.Logger;

/**
 * Classe représentant une adresse, comprenant des informations telles que le numéro de rue,
 * le nom de la rue, le code postal et la ville.
 * Cette classe est utilisée pour stocker des informations d'adresse liées à des entités comme les clients ou sociétés.
 */
public class Adresse {

    /** Logger pour enregistrer les événements et erreurs liés à l'adresse. */
    private static final Logger logger = Logger.getLogger(Adresse.class.getName());

    /** Numéro de rue de l'adresse. */
    private String numeroRue;

    /** Nom de la rue de l'adresse. */
    private String nomRue;

    /** Code postal de l'adresse. */
    private String codePostal;

    /** Ville de l'adresse. */
    private String ville;

    /**
     * Constructeur par défaut.
     * Permet de créer une instance d'Adresse sans initialiser ses champs.
     */
    public Adresse() {
        logger.info("Création d'une adresse avec le constructeur par défaut.");
    }

    /**
     * Constructeur avec tous les champs de la classe Adresse.
     *
     * @param numeroRue Numéro de rue de l'adresse.
     * @param nomRue Nom de la rue de l'adresse.
     * @param codePostal Code postal de l'adresse.
     * @param ville Ville de l'adresse.
     * @throws PersoException Si l'une des valeurs de l'adresse est invalide.
     */
    public Adresse(String numeroRue, String nomRue, String codePostal, String ville) throws PersoException {
        try {
            setNumeroRue(numeroRue);
            setNomRue(nomRue);
            setCodePostal(codePostal);
            setVille(ville);
        } catch (PersoException e) {
            logger.severe("Erreur lors de la création de l'adresse : " + e.getMessage());
            throw e;  // Propager l'exception après log.
        }
    }

    /**
     * Récupère le numéro de rue.
     *
     * @return Le numéro de rue de l'adresse.
     */
    public String getNumeroRue() {
        return numeroRue;
    }

    /**
     * Modifie le numéro de rue.
     *
     * @param numeroRue Le nouveau numéro de rue de l'adresse.
     * @throws PersoException Si le numéro de rue est invalide (null ou vide).
     */
    public void setNumeroRue(String numeroRue) throws PersoException {
        if (numeroRue == null || numeroRue.isEmpty()) {
            logger.warning("Tentative de définir un numéro de rue invalide.");
            throw new PersoException("Le n° de rue doit être renseigné");
        }
        this.numeroRue = numeroRue;
        logger.info("Numéro de rue défini : " + numeroRue);
    }

    /**
     * Récupère le nom de la rue.
     *
     * @return Le nom de la rue de l'adresse.
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Modifie le nom de la rue.
     *
     * @param nomRue Le nouveau nom de la rue de l'adresse.
     * @throws PersoException Si le nom de rue est invalide (null ou vide).
     */
    public void setNomRue(String nomRue) throws PersoException {
        if (nomRue == null || nomRue.isEmpty()) {
            logger.warning("Tentative de définir un nom de rue invalide.");
            throw new PersoException("Le nom de la rue doit être renseigné");
        }
        this.nomRue = nomRue;
        logger.info("Nom de rue défini : " + nomRue);
    }

    /**
     * Récupère le code postal.
     *
     * @return Le code postal de l'adresse.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Modifie le code postal.
     *
     * @param codePostal Le nouveau code postal de l'adresse.
     * @throws PersoException Si le code postal est invalide (null, vide, ou ne correspond pas au format attendu).
     */
    public void setCodePostal(String codePostal) throws PersoException {
        if (codePostal == null || codePostal.isEmpty()) {
            logger.warning("Tentative de définir un code postal invalide.");
            throw new PersoException("Le code postal doit être renseigné");
        }
        if (!codePostal.matches("^\\d{5}$")) {
            logger.warning("Le code postal n'est pas valide : " + codePostal);
            throw new PersoException("Le code postal doit contenir 5 chiffres");
        }
        this.codePostal = codePostal;
        logger.info("Code postal défini : " + codePostal);
    }

    /**
     * Récupère la ville.
     *
     * @return La ville de l'adresse.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Modifie la ville.
     *
     * @param ville La nouvelle ville de l'adresse.
     * @throws PersoException Si la ville est invalide (null ou vide).
     */
    public void setVille(String ville) throws PersoException {
        if (ville == null || ville.isEmpty()) {
            logger.warning("Tentative de définir une ville invalide.");
            throw new PersoException("Le nom de la ville doit être renseigné");
        }
        this.ville = ville;
        logger.info("Ville définie : " + ville);
    }

    /**
     * Retourne une représentation sous forme de chaîne de l'adresse.
     *
     * @return La chaîne représentant l'adresse complète sous forme de texte.
     */
    @Override
    public String toString() {
        return this.numeroRue + " " + this.nomRue + " " + this.codePostal + " " + this.ville;
    }

    /**
     * Effectue un nettoyage ou une opération finale après modification de l'adresse.
     */
    public void finaliserAdresse() {
        logger.info("Nettoyage et finalisation des modifications d'adresse.");
        // Exemple d'une tâche de finalisation
    }
}
