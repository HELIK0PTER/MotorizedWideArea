package mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mwa.models.Reservation;
import mwa.models.types.Agence;

public class ReservationDAO {

    private static final String INSERT_RESERVATION_SQL = "INSERT INTO Reservation (idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_RESERVATION_BY_ID = "SELECT id, idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident FROM Reservation WHERE id =?";
    private static final String SELECT_ALL_RESERVATIONS = "SELECT * FROM Reservation";
    private static final String DELETE_RESERVATION_SQL = "DELETE FROM Reservation WHERE id = ?;";
    private static final String UPDATE_RESERVATION_SQL = "UPDATE Reservation SET idClient = ?, idVoiture = ?, dateDebut = ?, dateFin = ?, prixTotal = ?, agenceDebut = ?, agenceFin = ?, incident = ? WHERE id = ?;";

    public void insertReservation(Reservation reservation) throws SQLException {
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION_SQL)) {
                // statement : INSERT INTO Reservation (idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                preparedStatement.setInt(1, reservation.getIdClient());
                preparedStatement.setInt(2, reservation.getIdVoiture());
                preparedStatement.setString(3, reservation.getDateDebut());
                preparedStatement.setString(4, reservation.getDateFin());
                preparedStatement.setFloat(5, reservation.getPrixTotal());
                preparedStatement.setString(6, reservation.getAgenceDebut().name());
                preparedStatement.setString(7, reservation.getAgenceFin().name());
                preparedStatement.setBoolean(8, reservation.isIncident());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }

    public Reservation selectReservation(int id) throws SQLException {
        Reservation reservation = null;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATION_BY_ID)) {
                // statement : SELECT id, idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident FROM Reservation WHERE id =?;
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int idClient = rs.getInt("idClient");
                    int idVoiture = rs.getInt("idVoiture");
                    String dateDebut = rs.getString("dateDebut");
                    String dateFin = rs.getString("dateFin");
                    float prixTotal = rs.getFloat("prixTotal");
                    String agenceDebut = rs.getString("agenceDebut");
                    String agenceFin = rs.getString("agenceFin");
                    boolean incident = rs.getBoolean("incident");
                    reservation = new Reservation(id, idClient, idVoiture, dateDebut, dateFin, prixTotal, Agence.valueOf(agenceDebut), Agence.valueOf(agenceFin), incident);
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return reservation;
    }

    public List<Reservation> selectAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESERVATIONS)) {
                // statement : SELECT * FROM Reservation;
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idClient = rs.getInt("idClient");
                    int idVoiture = rs.getInt("idVoiture");
                    String dateDebut = rs.getString("dateDebut");
                    String dateFin = rs.getString("dateFin");
                    float prixTotal = rs.getFloat("prixTotal");
                    String agenceDebut = rs.getString("agenceDebut");
                    String agenceFin = rs.getString("agenceFin");
                    boolean incident = rs.getBoolean("incident");
                    reservations.add(new Reservation(id, idClient, idVoiture, dateDebut, dateFin, prixTotal, Agence.valueOf(agenceDebut), Agence.valueOf(agenceFin), incident));
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return reservations;
    }

    public boolean deleteReservation(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION_SQL)) {
                // statement : DELETE FROM Reservation WHERE id = ?;
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return rowDeleted;
    }

    public boolean updateReservation(Reservation reservation) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVATION_SQL)) {
                // statement : UPDATE Reservation SET idClient = ?, idVoiture = ?, dateDebut = ?, dateFin = ?, prixTotal = ?, agenceDebut = ?, agenceFin = ?, incident = ? WHERE id = ?;
                statement.setInt(1, reservation.getIdClient());
                statement.setInt(2, reservation.getIdVoiture());
                statement.setString(3, reservation.getDateDebut());
                statement.setString(4, reservation.getDateFin());
                statement.setFloat(5, reservation.getPrixTotal());
                statement.setString(6, reservation.getAgenceDebut().name());
                statement.setString(7, reservation.getAgenceFin().name());
                statement.setBoolean(8, reservation.isIncident());
                statement.setInt(9, reservation.getId());
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
