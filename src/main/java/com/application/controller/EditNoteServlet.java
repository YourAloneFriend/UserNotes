package com.application.controller;

import com.application.database.NoteDao;
import com.application.database.UserDao;
import com.application.model.Note;
import com.application.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *      Servlet where user can update a name of the chosen note, its content and an exit button to the home page.
 *      And can edit note's name and text.
 */

@WebServlet("/edit-note")
public class EditNoteServlet extends HttpServlet implements GetUserAndSetNote{

    private User user;
    private int noteId;
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/pages/edit-note.jsp");

        noteId = Integer.parseInt(httpServletRequest.getParameter("id"));
        user = getUserAndSetNote(httpServletRequest, noteId);

        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        if(httpServletRequest.getParameter("button").equals("Back")) {
            httpServletResponse.sendRedirect("/home");
            return;
        }
        try {
            Note note = Note.base().id(noteId)
                    .noteName(httpServletRequest.getParameter("noteName"))
                    .note(httpServletRequest.getParameter("note"))
                    .userId(user.getId()).build();

            NoteDao.updateNote(note);
            httpServletRequest.getSession().setAttribute("alert-color", false);
            httpServletRequest.getSession().setAttribute("alert", "The note was successfully updated!");
            httpServletResponse.sendRedirect(String.format("/read-note?id=%s", noteId));
        }catch (Exception ex){
            httpServletRequest.getSession().setAttribute("alert-color", true);
            httpServletRequest.getSession().setAttribute("alert", ex.getMessage());
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/edit-note.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}