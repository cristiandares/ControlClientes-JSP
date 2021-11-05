package web;

import datos.ClienteDaoJDBC;
import domain.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //recuperamos el valor de la accion
        String accion = request.getParameter("accion");
        //validamos si tenemos info en la variable
        if (accion != null) {
            //validamos la opcion que viene en el post con la variable accion
            switch (accion) {
                //si la accion es insertar entonces almacenamos en la base de datos el cliente
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                //si no se envia una accion entonces redirigimos a la pagina principal y listamos
                //todos los clientes con el metodo default
                default:
                    this.accionDefault(request, response);
            }
        }else{
            this.accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el valor de la accion
        String accion = request.getParameter("accion");
        //validamos si tenemos info en la variable
        if (accion != null) {
            //validamos la opcion que viene en el post con la variable accion
            switch (accion) {
                //si la accion es insertar entonces almacenamos en la base de datos el cliente
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                //si no se envia una accion entonces redirigimos a la pagina principal y listamos
                //todos los clientes con el metodo default
                default:
                    this.accionDefault(request, response);
            }
        }else{
            this.accionDefault(request, response);
        }
    }
    
    
    //este metodo lo que hace es generar el listado y la informacion de la pagina principal
    //lo que genera es el listado de la informacion
    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //listamos todos los clientes haciendo el llamado al metodo listar de la clase ClienteDaoJDBC
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        //compartimos la informacion en el alcance o scope de session
        HttpSession session = request.getSession();
        //envio por medio del request el listado de los cliente, pongo los clientes al alcance del request
        session.setAttribute("clientes", clientes);
        //agrego al request la cantidad de clientes
        session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //agrego al request el saldoTotal
        session.setAttribute("totalClientes", clientes.size());
        //redirecciono hacia un jsp y envio el request y el response
        //session.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    
    //metodo para insertar un cliente
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario modal para agregar el cliente a la base de datos
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        
        //validamos si el saldo tiene un valor y no es igual a la cadena vacia
        //debemos de validarlo de esta forma porque puede generar error si llega
        //la cadena vacia o null
        if(saldoString != null && !"".equals(saldoString)){
            saldo = Double.parseDouble(saldoString);
        }
        
        //creamos el objeto de cliente con los datos que hemos recibido (Modelo)
        Cliente cliente = new Cliente(nombre, apellido, email, telefono,saldo);
        //lo insertamos en la base de datos
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);
        
        //redirigimos hacia la pagina o metodo default
        this.accionDefault(request, response);  
    }
    
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //recuperamos el id del cliente que fue enviado desde el url por metodo get
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        //recuperamos los datos del cliente desde la base de datos
        //creamos el objeto cliente y usamos la capa de datos DaoJDBC e invocamos e metodo encontrar
        //para esto nos vamos a crear un objeto de tipo cliente y le pasamos el idCliente para crearlo
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        //ponemos el objeto en el alcance del request
        request.setAttribute("cliente", cliente);
        //indicamos el servlet a donde vamos a redireccionar
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        //redireccionamos hacia el jsp de editar
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    
    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //recuperamos los valores del formulario editarCliente para agregar el cliente a la base de datos
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        
        //validamos si el saldo tiene un valor y no es igual a la cadena vacia
        //debemos de validarlo de esta forma porque puede generar error si llega
        //la cadena vacia o null
        if(saldoString != null && !"".equals(saldoString)){
            saldo = Double.parseDouble(saldoString);
        }
        
        //creamos el objeto de cliente con los datos que hemos recibido (Modelo)
        Cliente cliente = new Cliente(idCliente,nombre, apellido, email, telefono,saldo);
        //lo modificamos el objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);
        
        //redirigimos hacia la pagina o metodo default
        this.accionDefault(request, response);  
    }
    
    
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //recuperamos el id del cliente para eliminar el cliente a la base de datos
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        //creamos el objeto de cliente con los datos que hemos recibido (Modelo)
        Cliente cliente = new Cliente(idCliente);
        //lo modificamos el objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);
        
        //redirigimos hacia la pagina o metodo default
        this.accionDefault(request, response);  
    }

    
    //funcion para calcular el saldo, recibe el listado de clientes e itera sumando las cantidades
    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
    
}
