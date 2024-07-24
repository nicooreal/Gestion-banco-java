package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;
import Dominio.Telefono;
import Dao.ClienteDao;
import Dao.LocalidadDao;
import Dao.PaisDao;
import Dao.ProvinciaDao;

import java.util.ArrayList;

/**
 * Servlet implementation class adminClientesServlet
 */
@WebServlet("/adminClientesServlet")
public class adminClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
       if (request.getParameter("botonMostrarEliminados")!=null) {
            mostrarClientesEliminados(request, response);
       } else if (request.getParameter("botonMostrarActivados")!=null) {
    	   mostrarClientesActivados(request, response);
       } else if (request.getParameter("botonAgregar")!=null) {
        	mostrarFormularioAgregar(request, response);
        }else if (request.getParameter("btnModificar")!=null) {
        	mostrarFormularioModificar(request, response);
        } else if (request.getParameter("btnEliminar")!=null) {
            eliminarCliente(request, response);
        } else if (request.getParameter("modificarCliente")!=null) {
            modificarCliente(request, response);
        } else if (request.getParameter("btnActivar")!=null) {
            activarCliente(request, response);
        }else if (request.getParameter("SubmitCliente")!=null) {
            agregarCliente(request, response);
        } else {
            // Acción por defecto o manejo de casos no definidos
            mostrarClientes(request, response);
        }
    }

    private void mostrarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Cliente> listadoClientes = new ArrayList<>();
        
        try {
            ClienteDao cd = new ClienteDao();
            listadoClientes = cd.Listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listadoClientes", listadoClientes);
        RequestDispatcher rd = request.getRequestDispatcher("/adminClientes.jsp");
        rd.forward(request, response);
    }

    private void mostrarClientesEliminados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Cliente> listadoClientes = new ArrayList<>();
        
        try {
            ClienteDao cd = new ClienteDao();
            listadoClientes = cd.ListarConEstadoFalse();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listadoClientes", listadoClientes);
        RequestDispatcher rd = request.getRequestDispatcher("/adminClientes.jsp");
        rd.forward(request, response);
    }

   
    private void mostrarClientesActivados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Cliente> listadoClientes = new ArrayList<>();
        
        try {
            ClienteDao cd = new ClienteDao();
            listadoClientes = cd.ListarConEstadoTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listadoClientes", listadoClientes);
        RequestDispatcher rd = request.getRequestDispatcher("/adminClientes.jsp");
        rd.forward(request, response);
    }

    
    
    
    
    
    private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        int filasAgregadas = 0;

        // Validación de campos
        if (validarCamposCliente(request)) {
            // Procesamiento para agregar cliente
            java.util.Date dateNacimiento = null;
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateNacimiento = formatoFecha.parse(request.getParameter("fechaNacimiento"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            ProvinciaDao proDao = new ProvinciaDao();
            Provincia provincia = new Provincia();
            provincia = proDao.getProvinciaConId(Integer.parseInt(request.getParameter("provincia")));

            
            PaisDao paisDao = new PaisDao();
            Pais pais = new Pais();
            int idPais = Integer.parseInt(request.getParameter("nacionalidad"));
          
            
            pais = paisDao.getPaisConId(idPais);
            
            
            LocalidadDao localidadDao = new LocalidadDao();
            Localidad localidad = new Localidad();
            localidad = localidadDao.getLocalidadConId(Integer.parseInt(request.getParameter("localidad")));
            
            
            Cliente cliente = new Cliente();
            
            Telefono telefono1 = new Telefono(request.getParameter("telefono1"));
            Telefono telefono2 = new Telefono(request.getParameter("telefono2"));
            
            
            cliente.setDni(request.getParameter("dni"));
            cliente.setCuil(request.getParameter("cuil"));
            cliente.setNombre(request.getParameter("nombre"));
            cliente.setApellido(request.getParameter("apellido"));
            cliente.setSexo(request.getParameter("sexo"));
            cliente.setFechaNacimiento(dateNacimiento);
            cliente.setNacionalidad(pais);
            cliente.setDireccion(request.getParameter("direccion"));
            cliente.setLocalidad(localidad);
            cliente.setProvincia(provincia);
            cliente.setCorreoElectronico(request.getParameter("email"));
            cliente.setEstado("True");
          
            cliente.setTelefono1(telefono1);
            cliente.setTelefono2(telefono2);
            
            ClienteDao cd = new ClienteDao();
            filasAgregadas = cd.agregarCliente(cliente);
            
            
        } else {
            // Manejo de error: campos incompletos o inválidos
            // Podrías redirigir a un JSP de error o mostrar un mensaje adecuado
        }

        // Redireccionamiento a la lista de clientes después de agregar
        mostrarClientes(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idParaBorrar = Integer.parseInt(request.getParameter("ClienteId"));

        ClienteDao cliDao = new ClienteDao();
        int baja = cliDao.BajaLogicaCliente(idParaBorrar);

        if (baja == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("adminClientes.jsp");
            rd.forward(request, response);
        }
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idParaModificar = Integer.parseInt(request.getParameter("idModificar"));

       //  Validación de campos
    //    if (validarCamposCliente(request)) {
            // Procesamiento para modificar cliente
            java.util.Date dateNacimiento = null;
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateNacimiento = formatoFecha.parse(request.getParameter("fechaNacimiento"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            ProvinciaDao proDao = new ProvinciaDao();
            Provincia provincia = new Provincia();
            provincia = proDao.getProvinciaConId(Integer.parseInt(request.getParameter("provincia")));

            PaisDao paisDao = new PaisDao();            
            Pais pais = new Pais();
            pais = paisDao.getPaisConId(Integer.parseInt(request.getParameter("nacionalidad")));
            
            
          LocalidadDao localidadDao = new LocalidadDao();
          Localidad localidad = new Localidad();
          localidad = localidadDao.getLocalidadConId(Integer.parseInt(request.getParameter("localidad")));
          
          
            Cliente cliente = new Cliente();
            cliente.setDni(request.getParameter("dni"));
            cliente.setCuil(request.getParameter("cuil"));
            cliente.setNombre(request.getParameter("nombre"));
            cliente.setApellido(request.getParameter("apellido"));
            cliente.setSexo(request.getParameter("sexo"));
            cliente.setFechaNacimiento(dateNacimiento);
            cliente.setNacionalidad(pais);
            cliente.setDireccion(request.getParameter("direccion"));
            cliente.setLocalidad(localidad);
            cliente.setProvincia(provincia);
            cliente.setCorreoElectronico(request.getParameter("email"));
            cliente.setEstado("True");
            cliente.setId(idParaModificar);

            ClienteDao cd = new ClienteDao();
            cd.ModificacionCliente(cliente);
   
            
                     if(cd.ModificacionTelefonos(request.getParameter("telefono1"), request.getParameter("telefono2"), idParaModificar) > 0) {
                System.out.println("Telefono modificado ok");
            }   
            
            
            //     } else {
            // Manejo de error: campos incompletos o inválidos
            // Podrías redirigir a un JSP de error o mostrar un mensaje adecuado
    //   }

        // Redireccionamiento a la lista de clientes después de modificar
        mostrarClientes(request, response);
    }

    private void activarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idParaActivar = Integer.parseInt(request.getParameter("ClienteId"));

        ClienteDao cliDao = new ClienteDao();
        int alta = cliDao.AltaLogicaCliente(idParaActivar);

        if (alta == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("adminClientes.jsp");
            rd.forward(request, response);
        }
    }

    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        ProvinciaDao pDao = new ProvinciaDao();
        ArrayList<Provincia> listadoProvincias = pDao.getListaProvincias();
        request.setAttribute("listadoProvincias", listadoProvincias);
      //  RequestDispatcher rd = request.getRequestDispatcher("/adminCrearCliente.jsp");
       // rd.forward(request, response);
    
    
        PaisDao paisDao = new PaisDao();
        ArrayList<Pais> listadoPais = paisDao.getListaPaises();
        request.setAttribute("listadoPaises", listadoPais);
       
        
        LocalidadDao localidadDao = new LocalidadDao();
        ArrayList<Localidad> listadoLocalidad = localidadDao.getListaLocalidades();
        request.setAttribute("listadoLocalidades", listadoLocalidad);
       
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/adminCrearCliente.jsp");
        rd.forward(request, response);
    
    
    
    
    
    }
    
    private void mostrarFormularioModificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//listo provincias primero
    	 ProvinciaDao pDao = new ProvinciaDao();
         ArrayList<Provincia> listadoProvincias = pDao.getListaProvincias();
         request.setAttribute("listadoProvincias", listadoProvincias);
         
         PaisDao paisDao = new PaisDao();         
        ArrayList<Pais> listadoPais = paisDao.getListaPaises();
        request.setAttribute("listadoPaises", listadoPais);
      //   
         
       LocalidadDao localidadDao = new LocalidadDao();         
       ArrayList<Localidad> listadoLocalidad = localidadDao.getListaLocalidades() ;
       request.setAttribute("listadoLocalidades", listadoLocalidad);
        
        
         request.setAttribute("clienteId", Integer.parseInt(request.getParameter("clienteId")));
         
         RequestDispatcher rd = request.getRequestDispatcher("/adminCrearCliente.jsp");
         rd.forward(request, response);
    }

    private boolean validarCamposCliente(HttpServletRequest request) {
        return request.getParameter("dni") != null && !request.getParameter("dni").isEmpty() &&
               request.getParameter("cuil") != null && !request.getParameter("cuil").isEmpty() &&
               request.getParameter("nombre") != null && !request.getParameter("nombre").isEmpty() &&
               request.getParameter("apellido") != null && !request.getParameter("apellido").isEmpty() &&
               request.getParameter("sexo") != null && !request.getParameter("sexo").isEmpty() &&
               request.getParameter("nacionalidad") != null && !request.getParameter("nacionalidad").isEmpty() &&
               request.getParameter("direccion") != null && !request.getParameter("direccion").isEmpty() &&
               request.getParameter("localidad") != null && !request.getParameter("localidad").isEmpty() &&
               request.getParameter("provincia") != null && !request.getParameter("provincia").isEmpty() &&
               request.getParameter("email") != null && !request.getParameter("email").isEmpty() &&
               request.getParameter("fechaNacimiento") != null && !request.getParameter("fechaNacimiento").isEmpty();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}