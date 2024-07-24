package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CuentaDao;

/**
 * Servlet implementation class Transferencias
 */
@WebServlet("/TransferenciasServlet")
public class TransferenciasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public TransferenciasServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cbuOrigen = request.getParameter("cbuOrigen");
        String cbuDestino = request.getParameter("cbuDestino");
        double monto = Double.parseDouble(request.getParameter("monto"));
        CuentaDao cuentaDao = new CuentaDao();
        try {
            cuentaDao.realizarTransferencia(cbuOrigen, cbuDestino, monto);
            response.sendRedirect("ExitosaTransferencia.jsp"); // Página de éxito
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ErrorTransferencia.jsp"); // Página de error
        }
	}

}
