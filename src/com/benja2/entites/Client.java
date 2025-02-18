
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
    private List<Contrat> contrats;
    private int id;
    private long capital;

    /**
     * Constructeur principal avec validation.
     */
    public Client(String nomSociete, Adresse adresse, String telephone, String email,
                  Long chiffreAffaires, Integer nbEmployes, String commentaire) throws PersoException {
        super();

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

        this.nomSociete = nomSociete;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.chiffreAffaires = chiffreAffaires;
        this.nbEmployes = nbEmployes;
        this.commentaire = commentaire;
        this.contrats = new ArrayList<>();
        this.capital = 0L;

        LOGGER.info("Client créé avec succès : " + nomSociete);
    }

    /**
     * Constructeur alternatif avec ID et capital.
     */
    public Client(int id, String nomSociete, String adresse, String telephone, String email,
                  String commentaire, long capital, int nbEmployes) throws PersoException {
        this(nomSociete, adresse, telephone, email, capital, nbEmployes, commentaire);
        this.id = id;
        this.capital = capital;
    }

    public Client(String nomSociete, String adresse, String telephone, String email, long capital, int nbEmployes, String commentaire) {
        this.nomSociete = nomSociete;
        this.telephone = telephone;
        this.email = email;
        this.capital = capital;
        this.nbEmployes = nbEmployes;
        this.commentaire = commentaire;
    }

    // Getters et Setters
    public String getRaisonSociale() {
        return nomSociete;
    }

    public void setRaisonSociale(String nomSociete) throws PersoException {
        if (nomSociete == null || nomSociete.isEmpty()) {
            throw new PersoException("La raison sociale ne peut pas être vide.");
        }
        this.nomSociete = nomSociete;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) throws PersoException {
        if (adresse == null) {
            throw new PersoException("L'adresse ne peut pas être nulle.");
        }
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws PersoException {
        if (isValidTelephone(telephone)) {
            throw new PersoException("Numéro de téléphone invalide.");
        }
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PersoException {
        if (isValidEmail(email)) {
            throw new PersoException("Email invalide.");
        }
        this.email = email;
    }

    public Long getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(Long chiffreAffaires) throws PersoException {
        if (chiffreAffaires < 0) {
            throw new PersoException("Le chiffre d'affaires ne peut pas être négatif.");
        }
        this.chiffreAffaires = chiffreAffaires;
    }

    public Integer getNombreEmployes() {
        return nbEmployes;
    }

    public void setNombreEmployes(Integer nbEmployes) throws PersoException {
        if (nbEmployes < 0) {
            throw new PersoException("Le nombre d'employés ne peut pas être négatif.");
        }
        this.nbEmployes = nbEmployes;
    }

    @Override
    public void setNombreEmployes(int nombreEmployes) throws PersoException {

    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    @Override
    public Adresse getAdresseSociete() {
        return adresse;
    }

    // Méthodes de validation
    private boolean isValidTelephone(String telephone) {
        String regex = "^(\\+33|0)[1-9]([-. ]?[0-9]{2}){4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        return !matcher.matches();
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    /**
     * Méthode pour obtenir les informations du client sous forme de chaîne de caractères.
     */
    public String getClientInfo() {
        return String.format("Client [ID: %d, Raison Sociale: %s, Téléphone: %s, Email: %s, Nombre d'employés: %d, Capital: %d]",
                id, nomSociete, telephone, email, nbEmployes, capital);
    }

    /**
     * Méthode toString pour afficher une représentation textuelle de l'objet Client.
     * Utilisée par JComboBox pour afficher les éléments.
     */
    @Override
    public String toString() {
        return nomSociete; // Retourne le nom de la société pour l'affichage dans JComboBox
    }
}