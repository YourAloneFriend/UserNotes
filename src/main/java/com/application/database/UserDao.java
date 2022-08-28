package com.application.database;

import com.application.model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Class to work with users in DB. UserDao realises CRUD methods for user.
 * @method userRegistration - create new user.
 * @method userLogin - user authentication.
 * */
public interface UserDao {

    static void userRegistration(User user) throws SQLException, IOException {
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()) {
            String query = String.format("INSERT INTO %s.User(username, email, password) VALUES('%s', '%s', '%s');",
                    dataBaseHandler.getDBName(), user.getUserName(), user.getEmail(), user.getPassword());

            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)){
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("User with this email is already registered.");
            }
        } catch (IOException e){
            throw new IOException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
    }

    static User userLogin(User user) throws SQLException{
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()) {
            String query = String.format("SELECT * FROM %s.User WHERE email = '%s';",
                    dataBaseHandler.getDBName(), user.getEmail());
            ResultSet resultSet;
            Integer id = null;
            String password = null, username = null, email = null;

            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)){
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    password = resultSet.getString("password");
                    username = resultSet.getString("username");
                    email = resultSet.getString("email");
                }
                if(id == null)
                    throw new SQLException();
                if (!password.equals(user.getPassword()))
                    throw new IllegalArgumentException();

                return User.base().id(id).userName(username).email(email).password(password).build();
            } catch (SQLException e) {
                throw new SQLException("There is no registered account with this email.");
            } catch (IllegalArgumentException e) {
                throw new SQLException("Wrong password.");
            }
        } catch (IOException e) {
            throw new SQLException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
    }

    static User getUserById(int userId) throws SQLException, IOException {
        User user = null;
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()) {

            String query = String.format("SELECT * FROM %s.User WHERE id = '%s';",
                    dataBaseHandler.getDBName(), userId);
            ResultSet resultSet;

            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)){
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    user = User.base()
                            .id(resultSet.getInt("id"))
                            .userName(resultSet.getString("username"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password")).build();
            } catch (SQLException e) {
                throw new SQLException("There is no registered account with this id.");
            }
        } catch (IOException e) {
            throw new SQLException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
        return user;
    }
}
