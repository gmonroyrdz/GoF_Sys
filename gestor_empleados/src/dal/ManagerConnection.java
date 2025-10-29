package dal;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * ManagerConnection ahora delega en un ConnectionProvider. Esto aplica
 * Dependency Inversion: la obtención de la conexión queda abstraída y es
 * fácilmente sustituible (properties, env, pool, tests/mocks).
 */
public class ManagerConnection {
    private final ConnectionProvider provider;

    /** Constructor por defecto: usa PropertiesConnectionProvider (compatibilidad). */
    public ManagerConnection() {
        this(new PropertiesConnectionProvider());
    }

    /** Constructor para inyectar un proveedor alternativo (útil para tests). */
    public ManagerConnection(ConnectionProvider provider) {
        this.provider = provider;
    }

    /**
     * Obtiene una conexión. En caso de fallo se lanza una RuntimeException para
     * no obligar a los callers a manejar SQLException (mantener consistencia
     * con la implementación previa que retornaba null en error).
     */
    public Connection getConnection(){
        try{
            return provider.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo obtener la conexión a la BD", e);
        }
    }
}
