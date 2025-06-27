import java.util.HashMap;
import java.util.Scanner;

class Library {
    private final Scanner scan=new Scanner(System.in);
    private final HashMap<Long, Book> BookList=new HashMap<>();
    private final HashMap<Long, User> UserList=new HashMap<>();

    public void viewBooks() {
        for(Book b:BookList.values()) {
            System.out.println("ID - " + b.getID());
            System.out.println("Title - " + b.getTitle());
            System.out.println("Author - " + b.getAuthor());
            System.out.println("Total Copies - " + b.getTotalCopies());
            System.out.println("Available Copies - " + b.getAvailCopies());
            System.out.println();
        }
    }

    public void addBook() {
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

    public void removeBook() {
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

    public void searchBook() {
        System.out.println("Enter Title to Search - ");
        scan.nextLine();
        String title=scan.nextLine().trim();
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

    public void registerUser() {
        System.out.println("Enter User ID");
        long uid=scan.nextLong();
        if(UserList.containsKey(uid)) {
            System.out.println("Uid already exists, user not added");
            System.out.println();
            return;
        }
        System.out.println("Enter User Name");
        scan.nextLine();
        String name=scan.nextLine().trim();

        User user=new User(uid, name);
        UserList.put(user.getUid(), user);
        System.out.println("User added successfully, Uid - " + user.getUid());
        System.out.println();
    }

    public void viewUser() {
        System.out.println("Enter Uid");
        long uid=scan.nextLong();
        if(UserList.containsKey(uid)) {
            System.out.println();
            System.out.println("Name - " + UserList.get(uid).getName());
            System.out.println();
            UserList.get(uid).viewBorrowed();
        }
        else {
            System.out.println("Uid does not exist");
            System.out.println();
        }
    }

    public void viewAllUsers() {
        for(User u:UserList.values()) {
            System.out.println("Uid - " + u.getUid() + ", Name - " + u.getName());
            System.out.println();
            u.viewBorrowed();
        }
    }

    public void issueBook() {
        System.out.println("Enter uid");
        long uid=scan.nextLong();
        if(!UserList.containsKey(uid)) {
            System.out.println("Uid does not exist");
            System.out.println();
            return;
        }
        System.out.println("Enter Book ID to borrow"); 
        long bid=scan.nextLong();
        if(!BookList.containsKey(bid)) {
            System.out.println("Book ID does not exist");
            System.out.println();
            return;
        }
        System.out.println("Enter number of copies to borrow");
        int noOfCopies=scan.nextInt();
        if(BookList.get(bid).getAvailCopies()-noOfCopies<0) {
            System.out.println("Not enough available copies");
            System.out.println();
            return;
        }
        BookList.get(bid).remAvailCopies(noOfCopies);
        UserList.get(uid).borrowBook(bid, noOfCopies);
        System.out.println("Book successfully borrowed");
        System.out.println();
    }

    public void returnBookL() {
        System.out.println("Enter uid");
        long uid=scan.nextLong();
        if(!UserList.containsKey(uid)) {
            System.out.println("Uid does not exist");
            System.out.println();
            return;
        }
        System.out.println("Enter book ID to return");
        long bid=scan.nextLong();
        if(!BookList.containsKey(bid)) {
            System.out.println("Book ID does not exist");
            System.out.println();
            return;
        }
        System.out.println("Enter number of copies to return");
        int noOfCopies=scan.nextInt();
        int res=UserList.get(uid).returnBook(bid, noOfCopies);
        if(res==1) {
            BookList.get(bid).addAvailCopies(noOfCopies);
            System.out.println("Book sucessfully returned");
            System.out.println();
        }
    }
}