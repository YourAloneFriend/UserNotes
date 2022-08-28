package com.application.database;

import com.application.model.Note;
import com.application.model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with notes in DB. UserDao realises CRUD methods for user.
 * @method getAllUserNotes - get all notes with user's id.
 * @method readNote - get note by id from DB and return its data(Note object).
 * @method updateNote - get note by id from DB and set all data from supplied Note object, except id, to note.
 * @method deleteNote - remove note by id from DB.
 * */
public interface NoteDao {
    static List<Note> getAllUserNotes(int userId) throws SQLException, IOException{
        List<Note> notes = new ArrayList<>();
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()) {
            String query = String.format("SELECT * FROM %s.Note WHERE user_id = '%s';",
                    dataBaseHandler.getDBName(), userId);
            ResultSet resultSet;

            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)) {
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    notes.add(Note.base()
                            .id(resultSet.getInt("id"))
                            .noteName(resultSet.getString("note_name"))
                            .note(resultSet.getString("note"))
                            .createdAt(resultSet.getString("created_at")).build());
            } catch (SQLException e){
                throw new SQLException("Can't get your notes. Something went wrong please message it to the administrator.");
            }
        }catch (IOException e){
            throw new IOException("Can't connect to DB or execute this query.");
        }

        return notes;
    }

    static void createNote(Note note) throws SQLException, IOException {
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()){
            String query = String.format("INSERT INTO %s.Note(note_name, note, user_id) VALUES('%s', '%s', '%s');",
                    dataBaseHandler.getDBName(), note.getNoteName().replace("'", "''"), note.getNote().replace("'", "''"), note.getUserId());

            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)) {
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                throw new SQLException("Can't add your note. Something went wrong, so please message it to the administrator.");
            }
        } catch (IOException e){
            throw new IOException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
    }

    static Note readNote(int noteId) throws SQLException, IOException {
        Note note = null;
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()){
            String query = String.format("SELECT * FROM %s.Note WHERE id = '%s';",
                    dataBaseHandler.getDBName(), noteId);
            ResultSet resultSet;

            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)) {
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    note = Note.base()
                            .userId(resultSet.getInt("user_id"))
                            .noteName(resultSet.getString("note_name"))
                            .note(resultSet.getString("note"))
                            .createdAt(resultSet.getString("created_at"))
                            .build();
            } catch (SQLException e){
                throw new SQLException("Can't read your note. Something went wrong, so please message it to the administrator.");
            }
        } catch (IOException e){
            throw new IOException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
        return note;
    }

    static void updateNote(Note note) throws SQLException, IOException {
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()) {
            String query = String.format("UPDATE %s.Note SET note_name = '%s', note = '%s' WHERE id = '%s';",
                    dataBaseHandler.getDBName(), note.getNoteName().replace("'", "''"), note.getNote().replace("'", "''"), note.getId());
            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)) {
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                throw new SQLException("Can't update your note with this id. Maybe something went wrong, so please message it to the administrator.");
            }
        } catch (IOException e){
            throw new IOException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
    }

    static void deleteNote(int noteId) throws SQLException, IOException {
        try(DataBaseHandler dataBaseHandler = new DataBaseHandler()) {
            String query = String.format("DELETE FROM %s.Note WHERE id = '%s';",
                    dataBaseHandler.getDBName(), noteId);
            try(PreparedStatement preparedStatement = dataBaseHandler.getDbConnection().prepareStatement(query)) {
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                throw new SQLException("Can't delete your note with this id. Maybe something went wrong, so please message it to the administrator.");
            }
        } catch (IOException e){
            throw new IOException("Can't connect to DB or execute this query. Something went wrong, so please message it to the administrator.");
        }
    }

}
