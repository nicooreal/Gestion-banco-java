package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CuentaDao;
import Dominio.Cuenta;
import Dominio.Usuario;

/**
 * Servlet implementation class clientePerfilServlet
 */
@WebServlet("/clientePerfilServlet")
public class clientePerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public clientePerfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("crearPrestamo")!=null) {
			String mensajePrestamo = "Prestamo solicitado exitosamente.";
			request.setAttribute("mensajePrestamo", mensajePrestamo);
		}
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");

		
		request.getSession().setAttribute("perfilClienteNombre", usuario.getCliente().getNombre() +" " + usuario.getCliente().getApellido());
		request.getSession().setAttribute("perfilClienteEmail", usuario.getCliente().getCorreoElectronico());
		request.getSession().setAttribute("perfilClienteTelefono", usuario.getCliente().getTelefono1().getTelefono());
		request.getSession().setAttribute("perfilClienteDireccion", usuario.getCliente().getDireccion());
		request.getSession().setAttribute("perfilClienteFechaNacimiento", usuario.getCliente().getFechaNacimiento().toString());
		request.getSession().setAttribute("perfilClienteCUIT", usuario.getCliente().getCuil());
		
		//Listar cuentas x cliente
		CuentaDao cDao = new CuentaDao();
        List<Cuenta> listadoCuentas = cDao.getListaCuentasPorCliente(usuario.getCliente().getId());
        request.setAttribute("listadoCuentas", listadoCuentas);
		
        //dispatcher
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("PortalClientes.jsp"); 
		requestDispatcher.forward(request, response);
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
