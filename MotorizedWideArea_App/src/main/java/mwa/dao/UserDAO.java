package mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mwa.models.User;
import mwa.models.types.Role;

public class UserDAO {

    private static final String INSERT_USERS_SQL = "INSERT INTO User (nom, prenom, role) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, nom, prenom, role FROM User WHERE id =?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM User";
    private static final String DELETE_USERS_SQL = "DELETE FROM User WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE User SET nom = ?, prenom = ?, role = ? WHERE id = ?;";

    public void insertUser(User user) throws SQLException {
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
                // statement : INSERT INTO User (nom, prenom, role) VALUES (?, ?, ?);
                preparedStatement.setString(1, user.getNom());
                preparedStatement.setString(2, user.getPrenom());
                preparedStatement.setString(3, user.getRole().name());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }

    public User selectUser(int id) throws SQLException {
        User user = null;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
                // statement : SELECT id, nom, prenom, role FROM User WHERE id =?;
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String role = rs.getString("role");
                    user = new User(id, nom, prenom, Role.valueOf(role));
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return user;
    }

    public List<User> selectAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
                // statement : SELECT * FROM User;
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String role = rs.getString("role");
                    users.add(new User(id, nom, prenom, Role.valueOf(role)));
                }
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
                // statement : DELETE FROM User WHERE id = ?;
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Connexion.getConnexion();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
                // statement : UPDATE User SET nom = ?, prenom = ?, role = ? WHERE id = ?;
                statement.setString(1, user.getNom());
                statement.setString(2, user.getPrenom());
                statement.setString(3, user.getRole().name());
                statement.setInt(4, user.getId());
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
