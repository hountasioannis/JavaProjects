package gr.aueb.cf.ch1.service.exceptions;

import gr.aueb.cf.ch1.model.Account;


public class InsufficientBalanceException extends Exception {
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException(Account account, double amount) {
        super("insufficient balance " + account.getBalance() + " for amount " + amount);
    }
}
