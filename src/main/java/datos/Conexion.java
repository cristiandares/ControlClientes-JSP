package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

//esta clase se va a encargar de realizar la conexion a la base de datos
public class Conexion {

    //definimos las constantes para conectarnos a la base de datos
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";

    private static BasicDataSource dataSourse;

    //con este metodo vamos a obtener una conexion de la base de datos
    public static DataSource getDatasource() {

        if (dataSourse == null) {
            //creo el objeto de tipo BasicDataSource para crear el objeto conexion
            dataSourse = new BasicDataSource();

            //le paso los parametros para la conexion
            dataSourse.setUrl(JDBC_URL);
            dataSourse.setUsername(JDBC_USER);
            dataSourse.setPassword(JDBC_PASSWORD);
            //establesco el pool de conexiones
            dataSourse.setInitialSize(50);
        }

        //devuelvo el objeto de conexion
        return dataSourse;
    }

    public static Connection getConnection() throws SQLException {
        return getDatasource().getConnection();
    }

    //Sobrecargamos el metodo para cerrar las conexiones de la base de datos.
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement st) {
        try {
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection cn) {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
