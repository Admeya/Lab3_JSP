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

    private static Connection connection;

    private ConnectionPool() {
        try {
//            Properties env = new Properties();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
//            InitialContext context = new InitialContext(env);
//            ConnectionPoolDataSource dataSource = (ConnectionPoolDataSource) context.lookup("jdbc/myPostgres");
//           PooledConnection conn = dataSource.getPooledConnection();

            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("jdbc:postgresql");
            String dataResourceName = "jdbc/myPostgres";
            DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);


            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
