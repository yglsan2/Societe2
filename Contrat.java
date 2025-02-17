package com.benja2.entites;

import java.sql.Date;

public class Contrat {
    private Integer id;
    private Integer idClient;
    private String libelle;
    private double montant;

    public Contrat(Integer id, Integer idClient, String libelle, double montant) {
        this.id = id;
        this.idClient = idClient;
        this.libelle = libelle;
        this.montant = montant;
    }

    public Contrat(int id, String reference, Date dateDebut, Date dateFin, double montant) {
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

    public Object getReference() {
        return null;
    }

    public Object getDateDebut() {
        return null;
    }

    public Object getDateFin() {
        return null;
    }
}
