import java.util.Scanner;

public  class Main {
    public static void homePage(){
        System.out.println("Welcome");
        System.out.println("Choose Option ");
        System.out.println("1. Open New Account");
        System.out.println("2. Login");
        System.out.println("9. Exit");

    }
    public static void openAccount(){

    }  public static void login(){

    }
     public static boolean isRunning(){
         System.out.println("Thank you for banking with us ");
        return false;
     }
    public static void main(String[] args) {

        boolean isrunning = true ;
        int choice =0;

        homePage();

        while(isrunning){
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose:- ");
            choice = sc.nextInt();
            switch (choice){
                case 1  -> openAccount();
                case 2 -> login();
                case 9 -> isrunning = isRunning();
                default -> System.out.println("Wrong Input");
            }
        }
    }
}