import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.*;

class ViewUser extends JPanel {
    public ViewUser(JFrame frame, Library lib, JPanel menuPanel, long uid) {
        setLayout(new BorderLayout());
        JPanel userPanel=new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        Collection<User> users=lib.getUserList();

        if(users.isEmpty()) {
            JLabel noBooks=new JLabel("No Users Registered");
            noBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
            userPanel.add(noBooks);
        }
        else {
            boolean found=false;
            for(User u:users) {
                if(uid==u.getUid()) {
                    found=true;
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
                    userPanel.add(block);
                }
            }
            if(found==false) {
                JLabel notFound=new JLabel("No such user exists");
                notFound.setAlignmentX(Component.CENTER_ALIGNMENT);
                userPanel.add(notFound);
            }
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