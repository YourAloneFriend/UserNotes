package com.application.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *      Servlet where user can create an account. After push submit button, account goes to
 *      check out if the given data is correct, and then it throws to the home page with success text and add
 *      data to the DB, or it prints an error with invalid data and user stays on the registration page.
 */

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{
}
