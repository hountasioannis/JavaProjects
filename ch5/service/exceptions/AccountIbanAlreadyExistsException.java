package gr.aueb.cf.ch5.service.exceptions;

import gr.aueb.cf.ch5.model.Account;

public class AccountIbanAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountIbanAlreadyExistsException(Account account) {
        super("account with iban " + account.getIban() + " already exists");
    }
}
