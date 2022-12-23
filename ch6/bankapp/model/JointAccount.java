package gr.aueb.cf.ch6.bankapp.model;

/**
 * creates the account which includes two holders
 */
public final class JointAccount {
    private final Long id1;
    private final String firstname1;
    private final String lastname1;
    private final Long id2;
    private final String firstname2;
    private final String lastname2;
    private final String ssn;

    //default constructor
    public JointAccount() {
        id1 = 0L;
        firstname1 = "";
        lastname1 = "";
        id2 = 0L;
        firstname2 = "";
        lastname2 = "";
        ssn = "";
    }

    //overloaded constructor
    public JointAccount(Long id1, String firstname1, String lastname1, Long id2, String firstname2, String lastname2, String ssn) {
        this.id1 = id1;
        this.firstname1 = firstname1;
        this.lastname1 = lastname1;
        this.id2 = id2;
        this.firstname2 = firstname2;
        this.lastname2 = lastname2;
        this.ssn = ssn;
    }


    //getters
    public Long getId1() {
        return id1;
    }

    public String getFirstname1() {
        return firstname1;
    }

    public String getLastname1() {
        return lastname1;
    }

    public Long getId2() {
        return id2;
    }

    public String getFirstname2() {
        return firstname2;
    }

    public String getLastname2() {
        return lastname2;
    }

    public String getSsn() {return ssn; }

    /**
     * returns the holders' state as a string
     * @return the holders' in a string format
     */
    public String convertToString() {
        return id1 + ", " + firstname1 + ", " + lastname1 + "\n" + id2 + ", " + firstname2 + ", " + lastname2 + "\n" + ssn;
    }
}
