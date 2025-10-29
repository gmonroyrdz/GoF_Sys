package dal;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstraction for obtaining JDBC connections. Allows swapping implementations
 * (properties file, environment variables, connection pool, etc.) without
 * changing callers.
 */
public interface ConnectionProvider {
    Connection getConnection() throws SQLException;
}
