package bankaccount.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Account {

    private final LocalDateTime creationTime;
    private final String accountOwner;
    private final int accountNumber;
    protected Money balance;
    //private char accountType; (checking/saving)

    public Account(String accountOwner, int accountNumber, Currency currency) {
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        balance = Money.fromCent(0, currency);
        creationTime = LocalDateTime.now();
    }

    public String getAccountOwner() {
        return this.accountOwner;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Currency getCurrency() {
        return balance.getCurrency();
    }

    public Money getBalance() {
        return balance;
    }



    public void transfer(Account target, Money amount) {
        withdraw(amount);
        target.deposit(amount);
    }

    public abstract void deposit(Money amount);

    public abstract void withdraw(Money amount);
    //public abstract void closeAccount();
}
