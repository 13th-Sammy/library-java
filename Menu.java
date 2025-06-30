import java.awt.*;
import javax.swing.*;

class Menu {
    private final JFrame frame;
    private final Library lib=new Library();
    private final Logger logger=Logger.getInstance();
    private final JPanel menuPanel=new JPanel();

    public Menu() {
        frame=new JFrame("Library Menu");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel.setLayout(new GridLayout(12, 1, 5, 5));
        
        JButton viewBooksBtn=new JButton("View Books");
        viewBooksBtn.addActionListener(e -> {
            frame.setContentPane(new ViewBooks(frame, menuPanel));
            frame.revalidate();
            frame.repaint();
            logger.log("View Books");
        });
        menuPanel.add(viewBooksBtn);

        JButton addBookBtn=new JButton("Add Book");
        addBookBtn.addActionListener(e -> {
            String idStr=JOptionPane.showInputDialog(menuPanel, "Enter Book ID -").trim();
            long id=Long.parseLong(idStr);
            String title=JOptionPane.showInputDialog(menuPanel, "Enter Book Name -").trim();
            String author=JOptionPane.showInputDialog(menuPanel, "Enter Author Name -").trim();
            String copiesStr=JOptionPane.showInputDialog(menuPanel, "Enter number of copies -").trim();
            int copies=Integer.parseInt(copiesStr);
            String res=lib.addBook(id, title, author, copies);
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
            frame.setContentPane(new SearchBooks(frame, menuPanel, title));
            frame.revalidate();
            frame.repaint();
            logger.log("Search Book");
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

        JButton remUserBtn=new JButton("Remove User");
        remUserBtn.addActionListener(e -> {
            String uidStr=JOptionPane.showInputDialog(menuPanel, "Enter User ID to remove from System -").trim();
            long uid=Long.parseLong(uidStr);
            String res=lib.removeUser(uid);
            JOptionPane.showMessageDialog(menuPanel, res);
        });
        menuPanel.add(remUserBtn);

        JButton viewUserBtn=new JButton("View User");
        viewUserBtn.addActionListener(e-> {
            String uidStr=JOptionPane.showInputDialog(menuPanel, "Enter Uid -").trim();
            long uid=Long.parseLong(uidStr);
            frame.setContentPane(new ViewUser(frame, menuPanel, uid));
            frame.revalidate();
            frame.repaint();
            logger.log("View User");
        });
        menuPanel.add(viewUserBtn);

        JButton viewAllUsersBtn=new JButton("View All Users");
        viewAllUsersBtn.addActionListener(e -> {
            frame.setContentPane(new ViewAllUsers(frame, menuPanel));
            frame.revalidate();
            frame.repaint();
            logger.log("View All Users");
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

        JButton viewLogsBtn=new JButton("View Logs");
        viewLogsBtn.addActionListener(e -> {
            frame.setContentPane(new ViewLogs(frame, logger, menuPanel));
            frame.revalidate();
            frame.repaint();
        });
        menuPanel.add(viewLogsBtn);

        JButton clearLogsBtn=new JButton("Clear All Logs");
        clearLogsBtn.addActionListener(e -> {
            logger.clearLogs();
            logger.log("Logs Cleared");
        });
        menuPanel.add(clearLogsBtn);

        frame.setContentPane(menuPanel);
    }

    public void showMenu() {
        frame.setVisible(true);
    }
}