package models;

import models.types.TypeVehicule;
import models.types.StatutVehicule;
import models.types.Agence;

public class Voiture {
    private int id;
    private String marque;
    private String modele;
    private String immatriculation;
    private TypeVehicule typeVehicule;
    private StatutVehicule statutVehicule;
    private Agence agence;
    private double prix;

    public Voiture(int id, String marque, String modele, String immatriculation, TypeVehicule typeVehicule, StatutVehicule statutVehicule, Agence agence, double prix) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.typeVehicule = typeVehicule;
        this.statutVehicule = statutVehicule;
        this.agence = agence;
        this.prix = prix;
    }

    public Voiture(String marque, String modele, String immatriculation, TypeVehicule typeVehicule, StatutVehicule statutVehicule, Agence agence, double prix) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.typeVehicule = typeVehicule;
        this.statutVehicule = statutVehicule;
        this.agence = agence;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }
    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public StatutVehicule getStatutVehicule() {
        return statutVehicule;
    }
    public void setStatutVehicule(StatutVehicule statutVehicule) {
        this.statutVehicule = statutVehicule;
    }

    public Agence getAgence() {
        return agence;
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", typeVehicule='" + typeVehicule + '\'' +
                ", statutVehicule='" + statutVehicule + '\'' +
                ", agence='" + agence + '\'' +
                ", prix=" + prix +
                '}';
    }
}
