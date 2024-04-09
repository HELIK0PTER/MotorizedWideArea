package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    // Remplacez par votre URL de base de données, votre nom d'utilisateur et votre mot de passe
    private static final String URL = "jdbc:mysql://mysql-matheuskg.alwaysdata.net/matheuskg_mwa";
    private static final String USER = "matheuskg_mwa";
    private static final String PASSWORD = "abdel_mathe_willi";

    // Chargement du pilote JDBC
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Driver JDBC non chargé");
        }
    }

    // Méthode pour obtenir la connexion
    public static Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
