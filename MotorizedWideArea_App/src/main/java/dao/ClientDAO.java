package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Client;

public class ClientDAO {
    private static final String INSERT_CLIENTS_SQL = "INSERT INTO Client (nom, prenom, email, dateNaiss, telephone) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_CLIENT_BY_ID = "SELECT id, nom, prenom, email, dateNaiss, telephone FROM Client WHERE id =?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM Client";
    private static final String DELETE_CLIENTS_SQL = "DELETE FROM Client WHERE id = ?;";
    private static final String UPDATE_CLIENTS_SQL = "UPDATE Client SET nom = ?, prenom = ?, email = ?, dateNaiss = ?, telephone = ? WHERE id = ?;";

    public void insertClient(Client client) throws SQLException {
        try (Connection connection = Connexion.getConnexion();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTS_SQL)) {
                // statement : INSERT INTO Client (nom, prenom, email, dateNaiss, telephone) VALUES (?, ?, ?, ?, ?);
                preparedStatement.setString(1, client.getNom());
                preparedStatement.setString(2, client.getPrenom());
                preparedStatement.setString(3, client.getEmail());
                preparedStatement.setDate(4, new java.sql.Date(client.getDateNaiss().getTime()));
                preparedStatement.setString(5, client.getTelephone());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }

    public Client selectClient(int id) throws SQLException {
        Client client = null;
        try (Connection connection = Connexion.getConnexion();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
                // statement : SELECT id, nom, prenom, email, dateNaiss, telephone FROM Client WHERE id =?;
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String email = rs.getString("email");
                    java.util.Date dateNaiss = rs.getDate("dateNaiss");
                    String telephone = rs.getString("telephone");
                    client = new Client(id, nom, prenom, email, dateNaiss, telephone);
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return client;
    }

    public List<Client> selectAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            // statement : SELECT * FROM Client;
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                java.util.Date dateNaiss = rs.getDate("dateNaiss");
                String telephone = rs.getString("telephone");
                clients.add(new Client(id, nom, prenom, email, dateNaiss, telephone));
            }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return clients;
    }

    public boolean deleteClient(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLIENTS_SQL)) {
            // statement : DELETE FROM Client WHERE id = ?;
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateClient(Client client) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENTS_SQL)) {
            // statement : UPDATE Client SET nom = ?, prenom = ?, email = ?, dateNaiss = ?, telephone = ? WHERE id = ?;
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getEmail());
            statement.setDate(4, new java.sql.Date(client.getDateNaiss().getTime()));
            statement.setString(5, client.getTelephone());
            statement.setInt(6, client.getId());
            rowUpdated = statement.executeUpdate() > 0;
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
