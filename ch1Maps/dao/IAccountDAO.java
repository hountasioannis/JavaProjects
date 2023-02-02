package gr.aueb.cf.ch1.dao;

import gr.aueb.cf.ch1.model.Account;


import java.util.List;

public interface IAccountDAO {
    /**
     * Inserts a new {@linkAccount} instance in the Datasource.
     * @param account that contains the account data.
     * @return the {@linkAccount}
     */
    Account insertAccount(Account account);

    /**
     * Returns a {@linkAccount} based on the input id.
     * @param id the {@linkAccount#id} to be returned.
     * @return the {@linkAccount}
     */
    Account getAccountById(Long id);

    /**
     * Returns a {@linkAccount} based on the input iban.
     * @param iban the {@linkAccount#iban} to be returned.
     * @return the {@linkAccount}
     */
    Account getAccountByIban(String iban);

    /**
     * Returns all the {@linkAccount} instances from th Datasource.
     * @return the {@linkList<Account>}
     */
    List<Account> getAll();

    /**
     * Removes the  {@linkAccount} from the Datasource.
     * @param iban of the {@linkAccount} needed to be removed.
     */
    void delete(String iban);

    /**
     * Deposits the {@linkAccount} of the Datasource.
     * @param iban to be withdrawn.
     * @param amount that will deposit to the {@linkAccount}
     */
    void deposit(String iban, double amount);

    /**
     * Withdraws an amount from the {@linkAccount}.
     * @param iban to be withdrawn.
     * @param amount to be withdrawn.
     * @param ssn of the {@linkAccount} that withdrawn.
     */
    void withdraw(String iban, double amount, String ssn);

    /**
     * Checks if an account already exists in the Datasource.
     * @param iban to be reached.
     * @return true, if the account exists.
     */
    boolean accountIbanExists(String iban);

    /**
     * Checks if an account already exists in the Datasource.
     * @param id to be reached.
     * @return true, if the account exists.
     */
    boolean accountIdExists(Long id);

    /**
     * Checks if a ssn is valid.
     * @param iban to be reached
     * @param ssn to be reached.
     * @return true, if the ssn is valid.
     */
    boolean isSsnValid(String iban, String ssn);

    /**
     * Checks if the amount is a positive number.
     * @param amount to be reached.
     * @return true, if the amount is positive.
     */
    boolean isPositiveAmount(double amount);

    /**
     * Checks if the amount is less than balance.
     * @param iban to be reached.
     * @param amount to be reached.
     * @return true, if tha amount is less than balance.
     */
    boolean isAmountLessThanBalance(String iban, double amount);
}
