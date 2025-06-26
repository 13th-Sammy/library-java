public class Book {
    private final long bookID;
    private final String title;
    private final String author;
    private int totalCopies;
    private int availCopies;

    public Book(long ID, String t, String a) {
        this.bookID=ID;
        this.title=t;
        this.author=a;
        totalCopies=0;
        availCopies=0;
    }

    public long getID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailCopies() {
        return availCopies;
    }

    public void addTotalCopies(int x) {
        totalCopies=totalCopies+x;
    }

    public void remTotalCopies(int x) {
        totalCopies=totalCopies-x;
    }

    public void addAvailCopies(int x) {
        availCopies=availCopies+x;
    }

    public void remAvailCopies(int x) {
        availCopies=availCopies-x;
    }
}