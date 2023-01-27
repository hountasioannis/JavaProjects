package gr.aueb.cf.ch5.service.exceptions;

public class SsnNotValidException extends Exception {
    private static final long serialVersionUID = 1L;

    public SsnNotValidException(String ssn) {
        super("ssn " + ssn + " is not valid");
    }
}
