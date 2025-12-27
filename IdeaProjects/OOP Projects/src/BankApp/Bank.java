package BankApp;

import java.io.*;

import java.util.ArrayList;
import java.util.Random;


public class Bank {
    Random random = new Random();
    ArrayList<Account> accounts;
    final private String name;
    Bank (String name, ArrayList<Account> accounts) {
        this.accounts = accounts;
        this.name = name;
    }
    String getName() {
       return this.name;
    }

    void bankMenu() {
        System.out.println("Welcome to " + this.name + " Bank");
        System.out.println("1. Create account");
        System.out.println("2. Login");
        System.out.println("3. View All Accounts");
        System.out.println("4. Search");
        System.out.println("5. Exit");
    }
    void createAccount(String name, int pin) {
        Account newAccount;
        String accNo = "";
        for(int i=0; i<10;i++) {
            int number = random.nextInt(0,9);
            accNo+=number;
        }
        String pinS = Integer.toString(pin);

        if(pinS.length() == 6) {
            newAccount = new Account(accNo, name);
            newAccount.setPin(pin);
            accounts.add(newAccount);
            System.out.println("Your account number is: " + accNo);
        } else {
            System.out.println("Pin must be 6 digits");
        }


    }
    boolean loginAccount(String accNo, int pin) throws InvalidAccountNoException{
        boolean found = false;
        boolean pinCorrect = false;
        for(Account account: accounts) {
            if (account.getAccNo().equals(accNo) && account.getPin() == pin) {
                found = true;
                pinCorrect = true;
            }
            if (found && pinCorrect) {
                account.Menu();
                return found;
            }
        }
        if(found && pinCorrect) {
            return found;
        } else {
            return false;
        }
    }
    void viewAccounts() {
        for(Account account: accounts){
            System.out.println(account.toString());
        }
    }
    void bankReader() {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\BankApp\\Bank Accounts.txt";
        String pinPath = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\BankApp\\Account Pin.txt";
        String line;
        String pinLine;
        try(BufferedReader bankreader = new BufferedReader(new FileReader(path)); BufferedReader pinReader = new BufferedReader(new FileReader(pinPath))){
            String accNo, Ownwer, accOwnwer;
            int pin;
            double balance;
            while((line = bankreader.readLine()) != null && (pinLine = pinReader.readLine()) != null) {
                accNo = line.substring(line.indexOf(":")+1, line.indexOf("|")).trim();
                Ownwer = line.substring(line.indexOf(":") +1, line.lastIndexOf("|")).trim();
                accOwnwer = Ownwer.substring(Ownwer.indexOf(":")+1).trim();
                balance = Double.parseDouble(line.substring(line.indexOf("$")+1).trim());

                pin = Integer.parseInt(pinLine.substring(pinLine.indexOf(":")+1).trim());
                Account account = new Account(accNo, accOwnwer);
                account.setBalance(balance);
                account.setPin(pin);
                accounts.add(account);
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    void bankWriter() {
        String pinPath = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\BankApp\\Account Pin.txt";
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\BankApp\\Bank Accounts.txt";
        try(FileWriter bankwrite = new FileWriter(path); FileWriter pinwriter = new FileWriter(pinPath)){
            for (Account account: accounts) {
                bankwrite.write(account.toString());
                bankwrite.write("\n");
                pinwriter.write(account.getAccNo() + ":" + Integer.toString(account.getPin()));
                pinwriter.write("\n");

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    void transfer(double amount, String accNo, int accPin, String accNoToTransfer) throws InvalidAmountException, IncorrectAccountPinException, InvalidAccountNoException {
        Account sender = null;
        Account receiver = null;
        boolean found = false;
        for(Account account: accounts) {
            if(accNo.equals(account.getAccNo())) {
                sender = account;

            } else if (accNoToTransfer.equals(account.getAccNo())) {
                receiver = account;
            }
            if(sender != null && receiver!=null) {
                found = true;
                break;
            }
        }
        if (found) {
            if (accPin == sender.getPin()) {
                try {
                    sender.withdraw(amount);
                    receiver.deposit(amount);
                    System.out.println("Transfer successful");
                } catch (InsufficientFundsException | InvalidAmountException e) {
                    System.out.println(e);
                }
            } else {
                throw new IncorrectAccountPinException ("Incorrect pin");
            }
        } else {
            throw new InvalidAccountNoException("Account not found");
        }

    }
    void search(String accNo) throws InvalidAccountNoException {
        for(Account account: accounts) {
            if(accNo.equals(account.getAccNo())) {
                System.out.println("Account found");
                System.out.println(account.toString());
                break;
            }
            if (!account.getAccNo().equals(accNo)) {
                throw new InvalidAccountNoException("Account not found");
            }
        }
    }
}
