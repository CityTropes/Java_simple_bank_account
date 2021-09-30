package bankaccount;

import java.util.Scanner;

public class AccountMenus{

    public static int giveMainMenuInput(Scanner keyboard) {
        System.out.println("\nBank Account Menu");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Show Account Overview");
        System.out.println("5. Quit");

        int choice;

        do {
            System.out.println("Enter choice: ");
            choice = keyboard.nextInt();
        } while(choice < 0 || choice > 5);

        return choice;
    }

    //account choice
    public static int giveAccountMenuInput(Scanner keyboard) {
        System.out.println("Select Account Type: ");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");

        int choice;
        do {
            System.out.println("Enter choice: ");
            choice = keyboard.nextInt();
        } while(choice<1 || choice > 2);

        return choice;
    }

}
