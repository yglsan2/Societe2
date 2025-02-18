package com.benja2.entites;

import com.benja2.utilitaires.PersoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prospect extends Societe {
    private static final Logger LOGGER = Logger.getLogger(Prospect.class.getName());

    private LocalDate dateProspection;
    private boolean interesse;
    private static Integer idProspect = 1; // Compteur statique pour générer des IDs uniques
    private final Integer id; // ID unique pour chaque instance de Prospect

    public Prospect(String raisonSociale, Adresse adresse, String telephone, String email,
                    String commentaire, String dateProspection, boolean interesse) throws PersoException {
        super(idProspect++, raisonSociale, adresse, telephone, email, commentaire);
        this.id = idProspect - 1; // Attribution de l'ID unique

        try {
            setDateProspection(dateProspection);
            setInteresse(interesse);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Erreur légère lors de l'initialisation du prospect", e);
            throw new PersoException("Erreur lors de l'initialisation du prospect : " + e.getMessage());
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "Erreur modérée lors de l'initialisation du prospect", e);
            throw new PersoException("Erreur lors de l'initialisation du prospect : " + e.getMessage());
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.WARNING, "Erreur critique lors de l'initialisation du prospect", e);
            throw new PersoException("Format de date invalide. Le format attendu est dd/MM/yyyy.");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Erreur inattendue lors de l'initialisation du prospect", e);
            throw new PersoException("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }

    public Prospect(Integer id, String raisonSociale, String adresse, String telephone, String email, String commentaire, LocalDate dateProspection, boolean interested) {
        super(id, raisonSociale, new Adresse(), telephone, email, commentaire); // Appel au constructeur de la classe parente
        this.id = id;
        this.dateProspection = dateProspection;
        this.interesse = interested;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setNombreEmployes(Integer nombreEmployes) throws PersoException {
        if (nombreEmployes != null && nombreEmployes < 0) {
            throw new PersoException("Le nombre d'employés ne peut pas être négatif.");
        }
    }

    @Override
    public void setNombreEmployes(int nombreEmployes) throws PersoException {
        if (nombreEmployes < 0) {
            throw new PersoException("Le nombre d'employés ne peut pas être négatif.");
        }
    }

    @Override
    public Adresse getAdresseSociete() {
        return this.getAdresse();
    }

    @Override
    public void setId(int anInt) {

    }

    public LocalDate getDateProspection() {
        return dateProspection;
    }

    public void setDateProspection(String dateProspection) throws PersoException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.dateProspection = LocalDate.parse(dateProspection, formatter);
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.WARNING, "Erreur de format de la date de prospection", e);
            throw new PersoException("Format de date invalide. Le format attendu est dd/MM/yyyy.");
        }
    }

    public boolean getInteresse() {
        return interesse;
    }

    public void setInteresse(boolean interesse) throws PersoException {
        this.interesse = interesse;
    }

    @Override
    public String toString() {
        return "Prospect{" +
                "id=" + id +
                ", raisonSociale='" + getRaisonSociale() + '\'' +
                ", adresse=" + getAdresse().toString() +
                ", telephone='" + getTelephone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", commentaire='" + getCommentaire() + '\'' +
                ", dateProspection=" + dateProspection +
                ", interesse=" + (interesse ? "OUI" : "NON") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prospect prospect = (Prospect) o;
        return interesse == prospect.interesse &&
                Objects.equals(id, prospect.id) &&
                Objects.equals(getRaisonSociale(), prospect.getRaisonSociale()) &&
                Objects.equals(getAdresse(), prospect.getAdresse()) &&
                Objects.equals(getTelephone(), prospect.getTelephone()) &&
                Objects.equals(getEmail(), prospect.getEmail()) &&
                Objects.equals(getCommentaire(), prospect.getCommentaire()) &&
                Objects.equals(dateProspection, prospect.dateProspection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getRaisonSociale(), getAdresse(), getTelephone(), getEmail(), getCommentaire(), dateProspection, interesse);
    }

    public long tempsEcouleDepuisProspection() {
        return LocalDate.now().toEpochDay() - dateProspection.toEpochDay();
    }
}
