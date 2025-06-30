import java.awt.*;
import java.sql.*;
import javax.swing.*;

class ViewBooks extends JPanel {
    public ViewBooks(JFrame frame, JPanel menuPanel) {
        setLayout(new BorderLayout());
        JPanel booksPanel=new JPanel();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));

        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM BookList")) {
            boolean hasBooks=false;
            while(rs.next()) {
                hasBooks=true;
                JPanel block=new JPanel(new GridLayout(0, 1));
                block.setBorder(BorderFactory.createTitledBorder("Book ID - " + rs.getInt("id")));
                block.add(new JLabel("Title - " + rs.getString("title")));
                block.add(new JLabel("Author - " + rs.getString("author")));
                block.add(new JLabel("Total Copies - " + rs.getInt("total_copies")));
                block.add(new JLabel("Available Copies - " + rs.getInt("available_copies")));
                booksPanel.add(block);
            }
            if(!hasBooks) {
                JLabel noBooks=new JLabel("No Books in Library");
                noBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
                booksPanel.add(noBooks);
            }
        } catch (SQLException e) {
            JLabel errorMsg=new JLabel("Error - " + e.getMessage());
            errorMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            booksPanel.add(errorMsg);
        }
        JScrollPane scrollPane=new JScrollPane(booksPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton backBtn=new JButton("Back to Menu");
        backBtn.addActionListener(e -> {
            frame.setContentPane(menuPanel);
            frame.revalidate();
            frame.repaint();
        });
        JPanel bottomPanel=new JPanel();
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}