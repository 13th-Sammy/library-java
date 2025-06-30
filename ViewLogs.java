import java.awt.*;
import java.sql.*;
import javax.swing.*;

class ViewLogs extends JPanel {
    public ViewLogs(JFrame frame, JPanel menuPanel) {
        setLayout(new BorderLayout());
        JPanel logPanel=new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));

        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:logs.db");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM Logs")) {
            if(!rs.next()) {
                JLabel noLogs=new JLabel("No Logs yet");
                noLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
                logPanel.add(noLogs);
            }
            while(rs.next()) {
                JLabel logline=new JLabel(rs.getLong("id") + " | " + rs.getString("message") + " | " + rs.getString("timestamp"));
                logline.setAlignmentX(Component.CENTER_ALIGNMENT);
                logPanel.add(logline);
            }
        } catch(SQLException e) {
            JLabel errorMsg=new JLabel("Error - " + e.getMessage());
            errorMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            logPanel.add(errorMsg);
        }
        JScrollPane scrollPane=new JScrollPane(logPanel);
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