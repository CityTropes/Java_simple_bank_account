package bankaccount;

import java.time.LocalDateTime;

public abstract class Account {

    private LocalDateTime creationTime;
    private String accountOwner;
    private int accountNumber;
    private Enum currency;
    protected double balance;
    //private char accountType; (checking/saving/Crypto)

    public Account(String accountOwner, int accountNumber, Enum currency) {
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.currency = currency;
        balance = 0;
        creationTime = LocalDateTime.now();
    }

    public String getAccountOwner(){
        return this.accountOwner;
    }
    public int getAccountNumber() { return this.accountNumber; }
    public LocalDateTime getCreationTime() { return creationTime;}
    public Enum getCurrency() {
        return currency;
    }
    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    //public abstract void closeAccount();
}
