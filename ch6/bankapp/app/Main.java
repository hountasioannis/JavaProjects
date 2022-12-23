package gr.aueb.cf.ch6.bankapp.app;

import gr.aueb.cf.ch6.bankapp.model.JointAccount;
import gr.aueb.cf.ch6.bankapp.model.OverDraftAccount;

/**
 * creates an example of a bank account with two holders
 */
public class Main {

    public static void main(String[] args) {
        JointAccount holders = new JointAccount( 1L, "john", "chou", 2L, "george", "chou", "r123");
        OverDraftAccount holdersAccount = new OverDraftAccount(holders, "aaa", 100.5);
        System.out.println(holders.convertToString());

        System.out.println(holdersAccount.convertToString());
        try {
            holdersAccount.convertToString();
            holdersAccount.deposit(50.0);
            System.out.println(holdersAccount.convertToString());
            holdersAccount.withdraw( 200.5, "r123");
            System.out.println(holdersAccount.convertToString());
            holdersAccount.withdraw(80.0, "a123");
            System.out.println(holdersAccount.convertToString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
