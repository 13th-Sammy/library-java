import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

class SearchBooks extends JPanel {
    public SearchBooks(JFrame frame, Library lib, JPanel menuPanel, String title) {
        setLayout(new BorderLayout());
        JPanel searchedPanel=new JPanel();
        searchedPanel.setLayout(new BoxLayout(searchedPanel, BoxLayout.Y_AXIS));
        HashMap<Long, Book> books=lib.getBookList();

        if(books.isEmpty()) {
            JLabel noBooks=new JLabel("No Books in Library");
            noBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
            searchedPanel.add(noBooks);
        }
        else {
            boolean found=false;
            for(Book b:books.values()) {
                if(b.getTitle().equalsIgnoreCase(title)) {
                    found=true;
                    JPanel block=new JPanel(new GridLayout(0,1));
                    block.setBorder(BorderFactory.createTitledBorder("Book ID - " + b.getID()));
                    block.add(new JLabel("Title - " + b.getTitle()));
                    block.add(new JLabel("Author - " + b.getAuthor()));
                    block.add(new JLabel("Total Copies - " + b.getTotalCopies()));
                    block.add(new JLabel("Available Copies - " + b.getAvailCopies()));
                    searchedPanel.add(block);
                }
            }
            if(found==false) {
                JLabel notFound=new JLabel("No such book exists");
                notFound.setAlignmentX(Component.CENTER_ALIGNMENT);
                searchedPanel.add(notFound);
            }
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