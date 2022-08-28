package com.application.controller;

import com.application.database.NoteDao;
import com.application.database.UserDao;
import com.application.model.Note;
import com.application.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Interface with default method to get user by id and set note as attribute.
 * */
public interface GetUserAndSetNote {
    default User getUserAndSetNote(HttpServletRequest httpServletRequest, int noteId) throws IOException {
        try {
            Note note = NoteDao.readNote(noteId);
            httpServletRequest.getSession().setAttribute("note", note);
            return UserDao.getUserById(note.getUserId());
        } catch (SQLException e) {
            httpServletRequest.getSession().setAttribute("alert", e.getMessage());
        }
        return null;
    }

}
