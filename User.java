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

    public void borrowBook(long id, int n) {
        BooksBorrowed.put(id, BooksBorrowed.getOrDefault(id, 0)+n);
    }

    public int returnBook(long id, int n) {
        if(BooksBorrowed.containsKey(id)) {
            int count=BooksBorrowed.get(id);
            if(count-n>0) {
                BooksBorrowed.put(id, count-n);
                return 1;
            }
            else if(count-n==0) {
                BooksBorrowed.remove(id);
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return -1;
        }
    }

    public HashMap<Long, Integer> getBorrowedMap() {
        return new HashMap<>(BooksBorrowed);
    }
}