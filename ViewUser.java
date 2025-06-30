import java.awt.*;
import java.sql.*;
import javax.swing.*;

class ViewUser extends JPanel {
    public ViewUser(JFrame frame, JPanel menuPanel, long uid) {
        setLayout(new BorderLayout());
        JPanel userPanel=new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String getUser="SELECT * FROM UserList WHERE id=?";
            String getBooks="SELECT * FROM BorrowedBooks WHERE user_id=?";
            try(PreparedStatement ps1=conn.prepareStatement(getUser);
                PreparedStatement ps2=conn.prepareStatement(getBooks)) {
                ps1.setLong(1, uid);
                ps2.setLong(1, uid);
                try(ResultSet rs1=ps1.executeQuery();
                    ResultSet rs2=ps2.executeQuery()) {
                    if(rs1.next()) {
                        JPanel block=new JPanel(new GridLayout(0,1));
                        block.setBorder(BorderFactory.createTitledBorder("User ID - " + rs1.getLong("id")));
                        block.add(new JLabel("User Name - " + rs1.getString("name")));
                        boolean borrowed=false;
                        while(rs2.next()) {
                            borrowed=true;
                            block.add(new JLabel("Borrowed Book ID - " + rs2.getLong("book_id")));
                            block.add(new JLabel("Copies Borrowed - " + rs2.getInt("copies")));
                        }
                        if(borrowed==false) {
                            block.add(new JLabel("No Books Borrowed"));                
                        }
                        userPanel.add(block);
                    }
                    else {
                        JLabel notFound=new JLabel("No such user exists");
                        notFound.setAlignmentX(Component.CENTER_ALIGNMENT);
                        userPanel.add(notFound);                
                    }
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