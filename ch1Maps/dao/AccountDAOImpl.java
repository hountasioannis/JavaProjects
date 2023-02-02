package gr.aueb.cf.ch1.dao;

import gr.aueb.cf.ch1.model.Account;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * This DAO layer class implements the Proxy Design Pattern.
 */
public class AccountDAOImpl implements IAccountDAO {
    private static final Map<Long, Account> accounts = new HashMap<>();

    @Override
    public Account insertAccount(Account account) {
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account getAccountById(Long id) {
        Long pos = getIndexById(id);
        if (pos == -1) return null;
        return accounts.get(pos);
    }

    @Override
    public Account getAccountByIban(String iban) {
        Long pos =  getIndexByIban(iban);
        if (pos == -1) return null;
        return accounts.get(pos);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts1 = accounts.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
        return accounts1;
    }

    @Override
    public void delete(String iban) {
        accounts.values().removeIf((account) -> account.getIban().equals(iban));
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
    private Long getIndexById(Long id) {
        Long pos = -1L;

        for (Map.Entry<Long, Account> entry : accounts.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                pos = entry.getKey();
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
    private Long getIndexByIban(String iban) {
        Long pos = -1L;

        for (Map.Entry<Long, Account> entry : accounts.entrySet()) {
            if (entry.getValue().getIban().equals(iban)) {
                pos = entry.getKey();
                break;
            }
        }

        return pos;
    }
}
