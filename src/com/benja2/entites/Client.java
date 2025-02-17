package com.benja2.entites;

import com.benja2.utilitaires.PersoException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe représentant un Client.
 * Elle contient les informations relatives à une société cliente.
 * La classe gère la validation des données d'entrée avec des exceptions légères/modérées.
 */
public class Client extends Societe {

    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    private String nomSociete;
    private Adresse adresse;
    private String telephone;
    private String email;
    private Long chiffreAffaires;
    private Integer nbEmployes;
    private String commentaire;
    private List<Contrat> contrats; // Ajout de la liste des contrats
    /**
     * Constructeur de la classe Client.
     * Initialise un client avec les informations fournies.
     *
     * @param nomSociete La raison sociale de la société.
     * @param adresse L'adresse du client.
     * @param telephone Le numéro de téléphone du client.
     * @param email L'email du client.
     * @param chiffreAffaires Le chiffre d'affaires du client.
     * @param nbEmployes Le nombre d'employés de la société.
     * @param commentaire Des commentaires additionnels concernant le client.
     * @throws PersoException Si une donnée fournie est invalide.
     */
    public Client(String nomSociete, Adresse adresse, String telephone, String email,
                  Long chiffreAffaires, Integer nbEmployes, String commentaire) throws PersoException {
        super();
        try {
            // Validation des entrées
            if (nomSociete == null || nomSociete.isEmpty()) {
                throw new PersoException("La raison sociale ne peut pas être vide.");
            }
            if (adresse == null) {
                throw new PersoException("L'adresse ne peut pas être nulle.");
            }
            if (isValidTelephone(telephone)) {
                throw new PersoException("Numéro de téléphone invalide.");
            }
            if (isValidEmail(email)) {
                throw new PersoException("Email invalide.");
            }
            if (nbEmployes < 0) {
                throw new PersoException("Le nombre d'employés ne peut pas être négatif.");
            }

            // Initialisation des propriétés
            this.nomSociete = nomSociete;
            this.adresse = adresse;
            this.telephone = telephone;
            this.email = email;
            this.chiffreAffaires = chiffreAffaires;
            this.nbEmployes = nbEmployes;
            this.commentaire = commentaire;
            this.contrats = new ArrayList<>(); // Initialisation de la liste des contrats


            LOGGER.info("Client créé avec succès : " + nomSociete);
        } catch (PersoException e) {
            LOGGER.warning("Erreur lors de la création du client : " + e.getMessage());
            throw e; // Re-throwing the exception for further handling
        }
    }

    // Getters et Setters pour la liste des contrats
    public List<Contrat> getContrats() {
        return contrats;
    }

    public Client(String nomSociete, String adresse, String telephone, String email, long chiffreAffaires, int nbEmployes, String commentaire) {
    }

    // Getters et Setters avec gestion d'exception et logs

    public String getRaisonSociale() {
        return nomSociete;
    }

    public void setRaisonSociale(String nomSociete) throws PersoException {
        try {
            if (nomSociete == null || nomSociete.isEmpty()) {
                throw new PersoException("La raison sociale ne peut pas être vide.");
            }
            this.nomSociete = nomSociete;
        } catch (PersoException e) {
            LOGGER.warning("Erreur lors de la mise à jour de la raison sociale : " + e.getMessage());
            throw e;
        }
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) throws PersoException {
        try {
            if (adresse == null) {
                throw new PersoException("L'adresse ne peut pas être nulle.");
            }
            this.adresse = adresse;
        } catch (PersoException e) {
            LOGGER.warning("Erreur lors de la mise à jour de l'adresse : " + e.getMessage());
            throw e;
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws PersoException {
        try {
            if (isValidTelephone(telephone)) {
                throw new PersoException("Numéro de téléphone invalide.");
            }
            this.telephone = telephone;
        } catch (PersoException e) {
            LOGGER.warning("Erreur lors de la mise à jour du téléphone : " + e.getMessage());
            throw e;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PersoException {
        try {
            if (isValidEmail(email)) {
                throw new PersoException("Email invalide.");
            }
            this.email = email;
        } catch (PersoException e) {
            LOGGER.warning("Erreur lors de la mise à jour de l'email : " + e.getMessage());
            throw e;
        }
    }

    public Long getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(Long chiffreAffaires) throws PersoException {
        try {
            if (chiffreAffaires < 0) {
                throw new PersoException("Le chiffre d'affaires ne peut pas être négatif.");
            }
            this.chiffreAffaires = chiffreAffaires;
        } catch (Exception e) {
            LOGGER.warning("Erreur lors de la mise à jour du chiffre d'affaires : " + e.getMessage());
            try {
                throw new PersoException("Erreur lors de la mise à jour du chiffre d'affaires.");
            } catch (PersoException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public Integer getNombreEmployes() {
        return nbEmployes;
    }

    public void setNombreEmployes(Integer nbEmployes) throws PersoException {
        try {
            if (nbEmployes < 0) {
                throw new PersoException("Le nombre d'employés ne peut pas être négatif.");
            }
            this.nbEmployes = nbEmployes;
        } catch (PersoException e) {
            LOGGER.warning("Erreur lors de la mise à jour du nombre d'employés : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void setNombreEmployes(int nombreEmployes) throws PersoException {
        // Implémentation vide
    }

    @Override
    public Adresse getAdresseSociete() {
        return null;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        try {
            this.commentaire = commentaire;
        } catch (Exception e) {
            LOGGER.warning("Erreur lors de la mise à jour des commentaires : " + e.getMessage());
            try {
                throw new PersoException("Erreur lors de la mise à jour des commentaires.");
            } catch (PersoException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public Integer getId() {
        return Integer.parseInt("");
    }

    // Méthodes de validation pour téléphone et email

    private boolean isValidTelephone(String telephone) {
        try {
            // Validation simple pour un numéro français
            String regex = "^01-9{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(telephone);
            return !matcher.matches();
        } catch (Exception e) {
            LOGGER.warning("Erreur lors de la validation du téléphone : " + e.getMessage());
            try {
                throw new PersoException("Erreur de validation du téléphone.");
            } catch (PersoException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private boolean isValidEmail(String email) {
        try {
            // Validation simple pour un email
            String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return !matcher.matches();
        } catch (Exception e) {
            LOGGER.warning("Erreur lors de la validation de l'email : " + e.getMessage());
            try {
                throw new PersoException("Erreur de validation de l'email.");
            } catch (PersoException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Méthode pour obtenir les informations du client sous forme de chaîne de caractères.
     *
     * @return Informations détaillées sur le client.
     */
    public String getClientInfo() {
        try {
            return String.format("Client [Raison Sociale: %s, Téléphone: %s, Email: %s, Nombre d'employés: %d]",
                    nomSociete, telephone, email, nbEmployes);
        } catch (Exception e) {
            LOGGER.warning("Erreur lors de la récupération des informations du client : " + e.getMessage());
            try {
                throw new PersoException("Erreur lors de la récupération des informations du client.");
            } catch (PersoException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void setContrats(List<Contrat> contratsByClientId) {
    }

    public void setId(int anInt) {
    }
}