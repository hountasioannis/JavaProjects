package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IUserDAO userDAO = new UserDAOImpl();
	private final IUserService userService = new UserServiceImpl(userDAO);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(email);
		userDTO.setPassword(password);

		User user = null;
		try {
			user = AuthenticationProvider.authenticate(userDTO);
		if (user == null) {
			request.setAttribute("error", "user or password does not exist");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
			if(user.getUsername().equals("admin@gmail.com")) {
				HttpSession session1 = request.getSession(true);

				// Append to session object for personalization
				assert user != null;
				session1.setAttribute("username", userDTO.getUsername());

				session1.setMaxInactiveInterval(60 * 15); // 15 minutes

				// Create cookie with session ID
				Cookie cookie = new Cookie("JSESSIONID", session1.getId());
				System.out.println("Login gave: " + session1.getId());
				cookie.setMaxAge(session1.getMaxInactiveInterval());
				response.addCookie(cookie);
				response.sendRedirect(request.getContextPath() + "/schoolapp/menuforadmin");
			} else if ( user != null) {
				// Retrieve the current session, and if one doesn't exist
				// create it.
				HttpSession session = request.getSession(true);

				// Append to session object for personalization
				assert user != null;
				session.setAttribute("username", userDTO.getUsername());


				// Default session timeout is 30 min
				session.setMaxInactiveInterval(60 * 15); // 15 minutes

				// Create cookie with session ID
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				System.out.println("Login gave: " + session.getId());
				cookie.setMaxAge(session.getMaxInactiveInterval());
				response.addCookie(cookie);
				response.sendRedirect(request.getContextPath() + "/schoolapp/menu");
			}
		} catch (UserDAOException e) {
			e.getMessage();
		}


	}
}
