import java.util.Scanner;

public class Main {
    private final Scanner scan=new Scanner(System.in);
    private final Library lib=new Library();

    public static void main(String args[]) {
        Main program=new Main();
        program.run();
    }

    private void run() {
        OUTER:
        while (true) {
            printMenu();
            int choice=scan.nextInt();
            switch (choice) {
                case 0 -> {
                    break OUTER;
                }
                case 1 -> {
                    lib.viewBooks();
                }
                case 2 -> {
                    lib.addBook();
                }
                case 3 -> {
                    lib.removeBook();
                }
                case 4 -> {
                    lib.searchBook();
                }
                case 5 -> {
                    lib.registerUser();
                }
                case 6 -> {
                    lib.viewUser();
                }
                case 7 -> {
                    lib.viewAllUsers();
                }
                case 8 -> {
                    lib.issueBook();
                }
                case 9 -> {
                    lib.returnBookL();
                }
                default -> {
                    System.out.println("Invalid Choice");
                    System.out.println();
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("Welcome to the Library Management System !");
        System.out.println("Press 0 - Close");
        System.out.println("Press 1 - View Books");
        System.out.println("Press 2 - Add Book");
        System.out.println("Press 3 - Remove Book");
        System.out.println("Press 4 - Search Book");
        System.out.println("Press 5 - Register User");
        System.out.println("Press 6 - View User");
        System.out.println("Press 7 - View All Users");
        System.out.println("Press 8 - Issue Book");
        System.out.println("Press 9 - Return Book");
        System.out.println();
    }
}