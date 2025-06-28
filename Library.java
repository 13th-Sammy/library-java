import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

class Library {
    private final Scanner scan=new Scanner(System.in);
    private final HashMap<Long, Book> BookList=new HashMap<>();
    private final HashMap<Long, User> UserList=new HashMap<>();

    public Collection<Book> getBookList() {
        return BookList.values();
    }

    public Collection<User> getUserList() {
        return UserList.values();
    }

    public String addBook(long ID, String t, String a) {
        for(Book b:BookList.values()) {
            if(b.getTitle().equalsIgnoreCase(t) && b.getAuthor().equalsIgnoreCase(a) && b.getID()!=ID) {
                b.addTotalCopies(1);
                b.addAvailCopies(1);
                return ("Title and Author already exist, added to existing Book ID - " + b.getID());
            }
            else if(b.getTitle().equalsIgnoreCase(t) && b.getAuthor().equalsIgnoreCase(a) && b.getID()==ID) {
                b.addTotalCopies(1);
                b.addAvailCopies(1);
                return ("Added to existing Book ID - " + b.getID());
            }
            else if(b.getID()==ID && (!b.getTitle().equalsIgnoreCase(t) || !b.getAuthor().equalsIgnoreCase(a))) {
                return ("Different Books cannot have same Book ID, not added.");
            }
        }

        Book book=new Book(ID, t, a);
        book.addAvailCopies(1);
        book.addTotalCopies(1);
        BookList.put(book.getID(), book);
        return ("Book Added successfully, Book ID - " + book.getID());
    }

    public String removeBook(long ID) {
        if(BookList.containsKey(ID)) {
            BookList.remove(ID);
            return ("Book with ID " + ID + " removed from System permanently");
        }
        else {
            return ("No such Book ID exists.");
        }
    }

    public String registerUser(long uid, String name) {
        if(UserList.containsKey(uid)) {
            return ("Uid already exists, user not added");
        }

        User user=new User(uid, name);
        UserList.put(user.getUid(), user);
        return ("User added successfully, Uid - " + user.getUid());
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