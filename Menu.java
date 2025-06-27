import java.awt.*;
import javax.swing.*;

class Menu {
    private final JFrame frame;
    private final Library lib=new Library();
    private final JPanel menuPanel=new JPanel();

    public Menu() {
        frame=new JFrame("Library Menu");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel.setLayout(new GridLayout(9, 1, 5, 5));
        
        JButton viewBooksBtn=new JButton("View Books");
        viewBooksBtn.addActionListener(e -> {
            frame.setContentPane(new ViewBooks(frame, lib, menuPanel));
            frame.revalidate();
            frame.repaint();
        });
        menuPanel.add(viewBooksBtn);

        JButton addBookBtn=new JButton("Add Book");
        addBookBtn.addActionListener(e -> {
            lib.addBook();
        });
        menuPanel.add(addBookBtn);

        JButton remBookBtn=new JButton("Remove Book");
        remBookBtn.addActionListener(e -> {
            lib.removeBook();
        });
        menuPanel.add(remBookBtn);

        JButton searchBookBtn=new JButton("Search Book");
        searchBookBtn.addActionListener(e-> {
            lib.searchBook();
        });
        menuPanel.add(searchBookBtn);

        JButton regUserBtn=new JButton("Register User");
        regUserBtn.addActionListener(e -> {
            lib.registerUser();
        });
        menuPanel.add(regUserBtn);

        JButton viewUserBtn=new JButton("View User");
        viewUserBtn.addActionListener(e-> {
            lib.viewUser();
        });
        menuPanel.add(viewUserBtn);

        JButton viewAllUsersBtn=new JButton("View All Users");
        viewAllUsersBtn.addActionListener(e -> {
            lib.viewAllUsers();
        });
        menuPanel.add(viewAllUsersBtn);

        JButton issueBookBtn=new JButton("Issue Book");
        issueBookBtn.addActionListener(e-> {
            lib.issueBook();
        });
        menuPanel.add(issueBookBtn);

        JButton returnBookBtn=new JButton("Return Book");
        returnBookBtn.addActionListener(e -> {
            lib.returnBookL();
        });
        menuPanel.add(returnBookBtn);

        frame.setContentPane(menuPanel);
    }

    public void showMenu() {
        frame.setVisible(true);
    }
}