package BankApp;

public  class Account {

    private String accNo;
    private String accName;
    private double balance;
    private int pin;

    Account(String accNo, String accName) {
        this.accNo = accNo;
        this.accName = accName;
        this.balance = 0;
        this.pin = 0000;
    }
    //Getters and Setters
    //To get and set the values of Account number and Owner


    //Account Number
    String getAccNo() {
        return this.accNo;
    }
//    void setAccNo(String accNo) {
//         this.accNo = accNo;
//    }

    //Account Owner
    String getAccName() {
        return this.accName;
    }
    void setAccName(String accName) {
        this.accName = accName;
    }

    //Account Balance
    void setBalance(double balance) {
        this.balance = balance;
    }
    double getBalance() {
        return this.balance;
    }

    //Account Pin
    void setPin(int pin) {
        this.pin = pin;
    }
    int getPin() {
        return this.pin;
    }

    //Methods for the accounts
    void Menu() {
        System.out.println("Welcome " + getAccName().toUpperCase());
        System.out.println("1. Display Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Logout");
    }
    void displayBalance() {
        System.out.println('$' + Double.toString(getBalance()));
    }
    void deposit(double amount) throws InvalidAmountException {
        if(amount<=0) {
            throw new InvalidAmountException("Amount must be greater than 0.");
        } else {
            setBalance(balance + amount);
        }


    }
    void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if(amount>balance){
            throw new InsufficientFundsException("Insufficient funds your balance is " + balance);
        } else if(amount<=0){
            throw new InvalidAmountException("Amount must be greater than 0.");
        }
        else {
            setBalance(balance - amount);
        }

    }

    @Override
    public String toString() {
        return "Account Number: " + accNo  + " | Owner: " + accName + " | Balance: $" + balance;

    }

}
