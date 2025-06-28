import java.util.ArrayList;

class Logger {
    private final ArrayList<String> logs=new ArrayList<>();

    public void log(String s) {
        String timestamp=java.time.LocalDateTime.now().toString().trim();
        logs.add("[" + timestamp + "] " + s);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public void clearLogs() {
        logs.clear();
    }
}