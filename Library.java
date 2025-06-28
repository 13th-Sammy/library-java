import java.util.HashMap;

class Library {
    private final HashMap<Long, Book> BookList=new HashMap<>();
    private final HashMap<Long, User> UserList=new HashMap<>();
    private final Logger logger=Logger.getInstance();

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
                logger.log("Add Book - success");
                return ("Title and Author already exist, added to existing Book ID - " + b.getID());
            }
            else if(b.getTitle().equalsIgnoreCase(t) && b.getAuthor().equalsIgnoreCase(a) && b.getID()==ID) {
                b.addTotalCopies(1);
                b.addAvailCopies(1);
                logger.log("Add Book - success");
                return ("Added to existing Book ID - " + b.getID());
            }
            else if(b.getID()==ID && (!b.getTitle().equalsIgnoreCase(t) || !b.getAuthor().equalsIgnoreCase(a))) {
                logger.log("Add Book - failure");
                return ("Different Books cannot have same Book ID, not added.");
            }
        }

        Book book=new Book(ID, t, a);
        book.addAvailCopies(1);
        book.addTotalCopies(1);
        BookList.put(book.getID(), book);
        logger.log("Add Book - success");
        return ("Book Added successfully, Book ID - " + book.getID());
    }

    public String removeBook(long ID) {
        if(BookList.containsKey(ID)) {
            BookList.remove(ID);
            logger.log("Remove Book - success");
            return ("Book with ID " + ID + " removed from System permanently");
        }
        else {
            logger.log("Remove Book - failure");
            return ("No such Book ID exists.");
        }
    }

    public String registerUser(long uid, String name) {
        if(UserList.containsKey(uid)) {
            logger.log("Register User - failure");
            return ("Uid already exists, user not added");
        }

        User user=new User(uid, name);
        UserList.put(user.getUid(), user);
        logger.log("Register User - success");
        return ("User added successfully, Uid - " + user.getUid());
    }

    public String removeUser(long uid) {
        if(UserList.containsKey(uid)) {
            if(UserList.get(uid).getBorrowedMap().isEmpty()) {
                UserList.remove(uid);
                logger.log("Remove User - success");
                return ("User with ID " + uid + " removed from System permanently");
            }
            else {
                logger.log("Remove User - failure");
                return ("User has not returned all books, cannot remove");
            }
        }
        else {
            logger.log("Remove User - failure");
            return ("No such user exists");
        }
    }

    public String issueBook(long uid, long bid, int noOfCopies) {
        if(!UserList.containsKey(uid)) {
            logger.log("Issue Book - failure");
            return ("Uid does not exist");
        }
        if(!BookList.containsKey(bid)) {
            logger.log("Issue Book - failure");
            return ("Book ID does not exist");
        }
        if(BookList.get(bid).getAvailCopies()-noOfCopies<0) {
            logger.log("Issue Book - failure");
            return ("Not enough available copies");
        }
        BookList.get(bid).remAvailCopies(noOfCopies);
        UserList.get(uid).borrowBook(bid, noOfCopies);
        logger.log("Issue Book - success");
        return ("Book successfully borrowed");
    }

    public String returnBookL(long uid, long bid, int noOfCopies) {
        if(!UserList.containsKey(uid)) {
            logger.log("Return Book - failure");
            return ("Uid does not exist");
        }
        if(!BookList.containsKey(bid)) {
            logger.log("Return Book - failure");
            return ("Book ID does not exist");
        }
        int res=UserList.get(uid).returnBook(bid, noOfCopies);
        switch (res) {
            case 1 -> {
                BookList.get(bid).addAvailCopies(noOfCopies);
                logger.log("Return Book - success");
                return ("Book sucessfully returned");
            }
            case 0 -> {
                logger.log("Return Book - failure");
                return ("User does not have so many copies, not returned");
            }
            default -> {
                logger.log("Return Book - failure");
                return ("User has not borrowed this book with id - " + bid);
            }
        }
    }
}