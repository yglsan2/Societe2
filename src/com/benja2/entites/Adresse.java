package com.benja2.entites;

import com.benja2.utilitaires.Regex;
import java.util.logging.Logger;

/**
 * Classe représentant une adresse, comprenant des informations telles que le numéro de rue,
 * le nom de la rue, le code postal et la ville.
 */
public class Adresse {

    private static final Logger logger = Logger.getLogger(Adresse.class.getName());

    private String numeroRue;
    private String nomRue;
    private String codePostal;
    private String ville;

    public Adresse() {
        logger.info("Création d'une adresse avec le constructeur par défaut.");
    }

    public Adresse(String numeroRue, String nomRue, String codePostal, String ville) {
        setNumeroRue(numeroRue);
        setNomRue(nomRue);
        setCodePostal(codePostal);
        setVille(ville);
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        if (numeroRue == null || numeroRue.isEmpty()) {
            logger.warning("Tentative de définir un numéro de rue invalide. Valeur par défaut utilisée : 'Inconnu'.");
            this.numeroRue = "Inconnu";
        } else if (!numeroRue.matches("^[0-9A-Za-z]+$")) {
            logger.warning("Le numéro de rue contient des caractères non autorisés : " + numeroRue + ". Valeur par défaut utilisée : 'Inconnu'.");
            this.numeroRue = "Inconnu";
        } else {
            this.numeroRue = numeroRue;
            logger.info("Numéro de rue défini : " + numeroRue);
        }
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        if (nomRue == null || nomRue.isEmpty()) {
            logger.warning("Tentative de définir un nom de rue invalide. Valeur par défaut utilisée : 'Inconnu'.");
            this.nomRue = "Inconnu";
        } else if (!nomRue.matches("^[a-zA-Z0-9\\s\\-']+$")) {
            logger.warning("Le nom de la rue contient des caractères non autorisés : " + nomRue + ". Valeur par défaut utilisée : 'Inconnu'.");
            this.nomRue = "Inconnu";
        } else {
            this.nomRue = nomRue;
            logger.info("Nom de rue défini : " + nomRue);
        }
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        if (codePostal == null || codePostal.isEmpty()) {
            logger.warning("Tentative de définir un code postal invalide. Valeur par défaut utilisée : '00000'.");
            this.codePostal = "00000";
        } else if (!Regex.PATTERN_CODE_POSTAL.matcher(codePostal).matches()) {
            logger.warning("Le code postal n'est pas valide : " + codePostal + ". Valeur par défaut utilisée : '00000'.");
            this.codePostal = "00000";
        } else {
            this.codePostal = codePostal;
            logger.info("Code postal défini : " + codePostal);
        }
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        if (ville == null || ville.isEmpty()) {
            logger.warning("Tentative de définir une ville invalide. Valeur par défaut utilisée : 'Inconnu'.");
            this.ville = "Inconnu";
        } else if (!ville.matches("^[a-zA-Z\\s\\-']+$")) {
            logger.warning("Le nom de la ville contient des caractères non autorisés : " + ville + ". Valeur par défaut utilisée : 'Inconnu'.");
            this.ville = "Inconnu";
        } else {
            this.ville = ville;
            logger.info("Ville définie : " + ville);
        }
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "numeroRue='" + numeroRue + '\'' +
                ", nomRue='" + nomRue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    public void finaliserAdresse() {
        logger.info("Nettoyage et finalisation des modifications d'adresse.");
    }
}