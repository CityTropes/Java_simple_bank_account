package bankaccount.domain;
//Checking account with transaction fee

public class CheckingAccount extends Account {

    private Money transactionFee = Money.fromCent(50, Currency.EUR);

    public CheckingAccount(String accountOwner, int accountNumber, Currency currency, Money transactionFee) {
        super(accountOwner, accountNumber, currency);
        this.transactionFee = CurrencyConverter.convert(transactionFee, currency);
    }

    public CheckingAccount(String accountOwner, int accountNumber, Currency currency) {
        super(accountOwner, accountNumber, currency);
        this.transactionFee = CurrencyConverter.convert(transactionFee, currency);
        System.out.println("\nConstructing CheckingAccount... Done.");
    }

    public Money getTransactionFee() {
        return this.transactionFee;
    }

    public void deposit(Money amount) {
        if (amount.isNotZero()) {
            balance = balance.add(amount).sub(transactionFee);
            System.out.printf("Amount %s deposited%n", amount);
            System.out.printf("Transaction fee %s Applied%n", transactionFee);
            System.out.printf("Current Balance is: %s%n", balance);
        } else {
            System.out.println("Not possible to deposit a negative amount.");
        }
    }

    public void withdraw(Money amount) {
        if (amount.isNotZero()) {
            Money withdrawWithAddedFee = amount.add(transactionFee);
            if (withdrawWithAddedFee.lessThanOrEqual(balance)) {
                balance = balance.sub(withdrawWithAddedFee);
                System.out.printf("Amount %s withdrawn%n", amount);
                System.out.printf("Transaction fee %s Applied%n", transactionFee);
                System.out.printf("Current Balance is: %s%n", balance);
            } else {
                System.out.println("Current balance (" + balance + ") insufficient for this transaction.");
            }
        } else {
            System.out.println("Not possible to withdraw a negative amount.");
        }
    }

}
