package ProjectCopyOfBankServiceWithFinalTransactions;

import java.util.Scanner;

public class BankApplication {

    public void start() {
        System.out.println("\n\n************* Welcome to  🏦 our Bank  ***************\n\n");

        System.out.println("1: For Bank Of Baroda Services ");
        System.out.println("2: For State Bank Of India Services ");
        System.out.println("3: For HDFC Bank Services ");
        System.out.println("4: For Punjab National Bank Services ");
        System.out.println("5: For Exit ");

        System.out.print("\nEnter Your Bank Choice for Service: ");
        int choice = new Scanner(System.in).nextInt();

        switch (choice) {
            case 1:
                BankService b1 = new BankServiceImplOfBob();
                b1.visitBank();
                start();
                break;

            case 2:
                BankService b2 = new BankServiceImplOfSbi();
                b2.visitBank();
                start();
                break;
            case 3:
                BankService b3 = new BankServiceImplOfHdfc();
                b3.visitBank();
                start();
                break;
            case 4:
                BankService b4 = new BankServiceImplOfPunjabNationalBank();
                b4.visitBank();
                start();
                break;
            case 5:
                System.out.println("**************** Thank you for using our bank ***************");
                System.exit(0);
            default:
                System.out.println("Invalid choice");
                start();
                break;

        }
    }

    public static void main(String[] args) {
        BankApplication b = new BankApplication();
        b.start();

    }
}
