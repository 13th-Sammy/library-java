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
            JOptionPane.showMessageDialog(frame, "View Books clicked");
        });
        frame.add(viewBooksBtn);

        JButton addBookBtn=new JButton("Add Book");
        addBookBtn.addActionListener(e -> {
            lib.addBook();
            JOptionPane.showMessageDialog(frame, "Add Book clicked");
        });
        frame.add(addBookBtn);

        JButton remBookBtn=new JButton("Remove Book");
        remBookBtn.addActionListener(e -> {
            lib.removeBook();
            JOptionPane.showMessageDialog(frame, "Remove Book clicked");
        });
        frame.add(remBookBtn);

        JButton searchBookBtn=new JButton("Search Book");
        searchBookBtn.addActionListener(e-> {
            lib.searchBook();
            JOptionPane.showMessageDialog(frame, "Search Book clicked");
        });
        frame.add(searchBookBtn);

        JButton regUserBtn=new JButton("Register User");
        regUserBtn.addActionListener(e -> {
            lib.registerUser();
            JOptionPane.showMessageDialog(frame, "Register User clicked");
        });
        frame.add(regUserBtn);

        JButton viewUserBtn=new JButton("View User");
        viewUserBtn.addActionListener(e-> {
            lib.viewUser();
            JOptionPane.showMessageDialog(frame, "View User clicked");
        });
        frame.add(viewUserBtn);

        JButton viewAllUsersBtn=new JButton("View All Users");
        viewAllUsersBtn.addActionListener(e -> {
            lib.viewAllUsers();
            JOptionPane.showMessageDialog(frame, "View All Users clicked");
        });
        frame.add(viewAllUsersBtn);

        JButton issueBookBtn=new JButton("Issue Book");
        issueBookBtn.addActionListener(e-> {
            lib.issueBook();
            JOptionPane.showMessageDialog(frame, "Issue Book clicked");
        });
        frame.add(issueBookBtn);

        JButton returnBookBtn=new JButton("Return Book");
        returnBookBtn.addActionListener(e -> {
            lib.returnBookL();
            JOptionPane.showMessageDialog(frame, "Return Book clicked");
        });
        frame.add(returnBookBtn);
    }

    public void showMenu() {
        frame.setVisible(true);
    }
}