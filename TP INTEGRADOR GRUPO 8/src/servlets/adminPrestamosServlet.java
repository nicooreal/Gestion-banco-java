package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Prestamo;
import Dao.PrestamoDao;;
/**
 * Servlet implementation class adminPrestamosServlet
 */
@WebServlet("/adminPrestamosServlet")
public class adminPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminPrestamosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mostrarPrestamos(request,response);	
		if (request.getParameter("btnRechazar") != null)	{
	        int idParaRechazar = Integer.parseInt(request.getParameter("prestamoId"));
	        PrestamoDao preDao = new PrestamoDao();
	        int baja = preDao.rechazarPrestamo(idParaRechazar);
	        if (baja == 1) {
	            RequestDispatcher rd = request.getRequestDispatcher("adminPrestamos.jsp");
	            rd.forward(request, response);
	        }
		}
		if (request.getParameter("btnAprobar") != null)	{
			aprobarPrestamo(request,response);
		} 
		//PrestamoDao pDao = new PrestamoDao();
		//pDao.finalizarPrestamo();
		
	}
	
	private void aprobarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idParaAprobar = Integer.parseInt(request.getParameter("prestamoId"));
	        int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
	        PrestamoDao preDao = new PrestamoDao();
	        preDao.aprobarPrestamo(idParaAprobar, idCuentaDestino);
	}


    private void mostrarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Prestamo> listadoPrestamos = new ArrayList<>();
        try {
            PrestamoDao cd = new PrestamoDao();
            listadoPrestamos = cd.Listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
		request.setAttribute("listadoPrestamos", listadoPrestamos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminPrestamos.jsp");   
		requestDispatcher.forward(request, response);	
    }
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}











}
