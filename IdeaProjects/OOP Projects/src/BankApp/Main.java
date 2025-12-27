package BankApp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        Bank bank = new Bank("Mantorc" ,accounts);

        bank.bankReader();


        int bankChoice = 0;
        while(bankChoice != 5) {
            try {
                bank.bankMenu();
                System.out.print("Pick a choice (1-5): ");
                bankChoice = scanner.nextInt();
                scanner.nextLine();
                switch(bankChoice) {
                    case 1 -> {
                        System.out.print("Enter Last name: ");
                        String lname = scanner.nextLine().trim();
                        System.out.print("Enter First name: ");
                        String fname = scanner.nextLine().trim();
                        String name = lname + " " + fname;
                        System.out.print("Enter a (6-digits) Login Pin: ");
                        int pin = scanner.nextInt();
                        bank.createAccount(name, pin);
                    }
                    case 2 -> {
                        System.out.print("Enter your account number: ");
                        String accNo = scanner.nextLine().trim();
                        System.out.print("Enter your login pin: ");
                        int pin = scanner.nextInt();

                        if(bank.loginAccount(accNo, pin)){
                            int accountChoice = 0;
                            while(accountChoice!=5){
                                System.out.print("Enter a choice: ");
                                accountChoice = scanner.nextInt();
                                scanner.nextLine();
                                switch(accountChoice) {
                                    case 1->{
                                        System.out.println("-----------");
                                        for (Account account: accounts){
                                            if(accNo.equals(account.getAccNo())){
                                                account.displayBalance();
                                                break;
                                            }
                                        }
                                        System.out.println("-----------");
                                    }
                                    case 2 -> {
                                        System.out.print("Enter amount you want to deposit: ");
                                        double amount = scanner.nextDouble();
                                        for (Account account: accounts){
                                            if(accNo.equals(account.getAccNo())){
                                                try {
                                                    account.deposit(amount);
                                                    System.out.println("Amount successfully deposited");
                                                } catch (InvalidAmountException e) {
                                                    System.out.println(e);
                                                }

                                                break;
                                            }
                                        }
                                    }
                                    case 3 -> {
                                        System.out.print("Enter amount you want to withdraw: ");
                                        double amount = scanner.nextInt();
                                        for (Account account: accounts){
                                            if(accNo.equals(account.getAccNo())){
                                                try {
                                                    account.withdraw(amount);
                                                    System.out.println("Amount successfully withdrawn");
                                                } catch (InsufficientFundsException | InvalidAmountException e) {
                                                    System.out.println(e);
                                                }

                                                break;
                                            }
                                        }
                                    }
                                    case 4 -> {
                                        System.out.print("Enter account number you want to transfer to: ");
                                        String accNoTransfer = scanner.nextLine().trim();
                                        System.out.print("Enter amount to transfer: ");
                                        double amount = scanner.nextDouble();
                                        System.out.print("Enter transfer pin: ");
                                        int accPin = scanner.nextInt();
                                        try {
                                            bank.transfer(amount, accNo, accPin, accNoTransfer);
                                        } catch (InvalidAmountException | InvalidAccountNoException | IncorrectAccountPinException e) {
                                            System.out.println(e);
                                        }
                                    }
                                    case 5-> {
                                        System.out.println("Successfully logged out");
                                    }
                                    default -> {
                                        System.out.println("Enter correct choice");
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("---------------------");
                            System.out.println("Invalid Credentials");
                            System.out.println("---------------------");
                        };

                    }
                    case 3 -> bank.viewAccounts();
                    case 4 -> {
                        System.out.print("Enter account number: ");
                        String accNo = scanner.nextLine().trim();
                        try {
                            bank.search(accNo);
                        } catch (InvalidAccountNoException e) {
                            System.out.println(e);
                        }
                    }
                    case 5 -> {
                        System.out.println("Have a nice day, you have exited the " + bank.getName() +  " app.");
                        bank.bankWriter();
                    }
                    default -> System.out.println("Enter a valid choice");
                }
            }
            catch (InputMismatchException e){
                System.out.println(e);
                System.out.println("Enter valid input ");
                scanner.nextLine();
            }

        }
        scanner.close();
    }
}
