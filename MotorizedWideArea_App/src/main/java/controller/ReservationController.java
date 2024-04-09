package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ReservationController {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField vehicleIdField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<?> reservationsTable; // Utiliser un type approprié

    @FXML
    private void initialize() {
        // Initialiser la vue, par exemple en chargeant les réservations existantes dans le tableau
    }

    @FXML
    private void handleAddReservation() {
        // Récupérer les données du formulaire
        int userId = Integer.parseInt(userIdField.getText());
        int vehicleId = Integer.parseInt(vehicleIdField.getText());
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        // Ajouter la logique pour créer une nouvelle réservation
        // Utiliser un DAO pour insérer la réservation dans la base de données

        // Optionnel : Mettre à jour l'affichage, par exemple en ajoutant la nouvelle réservation au tableau
    }
}
