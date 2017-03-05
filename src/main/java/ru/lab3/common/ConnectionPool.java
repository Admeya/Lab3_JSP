package ru.lab3.common;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ирина on 20.02.2017.
 */
public class ConnectionPool {
    static Logger logger = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool conn;
    private static Connection connection;

    private ConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/Tourfirm");

            this.connection = ds.getConnection();
        } catch (SQLException e) {
            logger.error("SQL exception, однако", e);
        } catch (NamingException e) {
            logger.error("Ошибочка с именованием", e);
        } catch (ClassNotFoundException e) {
            logger.error("Драйвер для postgres не найден", e);
        }
    }

    public synchronized static ConnectionPool getInstance() {
        if (conn == null) {
            conn = new ConnectionPool();
        }
        return conn;
    }

    public Connection getConnection() {
        return connection;
    }
}
