import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

class ViewAllUsers extends JPanel {
    public ViewAllUsers(JFrame frame, Library lib, JPanel menuPanel) {
        setLayout(new BorderLayout());
        JPanel usersPanel=new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        HashMap<Long, User> users=lib.getUserList();

        if(users.isEmpty()) {
            JLabel noBooks=new JLabel("No Users Registered");
            noBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
            usersPanel.add(noBooks);
        }
        else {
            for(User u:users.values()) {
                JPanel block=new JPanel(new GridLayout(0,1));
                block.setBorder(BorderFactory.createTitledBorder("User ID - " + u.getUid()));
                block.add(new JLabel("User Name - " + u.getName()));
                HashMap<Long, Integer> BooksBorrowed=new HashMap<>(u.getBorrowedMap());
                if(BooksBorrowed.isEmpty()) {
                    block.add(new JLabel("No Books Borrowed"));
                }
                else {
                    for(long bid:BooksBorrowed.keySet()) {
                        block.add(new JLabel("Borrowed Book ID - " + bid));
                        block.add(new JLabel("Copies Borrowed - " + BooksBorrowed.get(bid)));
                    }
                }
                usersPanel.add(block);
            }
        }
        JScrollPane scrollPane=new JScrollPane(usersPanel);
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