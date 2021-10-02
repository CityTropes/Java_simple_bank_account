import bankaccount.domain.CheckingAccount;
import bankaccount.domain.Currency;
import bankaccount.domain.Money;
import bankaccount.ui.AccountDriver;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        /*
        int five = 5;
        Integer five = new Integer(5);

        int sum = five + 5;
        Integer sum = new Integer(five + 5);
        */

        TestAddingMoney();


        AccountDriver accountDriver = new AccountDriver("testName");
        accountDriver.mainMenu();

        CheckingAccount checkingAccount = new CheckingAccount("tijs", 666, Currency.EUR);


        Money moneyToDeposit = Money.fromBase(20, checkingAccount.getCurrency());
        checkingAccount.deposit(moneyToDeposit);
    }


    public static void TestAddingMoney() {

        Money nenEuro = Money.fromCent(100, Currency.EUR);
        Money aDollar = Money.fromCent(100, Currency.USD);

        Money sum = null;
        try {
            sum = nenEuro.add(aDollar);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sum);
    }

}
