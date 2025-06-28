import java.util.HashMap;

class Library {
    private final HashMap<Long, Book> BookList=new HashMap<>();
    private final HashMap<Long, User> UserList=new HashMap<>();

    public HashMap<Long, Book> getBookList() {
        return BookList;
    }

    public HashMap<Long, User> getUserList() {
        return UserList;
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

    public String issueBook(long uid, long bid, int noOfCopies) {
        if(!UserList.containsKey(uid)) {
            return ("Uid does not exist");
        }
        if(!BookList.containsKey(bid)) {
            return ("Book ID does not exist");
        }
        if(BookList.get(bid).getAvailCopies()-noOfCopies<0) {
            return ("Not enough available copies");
        }
        BookList.get(bid).remAvailCopies(noOfCopies);
        UserList.get(uid).borrowBook(bid, noOfCopies);
        return ("Book successfully borrowed");
    }

    public String returnBookL(long uid, long bid, int noOfCopies) {
        if(!UserList.containsKey(uid)) {
            return ("Uid does not exist");
        }
        if(!BookList.containsKey(bid)) {
            return ("Book ID does not exist");
        }
        int res=UserList.get(uid).returnBook(bid, noOfCopies);
        switch (res) {
            case 1 -> {
                BookList.get(bid).addAvailCopies(noOfCopies);
                return ("Book sucessfully returned");
            }
            case 0 -> {
                return ("User does not have so many copies, not returned");
            }
            default -> {
                return ("User has not borrowed this book with id - " + bid);
            }
        }
    }
}