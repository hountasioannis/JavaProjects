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
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateUserController", value = "/schoolapp/updateuser")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userServ = new UserServiceImpl(userDAO);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/static/templates/userupdate.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");


            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setId(id);
            newUserDTO.setUsername(username);
            int workload = 12;
            String salt = BCrypt.gensalt(workload);
            String hashedPassword = BCrypt.hashpw(password, salt);
            UserDTO userDTO = new UserDTO();
            newUserDTO.setPassword(password);
            request.setAttribute("insertedUser", newUserDTO);

            try {
                String error = Validator.validate(newUserDTO);
                if (!error.equals("")) {
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                            .forward(request, response);
                } else {
                    if (!userDAO.UserExists(username)) {
                        newUserDTO.setPassword(hashedPassword);
                        userServ.updateUser(newUserDTO);
                        request.setAttribute("user", newUserDTO);
                        request.getRequestDispatcher("/schoolapp/static/templates/userupdated.jsp")
                                .forward(request, response);
                    } else {
                        request.setAttribute("error", "user already exists");
                        request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                                .forward(request, response);
                    }
                }
            } catch (UserNotFoundException | UserDAOException e) {
                String message = e.getMessage();
                request.setAttribute("isError", true);
                request.setAttribute("teacher", newUserDTO);
                request.getRequestDispatcher("/schoolapp/static/templates//userupdated.jsp")
                        .forward(request, response);

            }


    }
}
