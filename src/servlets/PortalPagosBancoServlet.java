package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.PagoDao;
import Dominio.Pago;

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
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
