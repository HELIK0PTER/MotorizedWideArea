package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charge le fichier FXML pour l'interface utilisateur
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/ReservationView.fxml"));

            // Définit le titre de la fenêtre
            primaryStage.setTitle("Gestion des Réservations");

            // Crée la scène avec le contenu chargé du FXML
            Scene scene = new Scene(root);

            // Affecte la scène au stage (fenêtre) et l'affiche
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Lance l'application JavaFX
        launch(args);
    }
}
