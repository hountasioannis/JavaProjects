package gr.aueb.cf.ch5.dao;

import gr.aueb.cf.ch5.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * This DAO layer class implements the Proxy Design Pattern.
 */
public class AccountDAOImpl implements IAccountDAO {
    private static final List<Account> accounts = new ArrayList<>();

    @Override
    public Account insertAccount(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public Account getAccountById(Long id) {
        int pos = getIndexById(id);
        if (pos == -1) return null;
        return accounts.get(pos);
    }

    @Override
    public Account getAccountByIban(String iban) {
        int pos = getIndexByIban(iban);
        if (pos == -1) return null;
        return accounts.get(pos);
    }

    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public void delete(String iban) {
        accounts.removeIf((account) -> account.getIban().equals(iban));
    }

    @Override
    public void deposit(String iban, double amount) {
        Account account = getAccountByIban(iban);
        double balance = account.getBalance() + amount;
        account.setBalance(balance);
    }

    @Override
    public void withdraw(String iban, double amount, String ssn) {
        Account account = getAccountByIban(iban);
        double balance = account.getBalance() - amount;
        account.setBalance(balance);
    }

    @Override
    public boolean accountIbanExists(String iban) {
        return getIndexByIban(iban) != -1;
    }

    @Override
    public boolean accountIdExists(Long id) {
        return getIndexById(id) != -1;
    }

    @Override
    public boolean isSsnValid(String iban, String ssn) {
        Account account = getAccountByIban(iban);
        return account.getSsn().equals(ssn);
    }

    @Override
    public boolean isPositiveAmount(double amount) {
        return amount > 0;
    }

    @Override
    public boolean isAmountLessThanBalance(String iban, double amount) {
        Account account = getAccountByIban(iban);
        return account.getBalance() >= amount;
    }

    /**
     * Returns the position in the Arraylist Datasource of the account having the input id
     * @param id to be searched
     * @return -1 if the position will not be found.
     */
    private int getIndexById(Long id) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(id)) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    /**
     * Returns the position in the Arraylist Datasource of the account having the input iban
     * @param iban to be searched
     * @return -1 if the position will not be found.
     */
    private int getIndexByIban(String iban) {
        int pos = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getIban().equals(iban)) {
                pos = i;
                break;
            }
        }

        return pos;
    }
}
