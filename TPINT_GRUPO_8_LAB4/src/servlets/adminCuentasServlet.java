package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ClienteDao;
import Dao.CuentaDao;
import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.TiposCuenta;
import negocio.NegocioCliente;

/**
 * Servlet implementation class adminCuentasServlet
 */
@WebServlet("/adminCuentasServlet")
public class adminCuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	NegocioCliente negCliente = new NegocioCliente();
	
	
    public adminCuentasServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("btnEliminar")!=null) {
            eliminarCuenta(request, response);
		}
		else if (request.getParameter("btnActivar")!=null) {
            activarCuenta(request, response);
		}       
		else if (request.getParameter("btnModificarCuenta")!=null) {
            modificarCuenta(request, response);
		}  
		else if(request.getParameter("SubmitCuenta")!=null) {
			crearCuenta(request, response);
		}
		//para listar los ID a la hora de agregar cuenta
		else if(request.getParameter("botonAgregar")!=null) {
			prepararCrearCuenta(request, response);
		}else if(request.getParameter("btnnModificarPrepararFormu")!=null) {
			prepararModificarCuenta(request, response);
		}
		else {  
			mostrarCuenta(request, response);
		}
	
	 }

	
	private void prepararCrearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CuentaDao cuentaDao = new CuentaDao();
		
		request.setAttribute("idProximoCrear",cuentaDao.generarNumeroCuenta());
		request.setAttribute("CbuProximoACrear",cuentaDao.generarCbu());
		
		request.setAttribute("listadoIdClientes", negCliente.listarConTrue());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminCrearCuenta.jsp");   
		requestDispatcher.forward(request, response);
	}
	
	
	private void prepararModificarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CuentaDao cuentaDao = new CuentaDao();
	        Cuenta cuenta = cuentaDao.buscar_con_id(Integer.parseInt(request.getParameter("idCuentaSeleccionado")));
	        request.setAttribute("cuentaAModificar", cuenta);
	        
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminCrearCuenta.jsp");
	        requestDispatcher.forward(request, response);
	}
	
	
	private void crearCuenta (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClienteDao clDao = new ClienteDao();
        CuentaDao cuentaDao = new CuentaDao();
		TiposCuenta tipCuenta = new TiposCuenta();
		Cuenta cuenta  = new Cuenta();
		
	    java.util.Date fechaHoy = new  java.util.Date() ;
		
		String tipoCuenta = request.getParameter("tipoCuenta");
		if (tipoCuenta.equals("Caja Ahorro")) 
		
		{   tipCuenta.setIdTipoCuenta(2);      
		    tipCuenta.setTipoCuenta(tipoCuenta);
		
		} else {
			
			tipCuenta.setIdTipoCuenta(1);      
		    tipCuenta.setTipoCuenta(tipoCuenta);
		}
		
		
		cuenta.setCbu(request.getParameter(("cbu")));
		cuenta.setSaldo(Float.parseFloat(request.getParameter("saldo")));
		cuenta.setTipoDeCuenta(tipCuenta);
		cuenta.setCliente(clDao.buscar_con_id(Integer.parseInt(request.getParameter("idCliente"))));
		cuenta.setNumeroCuenta(cuentaDao.generarNumeroCuenta());
		cuenta.setFecha(fechaHoy);
		cuenta.setEstado("True");
		cuenta.setIdCuenta(cuentaDao.cantidadRegistros() + 1);
		
		CuentaDao cd = new CuentaDao();
		int filasAgregadas = cd.agregarCuenta(cuenta);
		
		if(filasAgregadas == 0) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorAlCrearCuenta.jsp");   
			requestDispatcher.forward(request, response);
		}
		
	
		
		mostrarCuenta(request, response);
	}
	
   

	private void modificarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	TiposCuenta tipCuenta = new TiposCuenta();
	Cuenta cuenta  = new Cuenta();
	Cliente cli = new Cliente();
	
	cli.setId( Integer.parseInt ( request.getParameter("idCliente") )  );
	
    java.util.Date fec = null;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    try {
        fec = formatoFecha.parse(request.getParameter("fecha"));
    } catch (ParseException e) {
        e.printStackTrace();
    }
	
	
	
	String tipoCuenta = request.getParameter("tipoCuenta");
	if (tipoCuenta.equals("Caja Ahorro")) 
	
	{   tipCuenta.setIdTipoCuenta(2);      
	    tipCuenta.setTipoCuenta(tipoCuenta);
	
	} else {
		
		tipCuenta.setIdTipoCuenta(1);      
	    tipCuenta.setTipoCuenta(tipoCuenta);
	}
	

	cuenta.setCbu(request.getParameter(("cbu")));
	cuenta.setSaldo(Float.parseFloat(request.getParameter("saldo")));
	cuenta.setTipoDeCuenta(tipCuenta);
	cuenta.setCliente(cli);	
	cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("numeroCuenta")));
	cuenta.setFecha(fec);
	cuenta.setEstado(request.getParameter("estado"));
	cuenta.setIdCuenta(Integer.parseInt (request.getParameter("idCuenta")));
	
	CuentaDao cueDao = new CuentaDao();
	cueDao.ModificarCuenta(cuenta);
	
	mostrarCuenta(request, response);
	
	}
	
	
	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idParaBorrar = Integer.parseInt(request.getParameter("CuentaId"));

        CuentaDao cuentaDao = new CuentaDao();
        int baja = cuentaDao.BajaLogicaCuenta(idParaBorrar);

        if (baja == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("adminCuentas.jsp");
            rd.forward(request, response);
        }
    }
	
	
	
	private void activarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idParaActivar = Integer.parseInt(request.getParameter("CuentaId"));

        CuentaDao cuentaDao = new CuentaDao();
        int baja = cuentaDao.AltaLogicaCuenta(idParaActivar);

        if (baja == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("adminCuentas.jsp");
            rd.forward(request, response);
        }
    }	
	
	
	private void mostrarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		ArrayList<Cuenta> listadoCuentas = new ArrayList<Cuenta>();
		try {
			CuentaDao cd = new CuentaDao();
			//if(request.getParameter("botonMostrar") != null) 
				listadoCuentas = cd.Listar();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		// el boton de mostrar que muestra todos los clientes desactivados (estado false)		
		try {
			CuentaDao cd = new CuentaDao();
			if(request.getParameter("botonMostrarEliminados") != null) {
				listadoCuentas = cd.ListarConEstadoFalse();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		
		int filasAgregadas = 0;
		request.setAttribute("listadoCuentas", listadoCuentas);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminCuentas.jsp");   
		requestDispatcher.forward(request, response);
	}
		
		
		
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
