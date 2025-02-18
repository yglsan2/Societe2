package com.benja2.entites;

import com.benja2.DAO.ClientDAO;
import com.benja2.DAO.ProspectDAO;
import com.benja2.utilitaires.PersoException;
import com.benja2.utilitaires.Regex;

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
    private ClientDAO clientDAO;
    private ProspectDAO prospectDAO;

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
    public Societe(int idClient, String raisonSociale, Adresse adresse, String telephone, String email, String commentaire) {
        try {
            this.identifiant = idClient;
            setRaisonSociale(raisonSociale);
            setAdresse(adresse);
            setTelephone(telephone);
            setEmail(email);
            setCommentaire(commentaire);
            this.clientDAO = new ClientDAO();
            this.prospectDAO = new ProspectDAO();
            logger.info("Société créée avec succès : " + raisonSociale);
        } catch (PersoException e) {
            logger.log(Level.INFO, "Erreur lors de l'initialisation de la société : ", e);
        }
    }

    public Societe() {
        // Initialisation des valeurs par défaut
        this.clientDAO = new ClientDAO();
        this.prospectDAO = new ProspectDAO();
    }

    public Societe(int idClient, String raisonSocialeSociete, String telSociete, String emailSociete, String commentaireSociete, Adresse adresseSociete) {
        // Initialisation par défaut si nécessaire
        this.clientDAO = new ClientDAO();
        this.prospectDAO = new ProspectDAO();
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
            logger.warning("La raison sociale doit être renseignée.");
            return;
        }

        try {
            if (raisonSocialeUnique(nouvelleRaison)) {
                this.raisonSociale = nouvelleRaison;
                logger.info("Raison sociale mise à jour : " + nouvelleRaison);
            } else {
                logger.warning("La raison sociale '" + nouvelleRaison + "' existe déjà !");
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur dans la définition de la raison sociale", e);
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
                logger.warning("Tous les champs de l'adresse doivent être renseignés.");
                return;
            }
            this.adresse = adresse;
            logger.info("Adresse mise à jour : " + this.adresse);
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue dans la définition de l'adresse", e);
        } finally {
            logger.info("Tentative de modification de l'adresse terminée.");
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws PersoException {
        if (telephone == null || telephone.trim().isEmpty()) {
            logger.warning("Le numéro de téléphone doit être renseigné.");
            return;
        }

        try {
            if (!telephone.trim().matches(String.valueOf(Regex.PATTERN_TELEPHONE))) {
                logger.warning("Le numéro de téléphone " + telephone + " est invalide.");
            } else {
                this.telephone = telephone.trim();
                logger.info("Numéro de téléphone mis à jour : " + telephone);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue lors de la définition du téléphone", e);
        } finally {
            logger.info("Tentative de modification du téléphone terminée.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PersoException {
        if (email == null || email.trim().isEmpty()) {
            logger.warning("L'email doit être renseigné.");
            return;
        }

        try {
            if (!email.matches(String.valueOf(Regex.PATTERN_EMAIL))) {
                logger.warning("L'adresse mail " + email + " est incorrecte.");
            } else {
                this.email = email;
                logger.info("Email mis à jour : " + email);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur inconnue lors de la définition de l'email", e);
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
    public static boolean raisonSocialeUnique(String nouvelleRaison) {
        try {
            // Vérifie l'unicité parmi les clients
            ClientDAO clientDAO = new ClientDAO();
            if (clientDAO.findByRaisonSociale(nouvelleRaison) != null) {
                return false; // La raison sociale existe déjà
            }

            // Vérifie l'unicité parmi les prospects
            ProspectDAO prospectDAO = new ProspectDAO();
            if (prospectDAO.findByRaisonSociale(nouvelleRaison) != null) {
                return false; // La raison sociale existe déjà
            }

            logger.info("Vérification d'unicité réussie pour la raison sociale : " + nouvelleRaison);
            return true; // La raison sociale est unique
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur lors de la vérification de l'unicité de la raison sociale", e);
            return false;
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

    public abstract void setId(int anInt);
}
