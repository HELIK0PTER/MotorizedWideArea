package mwa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import mwa.dao.UserDAO;
import mwa.models.User;

import java.sql.Array;
import java.util.List;

public class Connexion {

    @FXML
    private Label login_message;
    @FXML
    private Button login_button;
    @FXML
    private TextField nom_input;
    @FXML
    private TextField prenom_input;

    @FXML
    public void loginButtonAction(ActionEvent e){
        login_message.setText("Connexion en cours...");
        // si les champs sont vides
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
                        login_message.setText("Bienvenue " + u.getNom() + " " + u.getPrenom());
                        return;
                    }
                }
                // si l'utilisateur n'est pas dans la base de données
                login_message.setText("Utilisateur non trouvé");
            } catch (Exception exception) {
                login_message.setText("Erreur de connexion à la base de données");
            }
        }
    }
}
