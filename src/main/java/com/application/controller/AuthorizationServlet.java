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
 *      Servlet where user will authorize into its account.
 */

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/pages/authorization.jsp");
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
                    .email(httpServletRequest.getParameter("email"))
                    .password(httpServletRequest.getParameter("password"))
                    .build();

            user = UserDao.userLogin(user);
            httpServletRequest.getSession().setAttribute("user", user);
            httpServletResponse.sendRedirect("/home");
        } catch (Exception ex){
            httpServletRequest.getSession().setAttribute("alert", ex.getMessage());
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/authorization.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

}