package gr.aueb.cf.schoolapp.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MenuControllerForAdmin", value = "/schoolapp/menuforadmin")
public class MenuControllerForAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                .forward(request, response);
    }

}
