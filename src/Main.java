import bankaccount.domain.Account;
import bankaccount.domain.Currency;
import bankaccount.domain.Money;
import bankaccount.domain.SavingsAccount;
import bankaccount.domain.repository.AccountRepository;
import bankaccount.domain.repository.MemoryAccountRepository;
import bankaccount.ui.AccountDriver;

public class Main {

    public static void main(String[] args) {

        //TestAddingMoney();

        AccountRepository accountRepo = new MemoryAccountRepository();

        accountRepo.save(new SavingsAccount("Jan", 666, Currency.EUR));
        accountRepo.save(new SavingsAccount("Tijs", 667, Currency.EUR));

        boolean success;

        AccountDriver accountDriver = new AccountDriver("testName");
        accountDriver.mainMenu();

        //Money moneyToDeposit = Money.fromBase(20, checkingAccount.getCurrency());
        //checkingAccount.deposit(moneyToDeposit);
    }


    public static void TestAddingMoney() {

        Money nenEuro = Money.createFromCent(100, Currency.EUR);
        Money aDollar = Money.createFromCent(100, Currency.USD);

        Money sum = null;
        try {
            sum = nenEuro.add(aDollar);
        } catch (IllegalArgumentException e) {
            System.out.println("Error-check: " + e.getMessage());
        }

        System.out.println(sum);
    }

}
