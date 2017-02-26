package common;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ирина on 20.02.2017.
 */
public class ConnectionSingleton {
    static Logger logger = Logger.getLogger(ConnectionSingleton.class);

    private static ConnectionSingleton connection;
    private String url;
    private String user;
    private String pass;
    private String driver;

    private ConnectionSingleton() {
        try {
            this.driver = "org.postgresql.Driver";
            this.url = "jdbc:postgresql://localhost:5432/Tourfirm2";
            this.user = "postgres";
            this.pass = "root";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Не найден класс " + driver, e);
        }
    }

    public synchronized static ConnectionSingleton getInstance() {
        if (connection == null) {
            connection = new ConnectionSingleton();
        }
        return connection;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            logger.trace(e);
        }
        return con;
    }
}
