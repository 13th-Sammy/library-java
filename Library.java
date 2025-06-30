import java.sql.*;
import java.util.HashMap;

class Library {
    private final HashMap<Long, Book> BookList=new HashMap<>();
    private final HashMap<Long, User> UserList=new HashMap<>();
    private final Logger logger=Logger.getInstance();

    public HashMap<Long, Book> getBookList() {
        return new HashMap<>(BookList);
    }

    public HashMap<Long, User> getUserList() {
        return new HashMap<>(UserList);
    }

    public String addBook(long id, String title, String author, int copies) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String checkTitleAuthor="SELECT * FROM BookList WHERE title=? and author=?";
            try(PreparedStatement ps=conn.prepareStatement(checkTitleAuthor)) {
                ps.setString(1, title);
                ps.setString(2, author);
                ResultSet rs=ps.executeQuery();
                if(rs.next()) {
                    long existingId=rs.getLong("id");
                    String updateQuery="UPDATE BookList SET total_copies=total_copies+?, available_copies=available_copies+? WHERE id=?";
                    try(PreparedStatement updateStmt=conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, copies);
                        updateStmt.setInt(2, copies);
                        updateStmt.setLong(3, existingId);
                        updateStmt.executeUpdate();
                    }
                    logger.log("Add Book - success");
                    return ("Same title and author, added to existing book ID");
                }
            }
            String checkId="SELECT * FROM BookList WHERE id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkId)) {
                ps.setLong(1, id);
                ResultSet rs=ps.executeQuery();
                if(rs.next()) {
                    String existingTitle=rs.getString("title");
                    String existingAuthor=rs.getString("author");
                    if(!existingTitle.equalsIgnoreCase(title) || !existingAuthor.equalsIgnoreCase(author)) {
                        logger.log("Add Book - failure");
                        return ("Different books cannot have same id, not added");
                    }
                }
            }
            String insertQuery="INSERT INTO BookList (id, title, author, total_copies, available_copies) VALUES (?,?,?,?,?)";
            try(PreparedStatement insertStmt=conn.prepareStatement(insertQuery)) {
                insertStmt.setLong(1, id);
                insertStmt.setString(2, title);
                insertStmt.setString(3, author);
                insertStmt.setInt(4, copies);
                insertStmt.setInt(5, copies);
                insertStmt.executeUpdate();
            }
            logger.log("Add Book - success");
            return ("Book Added Successfully");
        } catch (SQLException e) {
            return ("Error - " + e.getMessage());
        }
    }

    public String removeBook(long ID) {
        if(BookList.containsKey(ID)) {
            if(BookList.get(ID).getAvailCopies()<BookList.get(ID).getTotalCopies()) {
                logger.log("Remove Book - failure");
                return ("All copies not returned, cannot remove Book");
            }
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