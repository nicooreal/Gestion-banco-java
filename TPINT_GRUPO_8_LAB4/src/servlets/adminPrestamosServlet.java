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

@WebServlet("/adminPrestamosServlet")
public class adminPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public adminPrestamosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Maneja las solicitudes para aprobar o rechazar préstamos
        if (request.getParameter("btnRechazar") != null) {
            manejarRechazoPrestamo(request);
        } else if (request.getParameter("btnAprobar") != null) {
            manejarAprobacionPrestamo(request);
        }
        
        // Mostrar la lista actualizada de préstamos
        mostrarPrestamos(request, response);
		
		
	}
	
	private void manejarRechazoPrestamo(HttpServletRequest request) {
        try {
            int idParaRechazar = Integer.parseInt(request.getParameter("prestamoId"));
            PrestamoDao preDao = new PrestamoDao();
            preDao.rechazarPrestamo(idParaRechazar);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    private void manejarAprobacionPrestamo(HttpServletRequest request) {
        try {
            int idParaAprobar = Integer.parseInt(request.getParameter("prestamoId"));
            int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
            PrestamoDao preDao = new PrestamoDao();
            preDao.aprobarPrestamo(idParaAprobar, idCuentaDestino);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Manejo de errores
        }
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

	
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}











}
