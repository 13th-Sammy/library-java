import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class ViewLogs extends JPanel {
    public ViewLogs(JFrame frame, Logger logger, JPanel menuPanel) {
        setLayout(new BorderLayout());
        JPanel logPanel=new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));
        ArrayList<String> logs=new ArrayList<>(logger.getLogs());

        if(logs.isEmpty()) {
            JLabel noLogs=new JLabel("No Logs yet");
            noLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
            logPanel.add(noLogs);
        }
        else {
            for(String s:logs) {
                JLabel logLine=new JLabel(s);
                logLine.setAlignmentX(Component.CENTER_ALIGNMENT);
                logPanel.add(logLine);
            }   
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