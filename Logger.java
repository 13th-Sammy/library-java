import java.util.ArrayList;

class Logger {
    private final ArrayList<String> logs=new ArrayList<>();

    public void log(String s) {
        String timestamp=java.time.LocalDateTime.now().toString().trim();
        logs.add("[" + timestamp + "] " + s);
    }

    public void printLogs() {
        for(String l:logs) {
            System.out.println(l);
        }
    }
}