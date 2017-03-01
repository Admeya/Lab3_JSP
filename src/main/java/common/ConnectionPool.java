package common;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ирина on 20.02.2017.
 */
public class ConnectionPool {
    static Logger logger = Logger.getLogger(ConnectionPool.class);

    private Connection connection;

    public ConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/Tourfirm");
            logger.trace("Connection is " + ds.getConnection());

            this.connection = ds.getConnection();
        } catch (SQLException e) {
            logger.error("SQL exception, однако", e);
        } catch (NamingException e) {
            logger.error("Ошибочка с именованием", e);
        } catch (ClassNotFoundException e) {
            logger.error("Драйвер для postgres не найден", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
