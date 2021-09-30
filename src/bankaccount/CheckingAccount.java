package bankaccount;
//Checking account with transaction fee

public class CheckingAccount extends Account {

    private double transactionFee = 0.5;
    private String stringCurrency = "NVT";

    public CheckingAccount(String accountOwner, int accountNumber, Enum currency, double transactionFee) {
        super(accountOwner, accountNumber, currency);
        this.transactionFee = transactionFee;
        this.stringCurrency = currency.toString();
    }
    public CheckingAccount(String accountOwner, int accountNumber, Enum currency){
        super(accountOwner, accountNumber, currency);
        this.stringCurrency = currency.toString();
        this.transactionFee = AccountDriver.freeEuroExchange(this.transactionFee, super.getCurrency());
        System.out.println("\nConstructing CheckingAccount... Done.");
    }

    public double getTransactionFee(){ return this.transactionFee;}

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            balance -= transactionFee;
            System.out.printf("Amount " +stringCurrency+ " %.2f deposited%n", amount);
            System.out.printf("Transaction fee " +stringCurrency+ " %.2f Applied%n", transactionFee);
            System.out.printf("Current Balance is: "+ stringCurrency+ " %.2f%n", balance);
        } else{
            System.out.println("Not possible to deposit a negative amount.");
        }
    }

    public void withdraw(double amount){
        if(amount > 0){
            if((amount + transactionFee) <= balance){
                balance -= amount;
                balance -= transactionFee;
                System.out.printf("Amount " +stringCurrency+ " %.2f withdrawn%n", amount);
                System.out.printf("Transaction fee "+stringCurrency+ " %.2f Applied%n", transactionFee);
                System.out.printf("Current Balance is: "+ stringCurrency+ " %.2f%n", balance);
            } else{
                System.out.println("Current balance (" + balance + stringCurrency + ") insufficient for this transaction.");
            }
        } else{
            System.out.println("Not possible to withdraw a negative amount.");
        }
    }
}
