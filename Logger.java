import java.util.ArrayList;

class Logger {
    private static  final Logger instance=new Logger();
    private final ArrayList<String> logs=new ArrayList<>();

    private Logger() {};

    public static Logger getInstance() {
        return instance;
    }

    public void log(String s) {
        String timestamp=java.time.LocalDateTime.now().toString().trim();
        logs.add("[" + timestamp + "] " + s);
    }

    public ArrayList<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void clearLogs() {
        logs.clear();
    }
}