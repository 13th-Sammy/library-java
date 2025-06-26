import java.util.HashMap;
import java.util.Scanner;

public class Library {
    private final Scanner scan=new Scanner(System.in);
    private final HashMap<Long, Book> BookList=new HashMap<>();

    public static void main(String args[]) {
        Library lib=new Library();
        OUTER:
        while (true) {
            lib.printMenu();
            int choice=lib.scan.nextInt();
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

    private void viewBooks() {
        for(Book b:BookList.values()) {
            System.out.println("ID - " + b.getID());
            System.out.println("Title - " + b.getTitle());
            System.out.println("Author - " + b.getAuthor());
            System.out.println("Total Copies - " + b.getTotalCopies());
            System.out.println("Available Copies - " + b.getAvailCopies());
            System.out.println();
        }
    }

    private void addBook() {
        System.out.println("Enter Book ID -");
        long ID=scan.nextLong();
        scan.nextLine();
        System.out.println("Enter Book Name -");
        String t=scan.nextLine().trim();
        System.out.println("Enter Author Name");
        String a=scan.nextLine().trim();

        for(Book b:BookList.values()) {
            if(b.getTitle().equalsIgnoreCase(t) && b.getAuthor().equalsIgnoreCase(a) && b.getID()!=ID) {
                b.addTotalCopies(1);
                b.addAvailCopies(1);
                System.out.println("Title and Author already exist, added to existing Book ID - " + b.getID());
                System.out.println();
                return;
            }
            else if(b.getTitle().equalsIgnoreCase(t) && b.getAuthor().equalsIgnoreCase(a) && b.getID()==ID) {
                b.addTotalCopies(1);
                b.addAvailCopies(1);
                System.out.println("Added to existing Book ID - " + b.getID());
                System.out.println();
                return;
            }
            else if(b.getID()==ID && (!b.getTitle().equalsIgnoreCase(t) || !b.getAuthor().equalsIgnoreCase(a))) {
                System.out.println("Different Books cannot have same Book ID, not added.");
                System.out.println();
                return;
            }
        }

        Book book=new Book(ID, t, a);
        book.addAvailCopies(1);
        book.addTotalCopies(1);
        BookList.put(book.getID(), book);
        System.out.println("Book Added successfully, Book ID - " + book.getID());
        System.out.println();
    }

    private void removeBook() {
        System.out.println("Enter Book ID to remove from System - ");
        long ID=scan.nextLong();
        if(BookList.containsKey(ID)) {
            BookList.remove(ID);
            System.out.println("Book with ID " + ID + " removed from System permanently");
            System.out.println();
        }
        else {
            System.out.println("No such Book ID exists.");
            System.out.println();
        }
    }

    private void searchBook() {
        System.out.println("Enter Title to Search - ");
        scan.nextLine();
        String title=scan.nextLine();
        System.out.println();
        boolean found=false;
        for(Book b:BookList.values()) {
            if(title.equalsIgnoreCase(b.getTitle())) {
                found=true;
                System.out.println("ID - " + b.getID());
                System.out.println("Title - " + b.getTitle());
                System.out.println("Author - " + b.getAuthor());
                System.out.println("Total Copies - " + b.getTotalCopies());
                System.out.println("Available Copies - " + b.getAvailCopies());
                System.out.println();
            }
        }
        if(found==false) {
            System.out.println("No such Book exists");
            System.out.println();
        }
    }
}