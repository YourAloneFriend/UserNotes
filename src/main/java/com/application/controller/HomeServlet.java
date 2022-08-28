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
import java.util.List;

/**
 *      Servlet where user is in the home page. There is shown all its notes from top to bottom,
 *      links in opposite to update and delete a note. Notes' name are links to notes' body.
 *      In the down there is a button to create a note and exit to the authentication page.
 */

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private User user;
    private List<Note> notes;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/pages/home.jsp");
        httpServletRequest.getSession().setAttribute("alert", null);
        httpServletRequest.getSession().setAttribute("alert-color", null);

        user = (User)httpServletRequest.getSession().getAttribute("user");
        httpServletRequest.getSession().setAttribute("user", user);

        try {
            notes = NoteDao.getAllUserNotes(user.getId());
            httpServletRequest.getSession().setAttribute("notes", notes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        if(httpServletRequest.getParameter("button").equals("Log out"))
            httpServletResponse.sendRedirect("/authentication");
        else {
            httpServletRequest.getSession().setAttribute("user", user);
            httpServletResponse.sendRedirect("/create-note");
        }
    }
}