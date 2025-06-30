import java.sql.*;

class Logger {
    private static Logger instance=new Logger();

    private Logger() {}

    public static synchronized Logger getInstance() {
        if(instance==null) {
            instance=new Logger();
        }
        return instance;
    }

    public void log(String s) {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:logs.db")) {
            String insertLog="INSERT INTO Logs (message) VALUES (?)";
            try(PreparedStatement ps=conn.prepareStatement(insertLog)) {
                ps.setString(1, s);
                ps.executeUpdate();
            }
        } catch(SQLException e) {}
    }

    public void clearLogs() {
        try(Connection conn=DriverManager.getConnection("jdbc:sqlite:logs.db");
            Statement st=conn.createStatement()) {
            st.executeUpdate("DELETE FROM Logs"); 
        } catch(SQLException e) {}
    }
}