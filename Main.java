import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String name;
    private int mpin;

    public User(int acNum, int mpin, String name) {
        this.acNum = acNum;
        this.mpin = mpin;
        this.name = name;
    }

    private int acNum;
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMpin() {
        return mpin;
    }

    public void setMpin(int mpin) {
        this.mpin = mpin;
    }

    public int getAcNum() {
        return acNum;
    }

    public void setAcNum(int acNum) {
        this.acNum = acNum;
    }

    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mpin=" + mpin +
                ", acNum=" + acNum +
                '}';
    }
}

public class Main {
    static  Map<Integer,User> userData = new HashMap<>();
    static Map<Integer, Integer> userDataAcNdMpin = new HashMap<>();
    static Map<Integer, Integer> userDataAcNdBal = new HashMap<>();
    static Map<Integer, String> userDataAcNdName = new HashMap<>();

    static boolean isrunning = true;
    static int choice = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        homePage();

        while (isrunning) {
            System.out.print("Choose:- ");
            String input = sc.nextLine();
            if(isInteger(input)){
                choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> openAccount(sc);
                case 2 -> login(sc);
                case 9 -> isrunning = exit();
                default -> System.out.println("Wrong Input");
            }
            }else{
                System.out.println("Input a Integer ");
            }

        }
    }

    public static void homePage() {
        System.out.println("Welcome");
        System.out.println("Choose Option ");
        System.out.println("1. Open New Account");
        System.out.println("2. Login");
        System.out.println("9. Exit");
    }

    public static void openAccount(Scanner sc) {
        System.out.println("Fill your details:- ");
        System.out.println("Enter your name ");
        String name = sc.nextLine();

        while (!isString(name)){
            System.out.println("Enter Only String");
            System.out.println("Enter your name ");
             name = sc.nextLine();
        }



        System.out.println("Enter your Account number of 5 Digits only :-");
        String accountstr = sc.nextLine();

        while (!isInteger(accountstr)|| accountstr.length()!=5) {
            System.out.println("Enter only 5 digits for the Account number: ");
            accountstr = sc.nextLine();;
        }
        int ac= Integer.parseInt(accountstr);



        System.out.println("Enter your MPIN:-");
        String mpins = sc.nextLine();
        while (!isInteger(mpins) || mpins.length() != 4) {
            System.out.println("Invalid input. Please enter a 4-digit MPIN: ");
            mpins = sc.nextLine();
        }
        int mpin = Integer.parseInt(mpins);
        User us = new User(ac, mpin,name);

        System.out.println("Account Created Successfully");
        System.out.println(us.toString());

        // Save user details in the Maps
        userDataAcNdMpin.put(us.getAcNum(), us.getMpin());
        userDataAcNdBal.put(us.getAcNum(), us.getBalance());
        userDataAcNdName.put(us.getAcNum(), us.getName());

        homePage(); // Go back to the homepage
    }

    public static void login(Scanner sc) {
        System.out.println("To login ");
        System.out.print("Enter A/c Number: ");
        String accountstr = sc.nextLine();

        while (!isInteger(accountstr)|| accountstr.length()!=5) {
            System.out.println("Enter only 5 digits for the Account number: ");
            accountstr = sc.nextLine();;
        }
        int ac= Integer.parseInt(accountstr);


        System.out.print("Enter Mpin: ");
        String mpins = sc.nextLine();
        while(!isInteger(mpins)){
            System.out.println("Enter only in integer");
            System.out.println("Enter Mpin");
            mpins = sc.nextLine();
        }
        int mpin = Integer.parseInt(mpins);


        if (userDataAcNdMpin.containsKey(ac) && userDataAcNdMpin.get(ac) == mpin) {
            System.out.println("Logging in... Please wait...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Error while logging in.");
            }
            System.out.println("Login Successful! Welcome " + userDataAcNdName.get(ac));

            // After login, show more options
            afterLoginMenu(sc, ac);
        } else {
            System.out.println("Invalid credentials. Please try again.");
            homePage();
        }
    }

    public static void afterLoginMenu(Scanner sc, int ac) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1 -> checkBalance(ac);
                case 2 -> depositMoney(sc, ac);
                case 3 -> withdrawMoney(sc, ac);
                case 4 -> {
                    System.out.println("Logging out...");
                    loggedIn = false;
                    homePage();
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void checkBalance(int ac) {
        System.out.println("Your current balance is: " + userDataAcNdBal.get(ac));
    }

    public static void depositMoney(Scanner sc, int ac) {
        System.out.print("Enter amount to deposit: ");
        int depositAmount = sc.nextInt();
        int currentBalance = userDataAcNdBal.get(ac);
        userDataAcNdBal.put(ac, currentBalance + depositAmount);
        System.out.println("Amount deposited successfully. Your new balance is: " + userDataAcNdBal.get(ac));
    }

    public static void withdrawMoney(Scanner sc, int ac) {
        System.out.print("Enter amount to withdraw: ");
        int withdrawAmount = sc.nextInt();
        int currentBalance = userDataAcNdBal.get(ac);
        if (withdrawAmount > currentBalance) {
            System.out.println("Insufficient balance.");
        } else {
            userDataAcNdBal.put(ac, currentBalance - withdrawAmount);
            System.out.println("Amount withdrawn successfully. Your new balance is: " + userDataAcNdBal.get(ac));
        }
    }

    public static boolean exit() {
        System.out.println("Thank you for choosing us...");
        isrunning = false;
        return false;
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);  // Attempt to parse the string to an integer
            return true;  // If successful, it's a valid integer
        } catch (NumberFormatException e) {
            return false;  // If parsing fails, return false
        }
    }
    public static boolean isString(String str) {
        return str.matches("[a-zA-Z ]+");  // Allows alphabetic characters (uppercase/lowercase) and spaces
    }

}