package gr.aueb.cf.ch5.service.exceptions;

import gr.aueb.cf.ch5.model.Account;

public class AccountIdAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountIdAlreadyExistsException(Account account) {
        super("account with id " + account.getId() + " already exists");
    }
}
