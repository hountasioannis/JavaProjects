package gr.aueb.cf.schoolapp.service.exceptions;

public class UserNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    public UserNotFoundException(String s) {
        super(s);
    }
}
