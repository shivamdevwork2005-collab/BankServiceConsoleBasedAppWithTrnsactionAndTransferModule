package ProjectCopyOfBankServiceWithFinalTransactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Atm implements Bank {

    Customer c ;       // Customer object

    Atm(Customer c){
        this.c = c;
    }

    BankApplication bapp = new BankApplication();


    static int pin; // No final

     {
        System.out.println("\n========================================");
        System.out.println("          🏦 ATM MACHINE");
        System.out.println("========================================");
        System.out.println("\nPlease Set Your 4 digit ATM PIN: ");

        Scanner sc = new Scanner(System.in);
        String temP = sc.next();

        if(temP.length() == 4 && charChek(temP)){
            pin = Integer.parseInt(temP);
            System.out.println("\n----------------------------------------");
            System.out.println("       PIN SET SUCCESSFULLY!");
            System.out.println("----------------------------------------\n");
        }else{
            System.out.println("\n----------------------------------------");
            System.out.println("       ❌ PIN SET FAILED!");
            System.out.println("----------------------------------------\n");

            Atm a = new Atm(c); // means again non_static block runs for the same customer;
        }


    }

    // *********************pin Validation***************************
    // Character.isDigit(ch);
    // Character.isLetter(ch);
    // Character.isNumber(ch);
    // Character.isAlphabetic(ch);
    // Character.isUpperCase(ch);
    // Character.isLowerCase(ch);
    // Character.isWhitespace(ch);
    // Character.isLetterOrDigit(ch);

    boolean charChek(String str){
         for(char ch:str.toCharArray()){
             if(!Character.isDigit(ch)) return false;
         }
         return true;
    }


    int yourPin() {
        System.out.println("\n========================================");
        System.out.println("           ATM LOGIN");
        System.out.println("========================================");

        System.out.print("\nPlease Enter Your PIN: ");

        int yourPin = new Scanner(System.in).nextInt();

        return yourPin;
    }

    public void checkPin() {

        int yourPin = yourPin();

        if (pin == yourPin) {

            System.out.println("\n----------------------------------------");
            System.out.println("          ✔️ PIN MATCHED");
            System.out.println("----------------------------------------");

            menu();

        } else {

            System.out.println("\n----------------------------------------");
            System.out.println("        ❌ INCORRECT PIN");
            System.out.println("----------------------------------------");
            System.out.println("Please Enter the Correct PIN.\n");

            checkPin();
        }
    }

    public void menu() {

        System.out.println("\n========================================");
        System.out.println("             ATM MENU"                     );
        System.out.println("========================================");

        System.out.println("\n" +
                            "1. Check Balance 💵");
        System.out.println("2. Withdraw 💵");
        System.out.println("3. Deposit 💵");
        System.out.println("4. Change Password");
        System.out.println("5. Show Transactions");
        System.out.println("6. Visit Bank Service");
        System.out.println("7. Exit");

        System.out.println("\n----------------------------------------");
        System.out.print("Enter Your Choice: ");

        int input = new Scanner(System.in).nextInt();

        switch (input) {

            case 1:
                checkBalance();
                break;

            case 2:
                System.out.print("\nEnter 💵 Amount to Withdraw: ");

                int amount = new Scanner(System.in).nextInt();

                withdraw(amount);
                break;

            case 3:
                System.out.print("\nEnter 💵 Amount to Deposit: ");

                int depositAmount = new Scanner(System.in).nextInt();

                deposite(depositAmount);
                break;

            case 4:
                System.out.println("\n Please You Can Change Your Pin: ");
                changePass();
                break;

            case 5:
                System.out.print("\n================= TRANSACTION HISTORY =================\n");
                System.out.print("Enter Your Account No: ");
                String accountNo4 = new Scanner(System.in).next();
                showTransactions(accountNo4);
                menu();
                break;

            case 6:
                String accountNo = c.getAccountNo();
                if(accountNo.contains("BOB")){
                    BankService b = new BankServiceImplOfBob();   // Bug
                    b.BankMenu();
                    bapp.start();
                    break;
                }else if(accountNo.contains("SBI")){
                    BankService b = new BankServiceImplOfSbi();
                    b.BankMenu();
                    bapp.start();
                    break;
                }else if(accountNo.contains("PNB")){
                    BankService b = new BankServiceImplOfPunjabNationalBank();
                    b.BankMenu();
                    bapp.start();
                    break;
                }else{
                    System.out.println("\n----------------------------------------");
                    System.out.println("       ❌ INVALID CHOICE");
                    System.out.println("----------------------------------------");
                    System.out.println("Please Enter a Valid Choice.\n");
                    bapp.start();
                    break;
                }

            case 7:
                exit();
                break;

            default:
                System.out.println("\n----------------------------------------");
                System.out.println("       ❌ INVALID CHOICE");
                System.out.println("----------------------------------------");
                System.out.println("Please Enter a Valid Choice.\n");

                menu();
                break;
        }
    }


    private void exit() {
        System.out.println("\n========================================");
        System.out.println("       THANK YOU FOR USING OUR ATM");
        System.out.println("========================================");
        System.out.println("\nHave a Nice Day! 😊\n");

        System.exit(0); // i Adarsh can use return keyword by declaring my this method as boolean and return true;
    }

    @Override
    public void deposite(int amount) {
        float balance = c.getBalance();
        balance+=amount;
        c.setBalance(balance);

        Transactions t = new Transactions();
        t.transactionId = t.hashCode()+""+c.getAccountNo().substring(0,4);
        t.amount = amount;
        t.transactionType = "Atm Deposit Transaction";
        t.transactionDate = new Date().toString();
        t.name = c.getName();
        t.accountNo = c.getAccountNo();


        if(c.getBankName()=="BOB"){
            if (!BankServiceImplOfBob.transactions.containsKey(c.getAccountNo())) {
                BankServiceImplOfBob.transactions.put(c.getAccountNo(), new ArrayList<>());
            }
            BankServiceImplOfBob.transactions.get(c.getAccountNo()).add(t);
            c.setTransactions(t);
        }else if(c.getBankName() == "SBI"){
            if (!BankServiceImplOfSbi.transactions.containsKey(c.getAccountNo())) {
                BankServiceImplOfSbi.transactions.put(c.getAccountNo(), new ArrayList<>());
            }
            BankServiceImplOfSbi.transactions.get(c.getAccountNo()).add(t);
            c.setTransactions(t);
        }else if(c.getBankName() == "PNB"){
            if (!BankServiceImplOfPunjabNationalBank.transactions.containsKey(c.getAccountNo())) {
                BankServiceImplOfPunjabNationalBank.transactions.put(c.getAccountNo(), new ArrayList<>());
            }
            BankServiceImplOfPunjabNationalBank.transactions.get(c.getAccountNo()).add(t);
            c.setTransactions(t);
        }else if(c.getBankName() == "HDFC"){
            if (!BankServiceImplOfHdfc.transactions.containsKey(c.getAccountNo())) {
                BankServiceImplOfHdfc.transactions.put(c.getAccountNo(), new ArrayList<>());
            }
            BankServiceImplOfHdfc.transactions.get(c.getAccountNo()).add(t);
            c.setTransactions(t);
        }


        System.out.println("\n----------------------------------------");
        System.out.println("         💵 DEPOSIT SUCCESSFUL");
        System.out.println("----------------------------------------");
        System.out.println("Deposited 💵 Amount : Rs" + amount);
        System.out.println("Current 💵 Balance  : Rs" + c.getBalance());
        System.out.println("----------------------------------------");

        menu();
    }

    @Override
    public void withdraw(int amount) {

        if (amount <= c.getBalance()) {

            float balance = c.getBalance();
            balance-=amount;
            c.setBalance(balance);

            Transactions t = new Transactions();
            t.transactionId = t.hashCode()+""+c.getAccountNo().substring(0,4);
            t.amount = amount;
            t.transactionType = "Atm Withdraw Transaction";
            t.transactionDate = new Date().toString();
            t.name = c.getName();
            t.accountNo = c.getAccountNo();


            if(c.getBankName()=="BOB"){
                if (!BankServiceImplOfBob.transactions.containsKey(c.getAccountNo())) {
                    BankServiceImplOfBob.transactions.put(c.getAccountNo(), new ArrayList<>());
                }
                BankServiceImplOfBob.transactions.get(c.getAccountNo()).add(t);
                c.setTransactions(t);
            }else if(c.getBankName() == "SBI"){
                if (!BankServiceImplOfSbi.transactions.containsKey(c.getAccountNo())) {
                    BankServiceImplOfSbi.transactions.put(c.getAccountNo(), new ArrayList<>());
                }
                BankServiceImplOfSbi.transactions.get(c.getAccountNo()).add(t);
                c.setTransactions(t);
            }else if(c.getBankName() == "PNB"){
                if (!BankServiceImplOfPunjabNationalBank.transactions.containsKey(c.getAccountNo())) {
                    BankServiceImplOfPunjabNationalBank.transactions.put(c.getAccountNo(), new ArrayList<>());
                }
                BankServiceImplOfPunjabNationalBank.transactions.get(c.getAccountNo()).add(t);
                c.setTransactions(t);
            }else if(c.getBankName() == "HDFC"){
                if (!BankServiceImplOfHdfc.transactions.containsKey(c.getAccountNo())) {
                    BankServiceImplOfHdfc.transactions.put(c.getAccountNo(), new ArrayList<>());
                }
                BankServiceImplOfHdfc.transactions.get(c.getAccountNo()).add(t);
                c.setTransactions(t);
            }


            System.out.println("\n----------------------------------------");
            System.out.println("         WITHDRAWAL SUCCESSFUL");
            System.out.println("----------------------------------------");
            System.out.println("Withdrawn 💵 Amount : Rs" + amount);
            System.out.println("Remaining 💵 Balance: Rs" + c.getBalance());
            System.out.println("----------------------------------------");

        } else {
            System.out.println("\n----------------------------------------");
            System.out.println("        ✗ INSUFFICIENT 💵 BALANCE");
            System.out.println("----------------------------------------");
            System.out.println("Please Enter a Valid Amount 💵.");
            System.out.println("----------------------------------------");
        }

        menu();
    }

    @Override
    public void checkBalance() {
        System.out.println("\n----------------------------------------");
        System.out.println("             ACCOUNT 💵 BALANCE");
        System.out.println("----------------------------------------");
        System.out.println("Current 💵 Balance: Rs" + c.getBalance());
        System.out.println("----------------------------------------");

        menu();
    }

    @Override
    public void changePass() {
        resetPin();
    }

    private void resetPin() {
        System.out.println("\n========================================");
        System.out.println("           ATM Pin Reset");
        System.out.println("========================================");

        System.out.print("\nPlease Enter Your New PIN: ");

        int yourPin = new Scanner(System.in).nextInt();
        pin = yourPin;

        System.out.println("\n----------------------------------------");
        System.out.println("          ✔️ PIN RESET");
        System.out.println("----------------------------------------");

        checkPin();
    }


    public void showTransactions(String accountNo){

        if (c == null) {
            System.out.println("Account not found.");
            return;
        }else{
            ArrayList<Transactions> res = c.getTransactions(accountNo);
            for(Transactions t : res){
                System.out.println(t);
                System.out.println("\n");
            }
        }

        System.out.println("\n\n");

    }

}



