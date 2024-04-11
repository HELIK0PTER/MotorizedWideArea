package mwa.models;

import mwa.models.types.Agence;

public class Reservation {
    private int id;
    private int idClient;
    private int idVoiture;
    private String dateDebut;
    private String dateFin;
    private float prixTotal;
    private Agence agenceDebut;
    private Agence agenceFin;
    private boolean incident;

    public Reservation(int id, int idClient, int idVoiture, String dateDebut, String dateFin, float prixTotal, Agence agenceDebut, Agence agenceFin, boolean incident) {
        this.id = id;
        this.idClient = idClient;
        this.idVoiture = idVoiture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.agenceDebut = agenceDebut;
        this.agenceFin = agenceFin;
        this.incident = incident;
    }

    public Reservation(int idClient, int idVoiture, String dateDebut, String dateFin, float prixTotal, Agence agenceDebut, Agence agenceFin, boolean incident) {
        this.idClient = idClient;
        this.idVoiture = idVoiture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.agenceDebut = agenceDebut;
        this.agenceFin = agenceFin;
        this.incident = incident;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdVoiture() {
        return idVoiture;
    }
    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public float getPrixTotal() {
        return prixTotal;
    }
    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Agence getAgenceDebut() {
        return agenceDebut;
    }
    public void setAgenceDebut(Agence agenceDebut) {
        this.agenceDebut = agenceDebut;
    }

    public Agence getAgenceFin() {
        return agenceFin;
    }
    public void setAgenceFin(Agence agenceFin) {
        this.agenceFin = agenceFin;
    }

    public boolean isIncident() {
        return incident;
    }
    public void setIncident(boolean incident) {
        this.incident = incident;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", idClient=" + idClient + ", idVoiture=" + idVoiture + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prixTotal=" + prixTotal + ", agenceDebut=" + agenceDebut + ", agenceFin=" + agenceFin + ", incident=" + incident + '}';
    }
}
