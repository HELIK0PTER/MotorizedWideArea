package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Voiture;
import models.types.TypeVehicule;
import models.types.StatutVehicule;
import models.types.Agence;

public class VoitureDAO {

    private static final String INSERT_VOITURES_SQL = "INSERT INTO Voiture (marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_VOITURE_BY_ID = "SELECT id, marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix FROM Voiture WHERE id =?";
    private static final String SELECT_ALL_VOITURES = "SELECT * FROM Voiture";
    private static final String DELETE_VOITURES_SQL = "DELETE FROM Voiture WHERE id = ?;";
    private static final String UPDATE_VOITURES_SQL = "UPDATE Voiture SET marque = ?, modele = ?, immatriculation = ?, typeVehicule = ?, statutVehicule = ?, agence = ?, prix = ? WHERE id = ?;";

    public void insertVoiture(Voiture voiture) throws SQLException {
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VOITURES_SQL)) {
                // statement : INSERT INTO Voiture (marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix) VALUES (?, ?, ?, ?, ?, ?, ?);
                preparedStatement.setString(1, voiture.getMarque());
                preparedStatement.setString(2, voiture.getModele());
                preparedStatement.setString(3, voiture.getImmatriculation());
                preparedStatement.setString(4, voiture.getTypeVehicule().name());
                preparedStatement.setString(5, voiture.getStatutVehicule().name());
                preparedStatement.setString(6, voiture.getAgence().name());
                preparedStatement.setDouble(7, voiture.getPrix());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }

    public Voiture selectVoiture(int id) throws SQLException {
        Voiture voiture = null;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOITURE_BY_ID)) {
                // statement : SELECT id, marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix FROM Voiture WHERE id =?;
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String marque = rs.getString("marque");
                    String modele = rs.getString("modele");
                    String immatriculation = rs.getString("immatriculation");
                    String typeVehicule = rs.getString("typeVehicule");
                    String statutVehicule = rs.getString("statutVehicule");
                    String agence = rs.getString("agence");
                    double prix = rs.getDouble("prix");
                    voiture = new Voiture(id, marque, modele, immatriculation, TypeVehicule.valueOf(typeVehicule), StatutVehicule.valueOf(statutVehicule), Agence.valueOf(agence), prix);
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return voiture;
    }

    public List<Voiture> selectAllVoitures() throws SQLException {
        List<Voiture> voitures = new ArrayList<>();
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VOITURES)) {
                // statement : SELECT * FROM Voiture;
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String marque = rs.getString("marque");
                    String modele = rs.getString("modele");
                    String immatriculation = rs.getString("immatriculation");
                    String typeVehicule = rs.getString("typeVehicule");
                    String statutVehicule = rs.getString("statutVehicule");
                    String agence = rs.getString("agence");
                    double prix = rs.getDouble("prix");
                    voitures.add(new Voiture(id, marque, modele, immatriculation, TypeVehicule.valueOf(typeVehicule), StatutVehicule.valueOf(statutVehicule), Agence.valueOf(agence), prix));
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return voitures;
    }

    public boolean deleteVoiture(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(DELETE_VOITURES_SQL)) {
                // statement : DELETE FROM Voiture WHERE id = ?;
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return rowDeleted;
    }

    public boolean updateVoiture(Voiture voiture) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(UPDATE_VOITURES_SQL)) {
                // statement : UPDATE Voiture SET marque = ?, modele = ?, immatriculation = ?, typeVehicule = ?, statutVehicule = ?, agence = ?, prix = ? WHERE id = ?;
                statement.setString(1, voiture.getMarque());
                statement.setString(2, voiture.getModele());
                statement.setString(3, voiture.getImmatriculation());
                statement.setString(4, voiture.getTypeVehicule().name());
                statement.setString(5, voiture.getStatutVehicule().name());
                statement.setString(6, voiture.getAgence().name());
                statement.setDouble(7, voiture.getPrix());
                statement.setInt(8, voiture.getId());
                rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
