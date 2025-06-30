import java.sql.*;

class Library {
    private final Logger logger=Logger.getInstance();
    
    public String addBook(long id, String title, String author, int copies) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String checkTitleAuthor="SELECT * FROM BookList WHERE title=? and author=?";
            try(PreparedStatement ps=conn.prepareStatement(checkTitleAuthor)) {
                ps.setString(1, title);
                ps.setString(2, author);
                try(ResultSet rs=ps.executeQuery()) {
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
            if(e.getMessage().contains("SQLITE_CONSTRAINT_PRIMARYKEY")) {
                logger.log("Add Book - failure");
                return ("Different Books cannot have same ID, not added");
            }
            return ("Error - " + e.getMessage());
        }
    }

    public String removeBook(long id) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String getBook="SELECT * FROM BookList WHERE id=?";
            try(PreparedStatement ps1=conn.prepareStatement(getBook)) {
                ps1.setLong(1, id);
                try(ResultSet rs=ps1.executeQuery()) {
                    if(rs.next()) {
                        int totalCopies=rs.getInt("total_copies");
                        int availableCopies=rs.getInt("available_copies");
                        if(availableCopies<totalCopies) {
                            logger.log("Remove Book - failure");
                            return ("All copies not returned, cannot remove Book");
                        }
                        String remBook="DELETE FROM BookList WHERE id=?";
                        try(PreparedStatement ps2=conn.prepareStatement(remBook)) {
                            ps2.setLong(1, id);
                            ps2.executeUpdate();
                        }
                        logger.log("Remove Book - success");
                        return ("Book with ID " + id + " removed from System permanently");
                    }
                    else {
                        logger.log("Remove Book - failure");
                        return ("No such book exists");
                    }
                }
            }
        } catch(SQLException e) {
            return ("Error - " + e.getMessage());
        }
    }

    public String registerUser(long uid, String name) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String regUser="INSERT INTO UserList VALUES (?, ?)";
            try(PreparedStatement ps=conn.prepareStatement(regUser)) {
                ps.setLong(1, uid);
                ps.setString(2, name);
                ps.executeUpdate();
            }
            logger.log("Register User - success");
            return ("User registered successfully, Uid - " + uid);
        } catch(SQLException e) {
            if(e.getMessage().contains("SQLITE_CONSTRAINT_PRIMARYKEY")) {
                logger.log("Register User - failure");
                return ("Different Users cannot have same ID, not added");
            }
            return ("Error - " + e.getMessage());
        }
    }

    public String removeUser(long uid) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String checkBorrowed="SELECT user_id FROM BorrowedBooks WHERE user_id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkBorrowed)) {
                ps.setLong(1, uid);
                try(ResultSet rs=ps.executeQuery()) {
                    if(rs.next()) {
                        logger.log("Remove User - failure");
                        return ("User has not returned all books, cannot remove");
                    }
                }
            }
            String checkExist="SELECT * FROM UserList WHERE id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkExist)) {
                ps.setLong(1, uid);
                try(ResultSet rs=ps.executeQuery()) {
                    if(!rs.next()) {
                        logger.log("Remove User - failure");
                        return ("No such user exists");
                    }
                }
            }
            String remUser="DELETE FROM UserList WHERE id=?";
            try(PreparedStatement ps=conn.prepareStatement(remUser)) {
                ps.setLong(1, uid);
                ps.executeUpdate();
            }
            logger.log("Remove User - success");
            return ("User with ID " + uid + " removed from System permanently");
        } catch(SQLException e) {
            return ("Error - " + e.getMessage());
        }
    }

    public String issueBook(long uid, long bid, int noOfCopies) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            try(Statement stmt=conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys=ON"); 
            }
            String checkCopies="SELECT * FROM BookList where id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkCopies)) {
                ps.setLong(1, bid);
                try(ResultSet rs=ps.executeQuery()) {
                    if(!rs.next()) {
                        logger.log("Issue Book - failure");
                        return ("No such book exists");
                    }
                    if(rs.getInt("available_copies")<noOfCopies) {
                        logger.log("Issue Book - failure");
                        return ("Not enough available copies");
                    }
                }
            }
            String issueBook="INSERT INTO BorrowedBooks (user_id, book_id, copies) VALUES (?,?,?) ON CONFLICT (user_id, book_id) DO UPDATE SET copies=copies+?";
            try(PreparedStatement ps=conn.prepareStatement(issueBook)) {
                ps.setLong(1, uid);
                ps.setLong(2, bid);
                ps.setLong(3, noOfCopies);
                ps.setLong(4, noOfCopies);
                ps.executeUpdate();
            }
            String remCopies="UPDATE BookList SET available_copies=available_copies-? WHERE id=?";
            try(PreparedStatement ps=conn.prepareStatement(remCopies)) {
                ps.setInt(1, noOfCopies);
                ps.setLong(2, bid);
                ps.executeUpdate();
            }
            logger.log("Issue Book - success");
            return ("Book issued successfully");
        } catch(SQLException e) {
            if(e.getMessage().contains("SQLITE_CONSTRAINT_FOREIGNKEY")) {
                logger.log("Issue Book - failure");
                return ("No such user exists");
            }
            return ("Error - " + e.getMessage());
        }
    }

    public String returnBook(long uid, long bid, int noOfCopies) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String checkBookExist="SELECT * FROM BookList WHERE id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkBookExist)) {
                ps.setLong(1, bid);
                try(ResultSet rs=ps.executeQuery()) {
                    if(!rs.next()) {
                        logger.log("Return Book - failed");
                        return ("No such book exists");
                    }
                }
            }
            String checkUserExist="SELECT * FROM UserList WHERE id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkUserExist)) {
                ps.setLong(1, uid);
                try(ResultSet rs=ps.executeQuery()) {
                    if(!rs.next()) {
                        logger.log("Return Book - failed");
                        return ("No such user exists");
                    }
                }
            }
            String checkBook="SELECT * FROM BorrowedBooks WHERE user_id=? AND book_id=?";
            try(PreparedStatement ps=conn.prepareStatement(checkBook)) {
                ps.setLong(1, uid);
                ps.setLong(2, bid);
                try(ResultSet rs=ps.executeQuery()) {
                    if(!rs.next()) {
                        logger.log("Return Book - failed");
                        return ("User has not borrowed this book with id - " + bid);
                    }
                    if(rs.getInt("copies")<noOfCopies) {
                        logger.log("Return Book - failed");
                        return ("User does not have so many copies, not returned");
                    }
                }
            }
            String retBook="UPDATE BorrowedBooks SET copies=copies-? WHERE user_id=? AND book_id=?";
            try(PreparedStatement ps2=conn.prepareStatement(retBook)) {
                ps2.setInt(1, noOfCopies);
                ps2.setLong(2, uid);
                ps2.setLong(3, bid);
                ps2.executeUpdate();
            }
            String checkBook2="SELECT * FROM BorrowedBooks WHERE user_id=? AND book_id=?";
            try(PreparedStatement ps2=conn.prepareStatement(checkBook2)) {
                ps2.setLong(1, uid);
                ps2.setLong(2, bid);
                try(ResultSet rs2=ps2.executeQuery()) {
                    if(rs2.getInt("copies")==0) {
                        String delBook="DELETE FROM BorrowedBooks WHERE user_id=? AND book_id=?";
                        try(PreparedStatement ps3=conn.prepareStatement(delBook)) {
                            ps3.setLong(1, uid);
                            ps3.setLong(2, bid);
                            ps3.executeUpdate();
                        }
                    }
                }
            }
            String updateBook="UPDATE BookList SET available_copies=available_copies+? WHERE id=?";
            try(PreparedStatement ps2=conn.prepareStatement(updateBook)) {
                ps2.setInt(1, noOfCopies);
                ps2.setLong(2, bid);
                ps2.executeUpdate();
            }
            logger.log("Return Book - success");
            return ("Book returned successfully");
        } catch(SQLException e) {
            return ("Error - " + e.getMessage());
        }
    }
}