package mwa.controllers;

// import des controles de scene
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// autres imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import mwa.dao.UserDAO;
import mwa.models.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Connexion {

    // variable pour gérer le changement de scène
    private Stage stage;
    private Scene scene;
    private Parent root;

    // variable globale pour stocker l'utilisateur connecté
    public static User userGlobal;
    public boolean isConnected = false;
    public ActionEvent event;

    @FXML
    private Label login_message;
    @FXML
    private Button login_button;
    @FXML
    private TextField nom_input;
    @FXML
    private TextField prenom_input;

    // bouton se connecter quand il est cliqué
    @FXML
    public void loginButtonAction(ActionEvent e){
        // si les champs sont vides
        login_message.setText("Connexion ...");
        if (nom_input.getText().isEmpty() || prenom_input.getText().isEmpty()) {
            login_message.setText("Veuillez remplir tous les champs");
        } else {
            // si les champs sont remplis
            // essayez de vous connecter à la base de données
            // si la connexion réussit, affichez un message de bienvenue
            // sinon, affichez un message d'erreur
            User user = new User(nom_input.getText(), prenom_input.getText());
            UserDAO userDAO = new UserDAO();
            List<User> users = null;
            try {
                users = userDAO.selectAllUsers();
                // si l'utilisateur est dans la base de données
                for (User u : users) {
                    if (u.getNom().equalsIgnoreCase(user.getNom()) && u.getPrenom().equalsIgnoreCase(user.getPrenom())) {
                        userGlobal = u;
                        event = e;
                        login_message.setText("Bienvenue " + u.getNom() + " " + u.getPrenom());
                        System.out.println("Bienvenue " + u.getNom() + " " + u.getPrenom());
                        Platform.runLater(() -> {
                            try {
                                displayDashboard(event);
                            } catch (IOException | InterruptedException ev) {
                                ev.printStackTrace();
                            }
                        });
                        break;
                    }
                }
                // si l'utilisateur n'est pas dans la base de données
                if (userGlobal == null) login_message.setText("Utilisateur non trouvé");
            } catch (Exception exception) {
                login_message.setText("Erreur de connexion");
            }
        }
    }

    // méthode pour afficher le dashboard
    public void displayDashboard(ActionEvent e) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/mwa/dashboard.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
