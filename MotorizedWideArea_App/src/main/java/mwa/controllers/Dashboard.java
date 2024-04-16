package mwa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mwa.models.Client;
import mwa.models.User;
import mwa.models.Voiture;
import mwa.models.Reservation;
import mwa.dao.ClientDAO;
import mwa.dao.VoitureDAO;
import mwa.dao.ReservationDAO;
import mwa.models.types.Agence;
import mwa.models.types.StatutVehicule;
import mwa.models.types.TypeVehicule;

import java.sql.SQLException;
import java.util.Date;

import static mwa.controllers.Connexion.userGlobal;

public class Dashboard {
    // Déclaration des éléments de l'interface
    // Elements des tables
    @FXML private TableView<Client> clientsTable;
    @FXML private TableView<Voiture> flotteTable;
    @FXML private TableView<Reservation> reservationsTable;


    // client
    @FXML private VBox AddClientForm;
    @FXML private VBox EditClientForm;
    @FXML private VBox DeleteClientForm;

    // client add labels
    @FXML private TextField AddClientNom;
    @FXML private TextField AddClientPrenom;
    @FXML private TextField AddClientEmail;
    @FXML private DatePicker AddClientDateNaiss;
    @FXML private TextField AddClientTel;
    @FXML private Text AddClientMessage;

    // client edit labels
    @FXML private Text EditClientID;
    @FXML private TextField EditClientNom;
    @FXML private TextField EditClientPrenom;
    @FXML private TextField EditClientEmail;
    @FXML private DatePicker EditClientDateNaiss;
    @FXML private TextField EditClientTel;

    // client delete labels
    @FXML private Text DeleteUserName;
    @FXML private TextField DeleteUserID;
    @FXML private TextField DeleteUserPrenom;
    @FXML private Text DeleteSubmitMessage;

    private final User user = userGlobal;

    private final ClientDAO clientDAO = new ClientDAO();
    private final VoitureDAO voitureDAO = new VoitureDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();

    public void initialize() throws SQLException {
        loadClients();
        loadCars();
        loadReservations();

        AddCarType.getItems().setAll(TypeVehicule.values());
        AddCarStatut.getItems().setAll(StatutVehicule.values());
        AddCarAgence.getItems().setAll(Agence.values());
        EditCarType.getItems().setAll(TypeVehicule.values());
        EditCarStatut.getItems().setAll(StatutVehicule.values());
        EditCarAgence.getItems().setAll(Agence.values());
    }

    private void loadClients() throws SQLException {
        ObservableList<Client> clients = FXCollections.observableArrayList(clientDAO.selectAllClients());
        clientsTable.setItems(clients);
    }

    private void loadCars() throws SQLException {
        ObservableList<Voiture> cars = FXCollections.observableArrayList(voitureDAO.selectAllVoitures());
        flotteTable.setItems(cars);
    }

    private void loadReservations() throws SQLException {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList(reservationDAO.selectAllReservations());
        reservationsTable.setItems(reservations);
    }

    // Clients
    // ouvrir le formulaire d'ajout de client
    @FXML private void openAddClientForm() {
        if (AddClientForm.isVisible()) {
            AddClientForm.setVisible(false);
        } else {
            AddClientForm.setVisible(true);
            EditClientForm.setVisible(false);
            DeleteClientForm.setVisible(false);
        }
    }

    // action pour ajouter un client
    @FXML
    private void addClient() {
        // vérifier que les champs obligatoires sont remplis
        if (AddClientNom.getText().isEmpty() || AddClientPrenom.getText().isEmpty() || AddClientEmail.getText().isEmpty() || AddClientDateNaiss.getValue() == null || AddClientTel.getText().isEmpty()) {
            AddClientMessage.setText("Veuillez remplir tous les champs obligatoires");
            return;
        }

        // Récupérer les informations du formulaire
        String nom = AddClientNom.getText();
        String prenom = AddClientPrenom.getText();
        String email = AddClientEmail.getText();
        Date dateNaiss = java.sql.Date.valueOf(AddClientDateNaiss.getValue());
        String tel = AddClientTel.getText();

        // Créer un objet Client avec les informations récupérées
        Client client = new Client(nom, prenom, email, dateNaiss, tel);

        // Ajouter le client à la base de données
        try {
            clientDAO.insertClient(client);
            AddClientMessage.setText("Client ajouté avec succès");
            loadClients();
        } catch (SQLException e) {
            AddClientMessage.setText("Erreur lors de l'ajout du client");
            e.printStackTrace();
        }
        AddClientForm.setVisible(false);
    }

    // ouvrir le formulaire de modification de client
    @FXML private void openEditClientForm() {
        if (EditClientForm.isVisible()) {
            EditClientForm.setVisible(false);
        } else {
            EditClientForm.setVisible(true);
            AddClientForm.setVisible(false);
            DeleteClientForm.setVisible(false);
        }
    }

    private Client selectedClient;
    // add action to table row click
    @FXML private void selectClient() {
        selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        // edit from
        // si aucun client n'est sélectionné, ne rien faire
        if (selectedClient == null) {
            return;
        }
        EditClientID.setText(String.valueOf(selectedClient.getId()));
        EditClientNom.setText(selectedClient.getNom());
        EditClientPrenom.setText(selectedClient.getPrenom());
        EditClientEmail.setText(selectedClient.getEmail());
        EditClientDateNaiss.setValue(new java.sql.Date(selectedClient.getDateNaiss().getTime()).toLocalDate());
        EditClientTel.setText(selectedClient.getTelephone());

        // delete form
        DeleteUserName.setText(selectedClient.getPrenom() + " " + selectedClient.getNom());
    }

    // action pour modifier un client
    @FXML
    private void editClient() {
        // Récupérer les informations du formulaire
        String nom = EditClientNom.getText();
        String prenom = EditClientPrenom.getText();
        String email = EditClientEmail.getText();
        Date dateNaiss = java.sql.Date.valueOf(EditClientDateNaiss.getValue());
        String tel = EditClientTel.getText();

        // Créer un objet Client avec les informations récupérées
        Client client = new Client(selectedClient.getId(), nom, prenom, email, dateNaiss, tel);

        // Mettre à jour le client dans la base de données
        try {
            clientDAO.updateClient(client);
            loadClients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EditClientForm.setVisible(false);
    }

    // ouvrir le formulaire de suppression de client
    @FXML private void openDeleteClientForm() {
        if (DeleteClientForm.isVisible()) {
            DeleteClientForm.setVisible(false);
        } else {
            DeleteClientForm.setVisible(true);
            AddClientForm.setVisible(false);
            EditClientForm.setVisible(false);
        }
    }
    // action pour supprimer un client
    @FXML
    private void deleteClient() {
        // vérifier que l'utilisateur a bien confirmé la suppression
        if (DeleteUserID.getText().isEmpty() || DeleteUserPrenom.getText().isEmpty()) {
            DeleteSubmitMessage.setText("Veuillez confirmer la suppression");
            return;
        }

        // Supprimer le client de la base de données
        try {
            clientDAO.deleteClient(selectedClient.getId());
            loadClients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // voiture
    @FXML private VBox AddCarForm;
    @FXML private VBox EditCarForm;
    @FXML private VBox DeleteCarForm;

    // voiture add labels
    @FXML private TextField AddCarMarque;
    @FXML private TextField AddCarModele;
    @FXML private TextField AddCarImmatriculation;
    @FXML private ComboBox<TypeVehicule> AddCarType;
    @FXML private ComboBox<StatutVehicule> AddCarStatut;
    @FXML private ComboBox<Agence> AddCarAgence;
    @FXML private TextField AddCarPrix;
    @FXML private Text AddCarMessage;

    // voiture edit labels
    @FXML private Text EditCarID;
    @FXML private TextField EditCarMarque;
    @FXML private TextField EditCarModele;
    @FXML private TextField EditCarImmatriculation;
    @FXML private ComboBox<TypeVehicule> EditCarType;
    @FXML private ComboBox<StatutVehicule> EditCarStatut;
    @FXML private ComboBox<Agence> EditCarAgence;
    @FXML private TextField EditCarPrix;

    // voiture delete labels
    @FXML private Text DeleteCarImmatriculation;
    @FXML private TextField DeleteCarID;
    @FXML private TextField DeleteCarMarque;
    @FXML private Text DeleteCarMessage;


    // Voitures
    // Ajouter, modifier, supprimer

    // traquer la voiture sélectionnée
    private Voiture selectedCar;
    // add action to table row click
    @FXML private void selectCar() {
        selectedCar = flotteTable.getSelectionModel().getSelectedItem();
        // edit form
        // si aucune voiture n'est sélectionnée, ne rien faire
        if (selectedCar == null) {
            return;
        }
        EditCarID.setText(String.valueOf(selectedCar.getId()));
        EditCarMarque.setText(selectedCar.getMarque());
        EditCarModele.setText(selectedCar.getModele());
        EditCarImmatriculation.setText(selectedCar.getImmatriculation());
        EditCarType.setValue(selectedCar.getTypeVehicule());
        EditCarStatut.setValue(selectedCar.getStatutVehicule());
        EditCarAgence.setValue(selectedCar.getAgence());
        EditCarPrix.setText(String.valueOf(selectedCar.getPrix()));

        // delete form
        DeleteCarID.setText(String.valueOf(selectedCar.getId()));
        DeleteCarMarque.setText(selectedCar.getMarque());
        DeleteCarImmatriculation.setText(selectedCar.getImmatriculation());
    }

    @FXML private void openAddCarForm() {
        if (AddCarForm.isVisible()) {
            AddCarForm.setVisible(false);
        } else {
            AddCarForm.setVisible(true);
            EditCarForm.setVisible(false);
            DeleteCarForm.setVisible(false);
        }
    }

    @FXML private void openEditCarForm() {
        if (EditCarForm.isVisible()) {
            EditCarForm.setVisible(false);
        } else {
            EditCarForm.setVisible(true);
            AddCarForm.setVisible(false);
            DeleteCarForm.setVisible(false);
        }
    }

    @FXML private void openDeleteCarForm() {
        if (DeleteCarForm.isVisible()) {
            DeleteCarForm.setVisible(false);
        } else {
            DeleteCarForm.setVisible(true);
            AddCarForm.setVisible(false);
            EditCarForm.setVisible(false);
        }
    }

    @FXML
    private void addCar() {
        // vérifier que les champs obligatoires sont remplis
        if (AddCarMarque.getText().isEmpty() || AddCarModele.getText().isEmpty() || AddCarImmatriculation.getText().isEmpty() || AddCarType.getValue() == null || AddCarStatut.getValue() == null || AddCarAgence.getValue() == null || AddCarPrix.getText().isEmpty()) {
            AddCarMessage.setText("Veuillez remplir tous les champs obligatoires");
            return;
        }

        // Récupérer les informations du formulaire
        String marque = AddCarMarque.getText();
        String modele = AddCarModele.getText();
        String immatriculation = AddCarImmatriculation.getText();
        TypeVehicule type = AddCarType.getValue();
        StatutVehicule statut = AddCarStatut.getValue();
        Agence agence = AddCarAgence.getValue();
        double prix = Double.parseDouble(AddCarPrix.getText());

        // Créer un objet Voiture avec les informations récupérées
        Voiture voiture = new Voiture(marque, modele, immatriculation, type, statut, agence, prix);

        // Ajouter la voiture à la base de données
        try {
            voitureDAO.insertVoiture(voiture);
            AddCarMessage.setText("Voiture ajoutée avec succès");
            loadCars();
        } catch (SQLException e) {
            AddCarMessage.setText("Erreur lors de l'ajout de la voiture");
            e.printStackTrace();
        }
        AddCarForm.setVisible(false);
    }

    @FXML
    private void editCar() {
        // Récupérer les informations du formulaire
        String marque = EditCarMarque.getText();
        String modele = EditCarModele.getText();
        String immatriculation = EditCarImmatriculation.getText();
        TypeVehicule type = EditCarType.getValue();
        StatutVehicule statut = EditCarStatut.getValue();
        Agence agence = EditCarAgence.getValue();
        double prix = Double.parseDouble(EditCarPrix.getText());

        // Créer un objet Voiture avec les informations récupérées
        Voiture voiture = new Voiture(selectedCar.getId(), marque, modele, immatriculation, type, statut, agence, prix);

        // Mettre à jour la voiture dans la base de données
        try {
            voitureDAO.updateVoiture(voiture);
            loadCars();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EditCarForm.setVisible(false);
    }

    @FXML
    private void deleteCar() {
        // vérifier que l'utilisateur a bien confirmé la suppression
        if (DeleteCarID.getText().isEmpty() || DeleteCarMarque.getText().isEmpty() || DeleteCarImmatriculation.getText().isEmpty()) {
            DeleteCarMessage.setText("Veuillez confirmer la suppression");
            return;
        }
        // Supprimer la voiture de la base de données
        try {
            voitureDAO.deleteVoiture(selectedCar.getId());
            loadCars();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DeleteCarForm.setVisible(false);
    }



    // reservation
    @FXML private Button AddReservation;
    @FXML private Button EditReservation;
    @FXML private Button DeleteReservation;


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
