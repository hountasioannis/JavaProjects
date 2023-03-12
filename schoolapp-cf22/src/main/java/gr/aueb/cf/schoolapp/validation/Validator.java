package gr.aueb.cf.schoolapp.validation;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;

public class Validator {
    private Validator() {}

    public static String validate(TeacherDTO dto) {
        if (dto.getFirstname().equals(""))  {
            return "Firstname: Empty";
        }

        if ((dto.getLastname().equals(""))) {
            return "Lastname: Empty";
        }

        return "";
    }

    public static String validate(UserDTO dto) {
        if (dto.getUsername().equals(""))  {
            return "email: Empty";
        }

        if ((dto.getPassword().equals(""))) {
            return "password: Empty";
        }

        return "";
    }
}
