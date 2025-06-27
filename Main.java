import java.util.Scanner;

public class Main {
    private final Scanner scan=new Scanner(System.in);
    private final Library lib=new Library();

    public static void main(String args[]) {
        Main main=new Main();
        OUTER:
        while (true) {
            main.printMenu();
            int choice=main.scan.nextInt();
            switch (choice) {
                case 0 -> {
                    break OUTER;
                }
                case 1 -> {
                    main.lib.viewBooks();
                }
                case 2 -> {
                    main.lib.addBook();
                }
                case 3 -> {
                    main.lib.removeBook();
                }
                case 4 -> {
                    main.lib.searchBook();
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
        System.out.println();
    }
}