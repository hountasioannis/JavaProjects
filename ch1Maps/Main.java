package gr.aueb.cf.ch1;

import gr.aueb.cf.ch1.dao.AccountDAOImpl;
import gr.aueb.cf.ch1.dto.AccountDTO;
import gr.aueb.cf.ch1.service.AccountServiceImpl;
import gr.aueb.cf.ch1.service.exceptions.*;


public class Main {

    public static void main(String[] args) {
        AccountDAOImpl dao = new AccountDAOImpl();
        AccountServiceImpl accountService = new AccountServiceImpl(dao);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setBalance(500.0);
        accountDTO.setFirstname("a");
        accountDTO.setLastname("a");
        accountDTO.setIban("123");
        accountDTO.setSsn("123");
        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(1L);
        accountDTO1.setBalance(500.0);
        accountDTO1.setFirstname("a");
        accountDTO1.setLastname("a");
        accountDTO1.setIban("123");
        accountDTO1.setSsn("123");
        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(2L);
        accountDTO2.setBalance(600.0);
        accountDTO2.setFirstname("b");
        accountDTO2.setLastname("b");
        accountDTO2.setIban("1234");
        accountDTO2.setSsn("1234");
        AccountDTO accountDTO3 = new AccountDTO();
        accountDTO3.setId(2L);
        accountDTO3.setBalance(600.0);
        accountDTO3.setIban("1234");


        try {
            System.out.println(accountService.insertAccount(accountDTO));
     //       System.out.println(accountService.insertAccount(accountDTO1));
            System.out.println(accountService.insertAccount(accountDTO2));
     //       System.out.println(accountService.insertAccount(accountDTO3));
        } catch (AccountIbanAlreadyExistsException | AccountIdAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            System.out.println(accountService.getAccountById(1L));
            //   System.out.println(accountService.getAccountById(3L));
            System.out.println(accountService.getAccountByIban("1234"));
            //    System.out.println(accountService.getAccountByIban("7"));
       } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
       System.out.println(accountService.getAll());
        System.out.println();

        try {
            accountService.delete("1234");
          //  accountService.delete("12");
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
       System.out.println(accountService.getAll());
        System.out.println();

        try {
            accountService.deposit("123", 400.0);
            //  accountService.deposit("123", -200.0);
           //   accountService.deposit("89", 100);
        } catch (InsufficientAmountException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println(accountService.getAll());
        System.out.println();


        try {
            accountService.withdraw("123", 250.0, "123");
            //  accountService.withdraw("123", 200.0,"456");
            //   accountService.withdraw("89", 100,"123");
           //   accountService.withdraw("123",5000,"123");
        } catch (InsufficientBalanceException | SsnNotValidException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
       }

        System.out.println();
        System.out.println(accountService.getAll());
        System.out.println();
    }
}
