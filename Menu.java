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
            String idStr=JOptionPane.showInputDialog(menuPanel, "Enter Book ID -").trim();
            long ID=Long.parseLong(idStr);
            String t=JOptionPane.showInputDialog(menuPanel, "Enter Book Name -").trim();
            String a=JOptionPane.showInputDialog(menuPanel, "Enter Author Name -").trim();
            String res=lib.addBook(ID, t, a);
            JOptionPane.showMessageDialog(menuPanel, res);
        });
        menuPanel.add(addBookBtn);

        JButton remBookBtn=new JButton("Remove Book");
        remBookBtn.addActionListener(e -> {
            String idStr=JOptionPane.showInputDialog(menuPanel, "Enter Book ID to remove from System -").trim();
            long id=Long.parseLong(idStr);
            String res=lib.removeBook(id);
            JOptionPane.showMessageDialog(menuPanel, res);
        });
        menuPanel.add(remBookBtn);

        JButton searchBookBtn=new JButton("Search Book");
        searchBookBtn.addActionListener(e-> {
            String title=JOptionPane.showInputDialog(menuPanel, "Enter Title to Search -").trim();
            frame.setContentPane(new SearchBooks(frame, lib, menuPanel, title));
            frame.revalidate();
            frame.repaint();
        });
        menuPanel.add(searchBookBtn);

        JButton regUserBtn=new JButton("Register User");
        regUserBtn.addActionListener(e -> {
            String uidStr=JOptionPane.showInputDialog(menuPanel, "Enter User ID -").trim();
            long uid=Long.parseLong(uidStr);
            String name=JOptionPane.showInputDialog(menuPanel, "Enter User Name -").trim();
            String res=lib.registerUser(uid, name);
            JOptionPane.showMessageDialog(menuPanel, res);
        });
        menuPanel.add(regUserBtn);

        JButton viewUserBtn=new JButton("View User");
        viewUserBtn.addActionListener(e-> {
            String uidStr=JOptionPane.showInputDialog(menuPanel, "Enter Uid -").trim();
            long uid=Long.parseLong(uidStr);
            frame.setContentPane(new ViewUser(frame, lib, menuPanel, uid));
            frame.revalidate();
            frame.repaint();
        });
        menuPanel.add(viewUserBtn);

        JButton viewAllUsersBtn=new JButton("View All Users");
        viewAllUsersBtn.addActionListener(e -> {
            frame.setContentPane(new ViewAllUsers(frame, lib, menuPanel));
            frame.revalidate();
            frame.repaint();
        });
        menuPanel.add(viewAllUsersBtn);

        JButton issueBookBtn=new JButton("Issue Book");
        issueBookBtn.addActionListener(e-> {
            String uidStr=JOptionPane.showInputDialog(menuPanel, "Enter Uid -").trim();
            long uid=Long.parseLong(uidStr);
            String bidStr=JOptionPane.showInputDialog(menuPanel, "Enter Book ID to borrow -").trim();
            long bid=Long.parseLong(bidStr);
            String noOfCopiesStr=JOptionPane.showInputDialog(menuPanel, "Enter number of copies to borrow -").trim();
            int noOfCopies=Integer.parseInt(noOfCopiesStr);
            String res=lib.issueBook(uid, bid, noOfCopies);
            JOptionPane.showMessageDialog(menuPanel, res);
        });
        menuPanel.add(issueBookBtn);

        JButton returnBookBtn=new JButton("Return Book");
        returnBookBtn.addActionListener(e -> {
            String uidStr=JOptionPane.showInputDialog(menuPanel, "Enter Uid -").trim();
            long uid=Long.parseLong(uidStr);
            String bidStr=JOptionPane.showInputDialog(menuPanel, "Enter Book ID to return -").trim();
            long bid=Long.parseLong(bidStr);
            String noOfCopiesStr=JOptionPane.showInputDialog(menuPanel, "Enter number of copies to return -").trim();
            int noOfCopies=Integer.parseInt(noOfCopiesStr);
            String res=lib.returnBookL(uid, bid, noOfCopies);
            JOptionPane.showMessageDialog(menuPanel, res);
        });
        menuPanel.add(returnBookBtn);

        frame.setContentPane(menuPanel);
    }

    public void showMenu() {
        frame.setVisible(true);
    }
}