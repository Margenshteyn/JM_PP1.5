package app.utils;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            PropertyReaderHelper prh = new PropertyReaderHelper();
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:").
                    append(prh.getProperty("jdbcDriver") + "://").
                    append(prh.getProperty("serverName") + ":").
                    append(prh.getProperty("port") + "/").
                    append(prh.getProperty("dataBaseName") + "?").
                    append("serverTimezone=" + prh.getProperty("serverTimezone")).
                    append("&useSSL=" + prh.getProperty("useSSL")).
                    append("&allowPublicKeyRetrieval=" + prh.getProperty("allowPublicKeyRetrieval"));

            return DriverManager.getConnection(url.toString(), prh.getProperty("user"), prh.getProperty("password"));
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            throw  new IllegalStateException();
        }
    }
}
