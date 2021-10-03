import bankaccount.domain.Currency;
import bankaccount.domain.Money;
import bankaccount.ui.AccountDriver;

public class Main {

    public static void main(String[] args) {

        //TestAddingMoney();

        AccountDriver accountDriver = new AccountDriver("testName");
        accountDriver.mainMenu();

        //Money moneyToDeposit = Money.fromBase(20, checkingAccount.getCurrency());
        //checkingAccount.deposit(moneyToDeposit);
    }


    public static void TestAddingMoney() {

        Money nenEuro = Money.fromCent(100, Currency.EUR);
        Money aDollar = Money.fromCent(100, Currency.USD);

        Money sum = null;
        try {
            sum = nenEuro.add(aDollar);
        } catch (IllegalArgumentException e) {
            System.out.println("Error-check: " + e.getMessage());
        }

        System.out.println(sum);
    }

}
