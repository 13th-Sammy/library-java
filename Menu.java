import java.awt.*;
import javax.swing.*;

class Menu {
    private final JFrame frame;
    private final Library lib=new Library();

    public Menu() {
        frame=new JFrame("Library Menu");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 1, 5, 5));
        
        JButton viewBooksBtn=new JButton("View Books");
        viewBooksBtn.addActionListener(e -> {
            lib.viewBooks();
        });
        frame.add(viewBooksBtn);

        JButton addBookBtn=new JButton("Add Book");
        addBookBtn.addActionListener(e -> {
            lib.addBook();
        });
        frame.add(addBookBtn);

        JButton remBookBtn=new JButton("Remove Book");
        remBookBtn.addActionListener(e -> {
            lib.removeBook();
        });
        frame.add(remBookBtn);

        JButton searchBookBtn=new JButton("Search Book");
        searchBookBtn.addActionListener(e-> {
            lib.searchBook();
        });
        frame.add(searchBookBtn);

        JButton regUserBtn=new JButton("Register User");
        regUserBtn.addActionListener(e -> {
            lib.registerUser();
        });
        frame.add(regUserBtn);

        JButton viewUserBtn=new JButton("View User");
        viewUserBtn.addActionListener(e-> {
            lib.viewUser();
        });
        frame.add(viewUserBtn);

        JButton viewAllUsersBtn=new JButton("View All Users");
        viewAllUsersBtn.addActionListener(e -> {
            lib.viewAllUsers();
        });
        frame.add(viewAllUsersBtn);

        JButton issueBookBtn=new JButton("Issue Book");
        issueBookBtn.addActionListener(e-> {
            lib.issueBook();
        });
        frame.add(issueBookBtn);

        JButton returnBookBtn=new JButton("Return Book");
        returnBookBtn.addActionListener(e -> {
            lib.returnBookL();
        });
        frame.add(returnBookBtn);
    }

    public void showMenu() {
        frame.setVisible(true);
    }
}