package com.application.controller;

import com.application.database.NoteDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Delete note by id.
 * */
@WebServlet("/delete-note")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        try {
            NoteDao.deleteNote(Integer.parseInt(httpServletRequest.getParameter("id")));
        } catch (SQLException e) {
            httpServletRequest.getSession().setAttribute("alert-color", true);
            httpServletRequest.getSession().setAttribute("alert", e.getMessage());
        }
        httpServletResponse.sendRedirect("/home");
    }

}
