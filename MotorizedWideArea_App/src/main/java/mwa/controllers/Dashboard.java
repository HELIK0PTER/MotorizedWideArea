package mwa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import mwa.models.Client;
import mwa.models.Voiture;
import mwa.models.Reservation;
import mwa.dao.ClientDAO;
import mwa.dao.VoitureDAO;
import mwa.dao.ReservationDAO;

import java.sql.SQLException;

public class Dashboard {
    @FXML private TableView<Client> clientsTable;
    @FXML private TableView<Voiture> fleetTable;
    @FXML private TableView<Reservation> reservationsTable;

    private final ClientDAO clientDAO = new ClientDAO();
    private final VoitureDAO voitureDAO = new VoitureDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();

    @FXML
    public void initialize() throws SQLException {
        loadClients();
        loadCars();
        loadReservations();
    }

    private void loadClients() throws SQLException {
        ObservableList<Client> clients = FXCollections.observableArrayList(clientDAO.selectAllClients());
        clientsTable.setItems(clients);
    }

    private void loadCars() throws SQLException {
        ObservableList<Voiture> cars = FXCollections.observableArrayList(voitureDAO.selectAllVoitures());
        fleetTable.setItems(cars);
    }

    private void loadReservations() throws SQLException {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList(reservationDAO.selectAllReservations());
        reservationsTable.setItems(reservations);
    }

    @FXML
    private void addClient() {
        // Logique pour ajouter un client
        // Cette méthode doit être complétée pour ouvrir un formulaire de saisie et insérer un client dans la base de données
    }

    @FXML
    private void editClient() {
        // Logique pour modifier un client sélectionné
        // Cette méthode nécessite un formulaire de modification avec les informations du client pré-remplies
    }

    @FXML
    private void deleteClient() {
        // Logique pour supprimer un client sélectionné
        // Cette action doit demander une confirmation avant de procéder à la suppression
    }

    @FXML
    private void addCar() {
        // Logique pour ajouter un véhicule
        // Implémenter la logique pour ouvrir un formulaire de saisie de nouveaux véhicules
    }

    @FXML
    private void editCar() {
        // Logique pour modifier un véhicule
        // Similaire à editClient, mais pour les véhicules
    }

    @FXML
    private void deleteCar() {
        // Logique pour supprimer un véhicule
        // Comme pour deleteClient, nécessite confirmation
    }

    @FXML
    private void addReservation() {
        // Logique pour ajouter une réservation
        // Doit permettre de sélectionner un client et un véhicule
    }

    @FXML
    private void editReservation() {
        // Logique pour modifier une réservation
        // Implique probablement un dialogue complexe avec des options de date, client, et véhicule
    }

    @FXML
    private void deleteReservation() {
        // Logique pour supprimer une réservation
        // Demande confirmation avant de supprimer
    }
}
