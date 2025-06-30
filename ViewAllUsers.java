import java.awt.*;
import java.sql.*;
import javax.swing.*;

class ViewAllUsers extends JPanel {
    public ViewAllUsers(JFrame frame, JPanel menuPanel) {
        setLayout(new BorderLayout());
        JPanel userPanel=new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db"); 
            Statement stmt=conn.createStatement()) {
            try(ResultSet rs=stmt.executeQuery("SELECT * FROM UserList")) {
                boolean foundUsers=false;
                while(rs.next()) {
                    foundUsers=true;
                    JPanel block=new JPanel(new GridLayout(0,1));
                    block.setBorder(BorderFactory.createTitledBorder("User ID - " + rs.getLong("id")));
                    block.add(new JLabel("User Name - " + rs.getString("name")));
                    String getBooks="SELECT * FROM BorrowedBooks WHERE user_id=?";
                    try(PreparedStatement ps=conn.prepareStatement(getBooks)) {
                        ps.setLong(1, rs.getLong("id"));
                        try(ResultSet rs2=ps.executeQuery()) {
                            boolean foundBooks=false;
                            while(rs2.next()) {
                                foundBooks=true;
                                block.add(new JLabel("Borrowed Book ID - " + rs2.getLong("book_id")));
                                block.add(new JLabel("Copies Borrowed - " + rs2.getInt("copies")));
                            }
                            if(foundBooks==false) {
                                block.add(new JLabel("No Books Borrowed"));
                            }
                        }
                    }
                    userPanel.add(block);
                }
                if(foundUsers==false) {
                    JLabel noUsers=new JLabel("No Users Registered");
                    noUsers.setAlignmentX(Component.CENTER_ALIGNMENT);
                    userPanel.add(noUsers);
                }
            }
        } catch(SQLException e) {
            JLabel errorMsg=new JLabel(e.getMessage());
            errorMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            userPanel.add(errorMsg);
        }
        JScrollPane scrollPane=new JScrollPane(userPanel);
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