package gr.aueb.cf.ch6.bankapp.model;

/**
 * creates an account with the holders.Includes public API methods
 */
public final class OverDraftAccount {
    private final JointAccount holders;
    private final String iban;
    private double balance;

    //default constructor
    public OverDraftAccount() {
        holders = new JointAccount();
        iban = "";
    }

    //overloaded constructor
    public OverDraftAccount(JointAccount holders, String iban, double balance) {
        this.holders = holders;
        this.iban = iban;
        this.balance = balance;
    }

    //getters and setters
    public JointAccount getHolders() {
        return holders;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //public API

    /**
     * deposits a zero or positive ammount to account's balance
     * @param amount to be deposited
     * @throws Exception if the amount is negative
     */
    public void deposit(double amount) throws Exception {
        try {
            if (amount >= 0) {
                balance += amount;
            } else {
                throw new Exception("negative amount problem");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * withdraws a certain amount from account's balance based on a ssn number
     * @param amount to be withdrawn
     * @param ssn the social security number
     * @throws Exception if ssn is not valid
     */
    public void withdraw(double amount, String ssn) throws Exception {
        try {
            if (!isSsnValid(ssn)){
                throw new Exception(" ssn is not valid");
            }
            balance -= amount;
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean isSsnValid (String ssn) {
        return this.holders.getSsn().equals(ssn);
    }

    /**
     * returns the account balance
     * @return the account balance
     */
    public double getAccountBalance() {
        return getBalance();
    }

    /**
     * returns the account's state as a string
     * @return the account in a string format
     */
    public String convertToString() {
        return holders.convertToString() + ", " + iban + ", " + balance;
    }
}
