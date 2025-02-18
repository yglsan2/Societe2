package com.benja2.entites;

import java.sql.Date;

public class Contrat {
    private Integer id;
    private Integer idClient;
    private String libelle;
    private double montant;
    private String reference;
    private Date dateDebut;
    private Date dateFin;

    // Constructeur principal
    public Contrat(Integer id, Integer idClient, String libelle, double montant, String reference, Date dateDebut, Date dateFin) {
        this.id = id;
        this.idClient = idClient;
        this.libelle = libelle;
        this.montant = montant;
        this.reference = reference;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Nouveau constructeur pour correspondre à votre appel
    public Contrat(int id, String reference, Date dateDebut, Date dateFin, double montant) {
        this.id = id;
        this.reference = reference;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.idClient = null; // ou une valeur par défaut
        this.libelle = null;  // ou une valeur par défaut
    }

    public Contrat(int i, int i1, String reference, Date date, Date date1, double v) {
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}