import java.awt.*;
import java.sql.*;
import javax.swing.*;

class SearchBooks extends JPanel {
    public SearchBooks(JFrame frame, JPanel menuPanel, String title) {
        setLayout(new BorderLayout());
        JPanel searchedPanel=new JPanel();
        searchedPanel.setLayout(new BoxLayout(searchedPanel, BoxLayout.Y_AXIS));

        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:library.db")) {
            String searchTitle="SELECT * FROM BookList WHERE title LIKE '%' || ? || '%' COLLATE NOCASE";
            try(PreparedStatement ps=conn.prepareStatement(searchTitle)) {
                ps.setString(1, title);
                ResultSet rs=ps.executeQuery();
                boolean found=false;
                while(rs.next()) {
                    found=true;
                    JPanel block=new JPanel(new GridLayout(0,1));
                    block.setBorder(BorderFactory.createTitledBorder("Book ID - " + rs.getLong("id")));
                    block.add(new JLabel("Title - " + rs.getString("title")));
                    block.add(new JLabel("Author - " + rs.getString("author")));
                    block.add(new JLabel("Total Copies - " + rs.getInt("total_copies")));
                    block.add(new JLabel("Available Copies - " + rs.getInt("available_copies")));
                    searchedPanel.add(block);
                }
                if(found==false) {
                    JLabel notFound=new JLabel("No such book exists");
                    notFound.setAlignmentX(Component.CENTER_ALIGNMENT);
                    searchedPanel.add(notFound);
                }
            }
        } catch(SQLException e) {
            JLabel errorMsg=new JLabel("Error - " + e.getMessage());
            errorMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            searchedPanel.add(errorMsg);
        }
        JScrollPane scrollPane=new JScrollPane(searchedPanel);
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