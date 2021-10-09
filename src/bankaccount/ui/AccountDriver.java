package bankaccount.ui;

import bankaccount.domain.*;

import java.util.Scanner;

public class AccountDriver {
    private final Scanner keyboardIn = new Scanner(System.in);
    private final String userName;
    protected final Account[] accounts = new Account[50];
    protected int numAccounts = 0;

    public AccountDriver(String userName) {
        this.userName = userName;
        System.out.println("AccountDriver started (input 0 at main menu to run test)... Current userName: " + userName);
    }

    public void mainMenu() {
        int choice;
        do {
            choice = AccountMenus.giveMainMenuInput(keyboardIn);

            if (choice == 1) {
                accounts[numAccounts++] = createAccount(keyboardIn);
            } else if (choice == 2) {
                doDeposit(accounts, numAccounts, keyboardIn);
            } else if (choice == 3) {
                doWithdraw(accounts, numAccounts, keyboardIn);
            } else if (choice == 4) {
                showAccountOverview(accounts, numAccounts, keyboardIn);
            } else if (choice == 0) {
                test();
            } else {
                System.out.println("Goodbye!");
            }
        } while (choice != 5);
    }


    public Account createAccount(Scanner keyboardIn) {

        Account account = null;
        int choice = AccountMenus.giveAccountMenuInput(keyboardIn);
        int accountNumber;
        int exist;
        do {
            System.out.print("Enter a new account number. ");
            accountNumber = keyboardIn.nextInt();
            exist = checkAccountNrs(accounts, numAccounts, accountNumber);
            if (exist >= 0) {
                System.out.println("This account number already exists. Try again.");
            }
        } while (exist >= 0);

        if (choice == 1) { //checking account
            System.out.println("Choose a currency for this Checking account (type 'EUR', 'USD', or 'GBP')");
            String curr;
            do {
                curr = keyboardIn.next();
                if (Currency.isValid(curr)) {
                    Currency currency = Enum.valueOf(Currency.class, curr);
                    account = new CheckingAccount(userName, accountNumber, currency);
                    System.out.println("New CheckingAccount created.");
                } else if (!Currency.isValid(curr)) {
                    System.out.println("Currency " + curr + " not available. Try again (type 'EUR', 'USD', or 'GBP').");
                }
            } while (!Currency.isValid(curr));
        } else { //savings account
            System.out.println("Choose a currency for this Savings account (type 'EUR', 'USD', or 'GBP')");
            String curr;
            do {
                curr = keyboardIn.next();
                if (Currency.isValid(curr)) {
                    Currency currency = Enum.valueOf(Currency.class, curr);
                    account = new SavingsAccount(userName, accountNumber, currency);
                    System.out.println("New SavingsAccount created.");
                } else {
                    System.out.println("Currency " + curr + " not available. Try again (type 'EUR', 'USD', or 'GBP').");
                }
            } while (!Currency.isValid(curr));
        }
        return account;
    }

    public static int searchAccount(Account[] accounts, int numAccounts, int accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return -1;
    }

    public static int checkAccountNrs(Account[] accounts, int numAccounts, int accountNumber) {
        for (int i = 0; i < numAccounts - 1; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return -1;
    }

    public static void doDeposit(Account[] accounts, int numAccounts, Scanner keyboardIn) {
        System.out.print("Enter Account Number ");
        int accountNumber = keyboardIn.nextInt();

        //search for account
        int index = searchAccount(accounts, numAccounts, accountNumber);

        if (index >= 0) {
            System.out.print("Enter Deposit Amount: ");
            Money amount = Money.createFromBase(keyboardIn.nextDouble(), accounts[index].getCurrency());
            accounts[index].deposit(amount);
        } else {
            System.out.print("No account exists with AccountNumber: " + accountNumber);
        }
    }

    public static void doWithdraw(Account[] accounts, int numAccounts, Scanner keyboardIn) {
        //get the account number
        System.out.print("Enter Account Number ");
        int accountNumber = keyboardIn.nextInt();

        //search for account
        int index = searchAccount(accounts, numAccounts, accountNumber);

        if (index >= 0) {
            //amount
            System.out.print("Enter Withdraw Amount: ");
            Money amount = Money.createFromBase(keyboardIn.nextDouble(), accounts[index].getCurrency());
            accounts[index].withdraw(amount);
        } else {
            System.out.print("No account exists with AccountNumber: " + accountNumber);
        }
    }

    public static void showAccountOverview(Account[] accounts, int numAccounts, Scanner keyboardIn) {
        System.out.print("Enter Account Number ");
        int accountNumber = keyboardIn.nextInt();
        //search for account
        int index = searchAccount(accounts, numAccounts, accountNumber);

        Currency currency = accounts[index].getCurrency();
        String curr = currency.toString();

        double interest = 0;
        if (accounts[index] instanceof SavingsAccount) {
            interest = ((SavingsAccount) accounts[index]).getInterestRate();
        }
        Money transactionFee = Money.createFromCent(0, currency);
        if (accounts[index] instanceof CheckingAccount) {
            transactionFee = ((CheckingAccount) accounts[index]).getTransactionFee();
        }

        System.out.println("\nOverview account number " + accounts[index].getAccountNumber() + ":");
        System.out.println("\tCreation date: " + accounts[index].getCreationTime() + "\n\tAccount owner: " + accounts[index].getAccountOwner() + "\n\tBalance: " + accounts[index].getBalance() + "\n\tCurrency: " + curr);
        if (accounts[index] instanceof SavingsAccount) {
            System.out.println("\tYearly interest: " + interest + "%");
        }
        if (accounts[index] instanceof CheckingAccount) {
            System.out.println("\tTransaction fee: " + transactionFee);
        }
    }




    //FOR TESTING
    // todo: To fix -> it's possible to make new accounts with existing accountNumbers, using test()
    // that's why running this multiple times creates empty accounts in [] & adds/subtracts from previously made test accounts (based on index)
    // -> temp fixed by method checkAccountNrs() in method createAccount() -> asks for new nr when given nr already exists.
    public void test() {

        accounts[numAccounts++] = new CheckingAccount(userName, 1, Currency.USD);
        int index = searchAccount(accounts, numAccounts, 1);
        accounts[index].deposit(Money.createFromBase(70000, Currency.USD));
        accounts[index].withdraw(Money.createFromBase(10000, Currency.USD));
        Currency currency = accounts[index].getCurrency();
        String curr = currency.toString();
        System.out.println("\nOverview account number " + accounts[index].getAccountNumber() + ":");
        System.out.println("\tCreation date: " + accounts[index].getCreationTime() + "\n\tAccount owner: " + accounts[index].getAccountOwner() + "\n\tBalance: " + accounts[index].getBalance() + "\n\tCurrency: " + curr);

        accounts[numAccounts++] = new CheckingAccount(userName, 2, Currency.GBP);
        index = searchAccount(accounts, numAccounts, 2);
        accounts[index].deposit(Money.createFromBase(5000, Currency.GBP));
        currency = accounts[index].getCurrency();
        curr = currency.toString();
        System.out.println("\nOverview account number " + accounts[index].getAccountNumber() + ":");
        System.out.println("\tCreation date: " + accounts[index].getCreationTime() + "\n\tAccount owner: " + accounts[index].getAccountOwner() + "\n\tBalance: " + accounts[index].getBalance() + "\n\tCurrency: " + curr);

        accounts[numAccounts++] = new SavingsAccount(userName, 3, Currency.EUR);
        index = searchAccount(accounts, numAccounts, 3);
        accounts[index].deposit(Money.createFromBase(80000, Currency.EUR));
        currency = accounts[index].getCurrency();
        curr = currency.toString();
        System.out.println("\nOverview account number " + accounts[index].getAccountNumber() + ":");
        System.out.println("\tCreation date: " + accounts[index].getCreationTime() + "\n\tAccount owner: " + accounts[index].getAccountOwner() + "\n\tBalance: " + accounts[index].getBalance() + "\n\tCurrency: " + curr);
        System.out.println("\nTest 'account creation & functions' completed.");

        for (int i = 0; i < numAccounts; i++) {
            System.out.println(accounts[i].getCreationTime() + "\n" + accounts[i].getBalance());
        }
    }

}


