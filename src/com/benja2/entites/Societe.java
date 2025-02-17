package com.benja2.entites;

import com.benja2.utilitaires.Regex;
import com.benja2.utilitaires.PersoException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe abstraite représentant une société avec ses informations principales.
 */
public abstract class Societe {

    private static final Logger logger = Logger.getLogger(Societe.class.getName());

    private int identifiant;
    private String raisonSociale;
    private Adresse adresse;
    private String telephone;
    protected String email;
    private String commentaire;
    private ChoixCRUD choixCRUD;

    /**
     * Constructeur principal de la classe Societe.
     * Initialise une nouvelle instance de Societe avec les informations fournies.
     *
     * @param idClient        L'identifiant unique de la société.
     * @param raisonSociale   La raison sociale de la société.
     * @param adresse         L'adresse de la société.
     * @param telephone       Le numéro de téléphone de la société.
     * @param email           L'adresse email de la société.
     * @param commentaire     Les commentaires concernant la société.
     */
    public Societe(int idClient, String raisonSociale, Adresse adresse, String telephone, String email, String commentaire) throws PersoException {
        try {
            this.identifiant = idClient;
            setRaisonSociale(raisonSociale);
            setAdresse(adresse);
            setTelephone(telephone);
            setEmail(email);
            setCommentaire(commentaire);
            logger.info("Société créée avec succès : " + raisonSociale);
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur lors de l'initialisation de la société : ", e);
            throw e;
        }
    }

    public Societe(String raisonSociale, Adresse adresse, String telephone, String email, String commentaire) {
        // Vous pouvez initialiser des valeurs par défaut ici si nécessaire
    }

    public Societe() {
        // Initialisation par défaut si nécessaire
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
        logger.info("Identifiant de la société mis à jour : " + identifiant);
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String nouvelleRaison) throws PersoException {
        if (nouvelleRaison == null || nouvelleRaison.trim().isEmpty()) {
            throw new PersoException("La raison sociale doit être renseignée.");
        }

        try {
            if (raisonSocialeUnique(nouvelleRaison)) {
                this.raisonSociale = nouvelleRaison;
                logger.info("Raison sociale mise à jour : " + nouvelleRaison);
            } else {
                throw new IllegalArgumentException("Erreur : La raison sociale '" + nouvelleRaison + "' existe déjà !");
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "Tentative de définir une raison sociale existante : " + nouvelleRaison, e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur générale dans la définition de la raison sociale", e);
            throw new PersoException("Erreur inconnue dans la définition de la raison sociale", e);
        } finally {
            logger.info("Tentative de modification de la raison sociale terminée.");
        }
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) throws PersoException {
        try {
            if (adresse == null || adresse.getNumeroRue().isEmpty() || adresse.getNomRue().isEmpty() ||
                    adresse.getVille().isEmpty() || adresse.getCodePostal().isEmpty()) {
                throw new IllegalArgumentException("Tous les champs de l'adresse doivent être renseignés.");
            }
            this.adresse = adresse;
            logger.info("Adresse mise à jour : " + this.adresse);
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "Adresse invalide fournie : " + adresse, e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue dans la définition de l'adresse", e);
            throw new PersoException("Erreur inconnue dans la définition de l'adresse", e);
        } finally {
            logger.info("Tentative de modification de l'adresse terminée.");
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws PersoException {
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new PersoException("Le numéro de téléphone doit être renseigné.");
        }

        try {
            if (!telephone.trim().matches(String.valueOf(Regex.PATTERN_TELEPHONE))) {
                throw new IllegalArgumentException("Erreur : le numéro de téléphone " + telephone + " est invalide.");
            }
            this.telephone = telephone.trim();
            logger.info("Numéro de téléphone mis à jour : " + telephone);
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "Numéro de téléphone invalide fourni : " + telephone, e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue lors de la définition du téléphone", e);
            throw new PersoException("Erreur inconnue lors de la définition du téléphone", e);
        } finally {
            logger.info("Tentative de modification du téléphone terminée.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PersoException {
        if (email == null || email.trim().isEmpty()) {
            throw new PersoException("L'email doit être renseigné.");
        }

        try {
            if (!email.matches(String.valueOf(Regex.PATTERN_EMAIL))) {
                throw new IllegalArgumentException("Erreur : l'adresse mail " + email + " est incorrecte.");
            }
            this.email = email;
            logger.info("Email mis à jour : " + email);
        } catch (IllegalArgumentException e) {
            logger.log(Level.INFO, "Email invalide fourni : " + email, e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue lors de la définition de l'email", e);
            throw new PersoException("Erreur inconnue lors de la définition de l'email", e);
        } finally {
            logger.info("Tentative de modification de l'email terminée.");
        }
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) throws PersoException {
        try {
            if (commentaire == null || commentaire.isEmpty()) {
                commentaire = "";
            }
            this.commentaire = commentaire;
            logger.info("Commentaire mis à jour.");
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue lors de la définition du commentaire", e);
            throw new PersoException("Erreur inconnue lors de la définition du commentaire", e);
        } finally {
            logger.info("Tentative de modification du commentaire terminée.");
        }
    }

    /**
     * Vérifie si la raison sociale est unique parmi les clients et prospects.
     *
     * @param nouvelleRaison La nouvelle raison sociale à vérifier.
     * @return true si la raison sociale est unique, false sinon.
     * @throws PersoException Si une erreur inconnue survient lors de la vérification de l'unicité.
     */
    public static boolean raisonSocialeUnique(String nouvelleRaison) throws PersoException {
        try {
            // Vérifie l'unicité parmi les clients
            for (Societe client : GestionClient.getGestClient()) {
                if (client.getRaisonSociale().equalsIgnoreCase(nouvelleRaison)) {
                    return false; // La raison sociale existe déjà
                }
            }

            // Vérifie l'unicité parmi les prospects
            for (Societe prospect : GestionProspect.getListeProspects()) {
                if (prospect.getRaisonSociale().equalsIgnoreCase(nouvelleRaison)) {
                    return false; // La raison sociale existe déjà
                }
            }
            logger.info("Vérification d'unicité réussie pour la raison sociale : " + nouvelleRaison);
            return true; // La raison sociale est unique
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur lors de la vérification de l'unicité de la raison sociale", e);
            throw new PersoException("Erreur inconnue lors de la vérification de l'unicité de la raison sociale", e);
        }
    }

    public ChoixCRUD getChoixCRUD() {
        return choixCRUD;
    }

    public void setChoixCRUD(ChoixCRUD enuCRUD) {
        this.choixCRUD = enuCRUD;
        logger.info("État CRUD mis à jour : " + enuCRUD);
    }

    @Override
    public String toString() {
        return "Societe{" +
                "identifiant=" + identifiant +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", adresse=" + adresse +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", enuCRUD=" + choixCRUD +
                '}';
    }

    protected abstract Integer getId();

    public abstract void setNombreEmployes(Integer nombreEmployes) throws PersoException;

    public abstract void setNombreEmployes(int nombreEmployes) throws PersoException;

    public abstract Adresse getAdresseSociete();

    /**
     * Retourne le nom de la société.
     *
     * @return La raison sociale de la société.
     */
    public String getNomSociete() {
        return raisonSociale; // Retourne la raison sociale comme nom de la société
    }

    /**
     * Retourne le nom de la société.
     *
     * @return La raison sociale de la société.
     */
    public String getNom() {
        return raisonSociale; // Retourne la raison sociale comme nom
    }
}