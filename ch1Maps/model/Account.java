package gr.aueb.cf.ch1.model;

public class Account extends AbstractEntity {
    private String firstname;
    private String lastname;
    private String ssn;
    private String iban;
    private double balance;

    public Account() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", ssn='" + ssn + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.getBalance(), getBalance()) != 0) return false;
        if (getFirstname() != null ? !getFirstname().equals(account.getFirstname()) : account.getFirstname() != null)
            return false;
        if (getLastname() != null ? !getLastname().equals(account.getLastname()) : account.getLastname() != null)
            return false;
        if (!getSsn().equals(account.getSsn())) return false;
        return getIban().equals(account.getIban());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getFirstname() != null ? getFirstname().hashCode() : 0;
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + getSsn().hashCode();
        result = 31 * result + getIban().hashCode();
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
