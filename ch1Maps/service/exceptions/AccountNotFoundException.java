package gr.aueb.cf.ch1.service.exceptions;

import gr.aueb.cf.ch1.model.Account;


public class AccountNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String iban) {
        super("the account with iban " + iban + " was not found");
    }

    public AccountNotFoundException(Long id) {
        super("the account with id " + id + " was not found");
    }

    public AccountNotFoundException(Account account) {
        super("the account  " + account + " was not found");
    }
}
