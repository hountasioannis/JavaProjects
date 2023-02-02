package gr.aueb.cf.ch1.service.exceptions;

public class InsufficientAmountException extends Exception{
    private static final long serialVersionUID = 1L;

    public InsufficientAmountException(double amount) {
        super(" Amount " + amount + " is insufficient");
    }
}
