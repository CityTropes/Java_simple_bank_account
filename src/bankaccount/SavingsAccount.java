package bankaccount;

public class SavingsAccount extends Account{

    private double interestRate = 4.5;
    private String stringCurrency = "NVT";

    public SavingsAccount(String accountOwner, int accountNumber, Enum currency) {
        super(accountOwner, accountNumber, currency);
        this.stringCurrency = currency.toString();
        this.interestRate = AccountDriver.freeEuroExchange(this.interestRate, super.getCurrency());
        System.out.println("\nConstructing CheckingAccount... Done.");
    }
    public SavingsAccount(String accountOwner, int accountNumber, Enum currency, double interestRate) {
        super(accountOwner, accountNumber, currency);
        this.interestRate = interestRate;
        this.stringCurrency = currency.toString();
    }

    public double getInterestRate(){ return this.interestRate; }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.printf("Amount " + stringCurrency + " %.2f deposited%n", amount);
            System.out.printf("Current Balance is: "+ stringCurrency+ " %.2f%n", balance);
        } else{
            System.out.println("Not possible to deposit a negative amount.");
        }
    }

    public void withdraw(double amount){
        if(amount > 0){
            if(amount <= balance){
                balance -= amount;
                System.out.printf("Amount " + stringCurrency + " %.2f withdrawn%n", amount);
                System.out.printf("Current Balance is: "+ stringCurrency+ " %.2f%n", balance);
            } else{
                System.out.println("Current balance (" + balance + stringCurrency + ") insufficient for this transaction.");
            }
        } else{
            System.out.println("Not possible to withdraw a negative amount.");
        }
    }

    public double calcInterestMonth() {
        return (balance * interestRate) / 12;
    }
    public void applyInterestMonth(){
        double interest = calcInterestMonth();
        deposit(interest);
        System.out.printf("Interest amount %.2f added to balance%n", interest);
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
