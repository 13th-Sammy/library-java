import java.awt.*;
import java.util.Collection;
import javax.swing.*;

class ViewBooks extends JPanel {
    public ViewBooks(JFrame frame, Library lib, JPanel menuPanel) {
        setLayout(new BorderLayout());
        JPanel booksPanel=new JPanel();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));
        Collection<Book> books=lib.getBookList();

        if(books.isEmpty()) {
            JLabel noBooks=new JLabel("No Books in Library");
            noBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
            booksPanel.add(noBooks);
        }
        else {
            for(Book b:books) {
                JPanel block=new JPanel(new GridLayout(0, 1));
                block.setBorder(BorderFactory.createTitledBorder("Book ID - " + b.getID()));
                block.add(new JLabel("Title - " + b.getTitle()));
                block.add(new JLabel("Author - " + b.getAuthor()));
                block.add(new JLabel("Available Copies - " + b.getAvailCopies()));
                block.add(new JLabel("Total Copies - " + b.getTotalCopies()));
                booksPanel.add(block);
            }
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