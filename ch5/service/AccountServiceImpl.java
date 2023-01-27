package gr.aueb.cf.ch5.service;

import gr.aueb.cf.ch5.dao.IAccountDAO;
import gr.aueb.cf.ch5.dto.AccountDTO;
import gr.aueb.cf.ch5.model.Account;
import gr.aueb.cf.ch5.service.exceptions.*;

import java.util.List;

/**
 * This service layer class implements  the Proxy Design Pattern.
 */
public class AccountServiceImpl implements IAccountService {
    private final IAccountDAO dao;

    public AccountServiceImpl(IAccountDAO dao) {
        this.dao = dao;
    }

    @Override
    public Account insertAccount(AccountDTO accountDTO) throws AccountIbanAlreadyExistsException, AccountIdAlreadyExistsException {
        Account account;
        try {
            account = new Account();
            mapAccount(account, accountDTO);

            if (dao.accountIbanExists(accountDTO.getIban())) {
                throw new AccountIbanAlreadyExistsException(account);
            }
            if (dao.accountIdExists(accountDTO.getId())) {
                throw new AccountIdAlreadyExistsException(account);
            }

            return dao.insertAccount(account);
        } catch (AccountIbanAlreadyExistsException | AccountIdAlreadyExistsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Account getAccountById(Long id) throws AccountNotFoundException {
        Account account;
        try {
            account = dao.getAccountById(id);

            if (account == null) {
                throw new AccountNotFoundException(id);
            }

            return dao.getAccountById(id);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Account getAccountByIban(String iban) throws AccountNotFoundException {
        Account account;
        try {
            account = dao.getAccountByIban(iban);

            if (account == null) {
                throw new AccountNotFoundException(iban);
            }

            return dao.getAccountByIban(iban);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Account> getAll() {
        return dao.getAll();
    }

    @Override
    public void delete(String iban) throws AccountNotFoundException {
        Account account;
        try {
            account = new Account();
            if (!dao.accountIbanExists(iban)) {
                throw new AccountNotFoundException(iban);
            }

            dao.delete(iban);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deposit(String iban, double amount) throws InsufficientAmountException,AccountNotFoundException {
        Account account;
        try {
            account = dao.getAccountByIban(iban);

            if (account == null) {
                throw new AccountNotFoundException(iban);
            }

            if (!dao.isPositiveAmount(amount)) {
                throw new InsufficientAmountException(amount);
            }

            dao.deposit(account.getIban(), amount);
        } catch (InsufficientAmountException | AccountNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void withdraw(String iban, double amount, String ssn) throws InsufficientBalanceException, SsnNotValidException,AccountNotFoundException {
       Account account;
        try {
            account = dao.getAccountByIban(iban);

            if (account == null) {
                throw new AccountNotFoundException(iban);
            }

            if (!dao.isSsnValid(account.getIban(),ssn)) {
                throw new SsnNotValidException(ssn);
            }
            if (!dao.isAmountLessThanBalance(account.getIban(),amount)) {
                throw new InsufficientBalanceException(account, amount);
            }

            dao.withdraw(account.getIban(), amount, ssn);
        } catch (InsufficientBalanceException | SsnNotValidException | AccountNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void mapAccount(Account account, AccountDTO accountDTO) {
        account.setId(accountDTO.getId());
        account.setFirstname(accountDTO.getFirstname());
        account.setLastname(accountDTO.getLastname());
        account.setSsn(accountDTO.getSsn());
        account.setIban(accountDTO.getIban());
        account.setBalance(accountDTO.getBalance());
    }
}
