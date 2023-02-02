package gr.aueb.cf.ch1.service;

import gr.aueb.cf.ch1.dto.AccountDTO;
import gr.aueb.cf.ch1.model.Account;
import gr.aueb.cf.ch1.service.exceptions.*;


import java.util.List;

public interface IAccountService {

    /**
     * Inserts a new {@linkAccount} instance in the Datasource.
     * @param accountDTO that contains the account data.
     * @return the resulting account
     * @throws AccountIbanAlreadyExistsException if the {@accountDTO#iban} already exists.
     * @throws AccountIdAlreadyExistsException if the {@accountDTO#id} already exists.
     */
    Account insertAccount(AccountDTO accountDTO)
    throws AccountIbanAlreadyExistsException, AccountIdAlreadyExistsException;

    /**
     * Returns a {@linkAccount} based on the input id.
     * @param id the {@linkAccount#id} to be returned.
     * @return the {@linkAccount}
     * @throws AccountNotFoundException if the {@account#id} does not exist.
     */
    Account getAccountById(Long id)
    throws AccountNotFoundException;

    /**
     * Returns a {@linkAccount} based on the input iban.
     * @param iban the {@linkAccount#iban} to be returned.
     * @return the {@linkAccount} if the {@account#iban} does not exist.
     * @throws AccountNotFoundException
     */
    Account getAccountByIban(String iban)
    throws AccountNotFoundException;

    /**
     * Returns all the {@linkAccount} instances from th Datasource.
     * @return the {@linkList<Account>}
     */
    List<Account> getAll();

    /**
     * Removes the  {@linkAccount} from the Datasource.
     * @param iban of the {@linkAccount} needed to be removed.
     * @throws AccountNotFoundException if the {@account#iban} does not exist.
     */
    void delete(String iban)
    throws AccountNotFoundException;

    /**
     * Deposits the {@linkAccount} of the Datasource.
     * @param iban to be deposited.
     * @param amount that will deposit to the {@linkAccount}
     * @throws InsufficientAmountException if the amount is insufficient.
     * @throws AccountNotFoundException if the {@account#iban} does not exist.
     */
    void deposit(String iban, double amount)
    throws InsufficientAmountException,AccountNotFoundException;

    /**
     * Withdraws an amount from the {@linkAccount}.
     * @param iban to be withdrawn.
     * @param amount to be withdrawn.
     * @param ssn of the {@linkAccount} that withdrawn.
     * @throws InsufficientBalanceException if the balance is insufficient.
     * @throws SsnNotValidException if the Ssn is not valid.
     * @throws AccountNotFoundException if the {@account#iban} does not exist.
     *
     */
    void withdraw(String iban, double amount, String ssn)
    throws InsufficientBalanceException,SsnNotValidException,AccountNotFoundException;

}
