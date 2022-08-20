package com.application.controller;

import com.application.database.UserDao;
import com.application.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *      Servlet where user can create an account. After push submit button, account goes to
 *      check out if the given data is correct, and then it throws to the home page with success text and add
 *      data to the DB, or it prints an error with invalid data and user stays on the registration page.
 */

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/pages/registration.jsp");
        httpServletRequest.getSession().setAttribute("alert-color", null);
        httpServletRequest.getSession().setAttribute("alert", null);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("button").equals("Back")) {
            httpServletResponse.sendRedirect("/");
            return;
        }

        try {
            User user = User.base()
                    .userName(httpServletRequest.getParameter("username"))
                    .email(httpServletRequest.getParameter("email"))
                    .checkPasswordsEquality(httpServletRequest.getParameter("password1"), httpServletRequest.getParameter("password2"))
                    .password(httpServletRequest.getParameter("password1"))
                    .build();

            UserDao.userRegistration(user);

            httpServletRequest.getSession().setAttribute("alert-color", false);
            httpServletRequest.getSession().setAttribute("alert", "Account is successfully registered!");
        } catch (Exception ex){
            httpServletRequest.getSession().setAttribute("alert-color", true);
            httpServletRequest.getSession().setAttribute("alert", ex.getMessage());
        } finally {
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

}
