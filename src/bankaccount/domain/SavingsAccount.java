package bankaccount.domain;

public class SavingsAccount extends Account {

    private double interestRate = 4.5;

    public SavingsAccount(String accountOwner, int accountNumber, Currency currency) {
        super(accountOwner, accountNumber, currency);
        System.out.println("\nConstructing CheckingAccount... Done.");
    }

    public SavingsAccount(String accountOwner, int accountNumber, Currency currency, double interestRate) {
        super(accountOwner, accountNumber, currency);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void deposit(Money amount) {
        if (amount.isNotZero()) {
            balance = balance.add(amount);
            System.out.printf("Amount %s deposited%n", amount);
            System.out.printf("Current Balance is: %s%n", balance);
        } else {
            System.out.println("Not possible to deposit a negative amount.");
        }
    }

    public void withdraw(Money amount) {
        if (amount.isNotZero()) {
            if (amount.lessThanOrEqual(balance)) {
                balance = balance.sub(amount);
                System.out.printf("Amount %s withdrawn%n", amount);
                System.out.printf("Current Balance is: %s%n", balance);
            } else {
                System.out.println("Current balance (" + balance + ") insufficient for this transaction.");
            }
        } else {
            System.out.println("Not possible to withdraw a negative amount.");
        }
    }

    public void applyMonthlyInterest() {
        Money monthlyInterest = balance.multiply(interestRate / 12f);
        deposit(monthlyInterest);
        System.out.printf("Interest amount %s added to balance%n", monthlyInterest);
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
