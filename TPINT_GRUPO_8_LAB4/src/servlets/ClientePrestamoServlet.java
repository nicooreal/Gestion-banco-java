package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CuentaDao;
import Dao.PrestamoDao;
import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Prestamo;
import Dominio.Usuario;

/**
 * Servlet implementation class ClientePrestamoServlet
 */
@WebServlet("/ClientePrestamoServlet")
public class ClientePrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ClientePrestamoServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener el usuario desde la sesión
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int id_cliente = usuario.getCliente().getId();
        
        
        if (request.getParameter("action") != null) {
	        if(request.getParameter("action").equals("ListarPrestamosPorCliente")) {
	        	PrestamoDao pDao = new PrestamoDao();
	    		List<Prestamo> listadoPrestamosPorCliente = pDao.ListarPorIdCliente(id_cliente);
	    		request.setAttribute("listadoPrestamosPorCliente", listadoPrestamosPorCliente);
	            RequestDispatcher rd = request.getRequestDispatcher("/clientePagoPrestamos.jsp");
	            rd.forward(request, response);
	        }
        }
        else {
        	if (request.getParameter("botonCrearPrestamo") != null) {
        		mostrarListadoCuentas(request, response);
        	}
        }
		
	}
	
	//traer listado de cuentas por cliente
	private void mostrarListadoCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuentaDao cDao = new CuentaDao();
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
        
        List<Cuenta> listadoCuentas = cDao.getListaCuentasPorCliente(usuarioLogueado.getCliente().getId());
        request.setAttribute("listadoCuentas", listadoCuentas);
        RequestDispatcher rd = request.getRequestDispatcher("/clienteCrearPrestamos.jsp");
        rd.forward(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("crearPrestamo")!=null) {
			determinarCostosAgregarPrestamo(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/clientePerfilServlet?crearPrestamo=true");
			rd.forward(request, response);
        }
	}
	
	
	private void determinarCostosAgregarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filasAgregadas = 0;
		if(validarCamposPrestamo(request)) {
			
			Cliente cliente = new Cliente(); 
			Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
			cliente = usuarioLogueado.getCliente();
			int porcentaje_interes = 10;
			int plazo = 365; //dias para pagarlo
			int cuotas = Integer.parseInt(request.getParameter("cuotas"));
			float importe_solicitado = Float.parseFloat(request.getParameter("importe_solicitado"));
			float interes_nominal = (importe_solicitado / 100) * porcentaje_interes;
			float importe_a_pagar = interes_nominal + importe_solicitado;
			float monto_mensual = importe_a_pagar/cuotas;
			
			
			Prestamo prestamo = new Prestamo();
			prestamo.setCliente(cliente);
			prestamo.setCuotas(cuotas);
			prestamo.setImporteSolicitado(importe_solicitado);
			prestamo.setIdCuentaDestino(Integer.parseInt(request.getParameter("id_cuenta_destino")));
			prestamo.setImporteApagar(importe_a_pagar);
			prestamo.setPlazo(plazo);
			prestamo.setMontoMensual(monto_mensual);
			prestamo.setEstado("Solicitado");
			
			PrestamoDao pDao = new PrestamoDao();
			filasAgregadas = pDao.agregarPrestamo(prestamo);
			
		}
		
		
	}
	
	
	private boolean validarCamposPrestamo(HttpServletRequest request) {
        return request.getParameter("importe_solicitado") != null && !request.getParameter("importe_solicitado").isEmpty() &&
               request.getParameter("cuotas") != null && !request.getParameter("cuotas").isEmpty() &&
               request.getParameter("id_cuenta_destino") != null && !request.getParameter("id_cuenta_destino").isEmpty();
    }

}
