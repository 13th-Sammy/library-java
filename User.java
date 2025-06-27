import java.util.HashMap;

class User {
    private final long uid;
    private final String name;
    private final HashMap<Long, Integer> BooksBorrowed=new HashMap<>();

    public User(long id, String n) {
        this.uid=id;
        this.name=n;
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(long id) {
        BooksBorrowed.put(id, BooksBorrowed.getOrDefault(id, 0)+1);
    }

    public int returnBook(long id) {
        if(BooksBorrowed.containsKey(id)) {
            int count=BooksBorrowed.get(id);
            if(count>1) {
                BooksBorrowed.put(id, count-1);
                return 1;
            }
            else {
                BooksBorrowed.remove(id);
                return 1;
            }
        }
        else {
            System.out.println("User has not borrowed this book with id - " + id);
            System.out.println();
            return -1;
        }
    }

    public void viewBorrowed() {
        for(long id:BooksBorrowed.keySet()) {
            System.out.println("Book ID - " + id);
            System.out.println("Copies Borrowed - " + BooksBorrowed.get(id));
            System.out.println();
        }
    }

    public HashMap<Long, Integer> getBorrowedMap() {
        return new HashMap<>(BooksBorrowed);
    }
}