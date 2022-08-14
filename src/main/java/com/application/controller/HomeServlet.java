package com.application.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *      Servlet where user is in the main page. There is shown all its notes from top to bottom,
 *      links in opposite to read, update and delete a note. In the down there is a button to create a note and exit
 *      to the home page.
 */

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
}