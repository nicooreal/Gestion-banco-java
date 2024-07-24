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

import Dao.MovimientoDao;
import Dao.PagoDao;
import Dominio.Movimiento;
import Dominio.Pago;
import Dominio.Usuario;

@WebServlet("/PortalPagosBancoServlet")
public class PortalPagosBancoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PortalPagosBancoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnPagarPrestamo")!=null) {
			generarListaDePago(request, response);
        }
		if (request.getParameter("btnEfectuarPago")!=null) {
			registrarPagoYDescontarDeCuenta(request, response);
			redirigirAPerfil(request,response);
        }
		if(request.getParameter("btnVerMovimientos")!=null) {
			redirigirAPerfil(request, response);
		}
	}
	
	protected void generarListaDePago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PagoDao pagoDao = new PagoDao();
		int idPrestamo = Integer.parseInt(request.getParameter("prestamoId"));
		ArrayList<Pago> listaDePagosPorPrestamo = new ArrayList<>();
		listaDePagosPorPrestamo = pagoDao.ListarPorIdPrestamo(idPrestamo);
		
		request.setAttribute("idPrestamo", idPrestamo);
		request.setAttribute("listaDePagosPorPrestamo", listaDePagosPorPrestamo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/PortalDePagosClientes.jsp");
        rd.forward(request, response);
	}
	
	protected void registrarPagoYDescontarDeCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PagoDao pDao = new PagoDao();
		Pago pago = new Pago();
		MovimientoDao mDao = new MovimientoDao();
		int idPagoReferido = Integer.parseInt(request.getParameter("idPagoReferido"));
		
		pago = pDao.buscarPorId(idPagoReferido);
		mDao.agregarPagoEfectuadoAMovimiento(pago);
		pDao.estadoPagoAPagado(idPagoReferido);
		
	}
	
	protected void redirigirAPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = null;
		HttpSession session = request.getSession();
		usuario = (Usuario) session.getAttribute("usuario");
		
		request.getSession().setAttribute("usuario", usuario);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/clientePerfilServlet");   
		requestDispatcher.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
