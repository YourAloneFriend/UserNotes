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
 *      Servlet where user can see a name of the chosen note, its content and an exit button to the home page.
 */

@WebServlet("/read-note")
public class ReadNoteServlet extends HttpServlet implements GetUserAndSetNote{

    private User user;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/pages/read-note.jsp");

        user = getUserAndSetNote(httpServletRequest, Integer.parseInt(httpServletRequest.getParameter("id")));

        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.sendRedirect("/home");
    }
}