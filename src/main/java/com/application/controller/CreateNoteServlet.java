package com.application.controller;

import com.application.database.NoteDao;
import com.application.model.Note;
import com.application.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *      Servlet where user could create a note.
 */

@WebServlet("/create-note")
public class CreateNoteServlet extends HttpServlet {
    private User user;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/pages/create-note.jsp");
        user = (User)httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.getSession().setAttribute("alert-color", null);
        httpServletRequest.getSession().setAttribute("alert", null);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("button").equals("Back")) {
            httpServletResponse.sendRedirect("/home");
            return;
        }
        try {
            Note note = Note.base().userId(user.getId())
                    .noteName(httpServletRequest.getParameter("noteName"))
                    .note(httpServletRequest.getParameter("note")).build();

            NoteDao.createNote(note);

            httpServletRequest.getSession().setAttribute("alert-color", false);
            httpServletRequest.getSession().setAttribute("alert", "The note was successfully added!");
        }catch (Exception ex){
            httpServletRequest.getSession().setAttribute("alert-color", true);
            httpServletRequest.getSession().setAttribute("alert", ex.getMessage());
        }finally {
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/create-note.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

}