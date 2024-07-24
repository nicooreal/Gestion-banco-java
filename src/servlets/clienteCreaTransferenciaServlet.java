package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CuentaDao;
import Dao.UsuarioDao;
import Dominio.Cuenta;
import Dominio.Usuario;

/**
 * Servlet implementation class clienteCreaTransferencia
 */
@WebServlet("/clienteCreaTransferenciaServlet")
public class clienteCreaTransferenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public clienteCreaTransferenciaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("action").equals("RealizarTransferencia")) {
			CuentaDao cDao = new CuentaDao();
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			
			
			Usuario usuario = null;
			HttpSession session = request.getSession();
			usuario = (Usuario) session.getAttribute("usuario");
			
			listaCuentas = usuario.getCliente().getCuentas();
			
			request.setAttribute("listaCuentas", listaCuentas);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/clienteTransferencias.jsp"); 
	        requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
