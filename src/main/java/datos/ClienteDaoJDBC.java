package datos;

import domain.Cliente;
import datos.*;
import java.sql.*;
import java.util.*;

//esta clase se va a conectar a la base de datos
public class ClienteDaoJDBC {
    //Declaro las sentencias SQL con las que voy a trabajar

    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente WHERE id_cliente = ?";

    private static final String SQL_INSERT = "INSERT INTO CLIENTE (nombre, apellido, email, telefono, saldo)"
            + "VALUES(?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE cliente "
            + "SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente =?";

    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";
    
    
    //este metodo se encarga de encontrar un cliente, un solo registro
    public List<Cliente> listar() {
        //creo la variable con la que voy a manejar la conexion a la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            //realizamos la conexion y ejecutamos la consulta
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            //recorremos el resultado de la consulta
            //validamos si hay algun elemento por iterar
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente"); //recuperamos el valor de la columna
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Double saldo = rs.getDouble("saldo");

                //por cada uno de los registros de la base de datos voy creando el objeto con los datos
                cliente = new Cliente(id_cliente, nombre, apellido, email, telefono, saldo);

                //agregamos el objeto al listado de clientes
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //cerramos las conexiones de la base de datos
            //recordar que se cierran las conexiones de manera invertida en como se crearon
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //retornamos la lista de clientesq
        return clientes;
    }

    
    //este metodo busca un cliente en la base de datos y lo devuelte.
    public Cliente encontrar(Cliente cliente) {
        //creo la variable con la que voy a manejar la conexion a la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //realizamos la conexion y ejecutamos la consulta
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //proporcionamos el parametro que vamos a ejecutar en la consulta SQL
            //indicamos el numero del parametro segun la posicion de la sentencia SQL
            //y como segundo paramtro le indico el valor, en este caso obtengo el id del cliente para la consulta
            stmt.setInt(1, cliente.getIdCliente());
            //ejecutamos la consulta
            rs = stmt.executeQuery();
            //nos posicionamos en el primer registro del resultado de la consulta SQL
            rs.absolute(1);

            //Recuperamos los valores 
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            //inicializamos las variables en el objeto que recibimos    
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //cerramos las conexiones de la base de datos
            //recordar que se cierran las conexiones de manera invertida en como se crearon
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //retornamos el cliente con la informacion consultada en la base de datos
        return cliente;
    }

    
    //este metodo va a insertar un elemento de tipo Cliente en la base de datos
    public int insertar(Cliente cliente){
        //creo la variable con la que voy a manejar la conexion a la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;
        //creamos la variable donde vamos a recibir la cantidad de registros modificados
        int rows = 0;

        try {
            //realizamos la conexion y ejecutamos la consulta de insertar datos
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            //en la consultar de INSERTR hay unos signos ? que son los parametros que vamos a poner en la consulta
            //en estas siguientes lineas vamos a poner esos parametros, tomando el cliente y pidiendo los valores
            stmt.setString(1,cliente.getNombre());
            stmt.setString(2,cliente.getApellido());
            stmt.setString(3,cliente.getEmail());
            stmt.setString(4,cliente.getTelefono());
            stmt.setDouble(5,cliente.getSaldo());
            
            //ejecutamos la consulta y almacenamos la respuestra de tipo entero en la variable rowus
            //para saber la cantidad de elementos modificados
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //cerramos las conexiones de la base de datos
            //recordar que se cierran las conexiones de manera invertida en como se crearon
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //retornamos la canditdad de elementos insertados
        return rows;
    }
    
    
    //este metodo va a actualizar un registgro de la base de datos
    public int actualizar(Cliente cliente){
        //creo la variable con la que voy a manejar la conexion a la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;
        //creamos la variable donde vamos a recibir la cantidad de registros modificados
        int rows = 0;

        try {
            //realizamos la conexion y ejecutamos la consulta de actualizar datos
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            //en la consultar de UPDATE hay unos signos ? que son los parametros que vamos a poner en la consulta
            //en estas siguientes lineas vamos a poner esos parametros, tomando el cliente y pidiendo los valores
            stmt.setString(1,cliente.getNombre());
            stmt.setString(2,cliente.getApellido());
            stmt.setString(3,cliente.getEmail());
            stmt.setString(4,cliente.getTelefono());
            stmt.setDouble(5,cliente.getSaldo());
            stmt.setInt(6,cliente.getIdCliente());
            
            //ejecutamos la consulta y almacenamos la respuestra de tipo entero en la variable rowus
            //para saber la cantidad de elementos modificados
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //cerramos las conexiones de la base de datos
            //recordar que se cierran las conexiones de manera invertida en como se crearon
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    
    public int eliminar(Cliente cliente){
        //creo la variable con la que voy a manejar la conexion a la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;
        //creamos la variable donde vamos a recibir la cantidad de registros eliminados
        int rows = 0;

        try {
            //realizamos la conexion y ejecutamos la consulta de eliminar datos
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            //en la consultar de UPDATE hay unos signos ? que son los parametros que vamos a poner en la consulta
            //en estas siguientes lineas vamos a poner esos parametros, tomando el cliente y pidiendo los valores
            stmt.setInt(1,cliente.getIdCliente());
            
            //ejecutamos la consulta y almacenamos la respuestra de tipo entero en la variable rowus
            //para saber la cantidad de elementos modificados
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //cerramos las conexiones de la base de datos
            //recordar que se cierran las conexiones de manera invertida en como se crearon
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
