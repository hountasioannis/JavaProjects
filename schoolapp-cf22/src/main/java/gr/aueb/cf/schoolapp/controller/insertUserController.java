package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "insertUserController", value = "/schoolapp/insertUser")
public class insertUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userServ = new UserServiceImpl(userDAO);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("error", "");
        String email = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
            int workload = 12;
            String salt = BCrypt.gensalt(workload);
            String hashedPassword = BCrypt.hashpw(password, salt);
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(email);
            userDTO.setPassword(password);
            request.setAttribute("insertedUser", userDTO);
            try {
                String error = Validator.validate(userDTO);
                if (!error.equals("")) {
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                            .forward(request, response);

                } else {
                    if (!userDAO.UserExists(userDTO.getUsername())) {
                        userDTO.setPassword(hashedPassword);
                        userServ.insertUser(userDTO);
                        request.getRequestDispatcher("/schoolapp/static/templates/userinserted.jsp")
                                .forward(request, response);
                    } else {
                        request.setAttribute("error", "user already exists");
                        request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                                .forward(request, response);
                    }
                }
            } catch (UserDAOException e) {
                //e.printStackTrace();
                request.setAttribute("sqlError", true);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }

    }
}



