package com.application.database;

import com.application.model.Note;

import java.util.List;

/**
 * Class to work with notes in DB. UserDao realises CRUD methods for user.
 * @method getAllUserNotes - get all notes with user's id.
 * @method readNote - get note by id from DB and return its data(Note object).
 * @method updateNote - get note by id from DB and set all data from supplied Note object, except id, to note.
 * @method deleteNote - remove note by id from DB.
 * */
public interface NoteDao {
    static List<Note> getAllUserNotes(int userId){
        return null;
    }

    static void createNote(Note note){

    }

    static Note readNote(int noteId){
        return null;
    }

    static void updateNote(int noteId, Note note){

    }

    static void deleteNote(int noteId){

    }

}
