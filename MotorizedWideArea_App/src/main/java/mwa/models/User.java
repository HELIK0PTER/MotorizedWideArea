package mwa.models;

import mwa.models.types.Role;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private Role role;

    public User(int id, String nom, String prenom, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public User(String nom, String prenom, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
