package servlets;

import java.io.IOException;
import java.sql.Date;
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
import Dao.ClienteSaldoDao;
import Dao.ProvinciaDao;
import Dominio.ClienteSaldo;
import Dominio.Provincia;

/**
 * Servlet implementation class adminInformesServlet
 */
@WebServlet("/adminInformesServlet")
public class adminInformesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminInformesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		if(request.getParameter("btnInforme1")!= null) {
          
           
			
			java.util.Date fechaInicio = null;
			java.util.Date fechaLimite = null;
			
			
            try {
                fechaInicio = formatoFecha.parse(request.getParameter("inputFechaInicio1"));
                fechaLimite = formatoFecha.parse(request.getParameter("inputFechaFin1"));
          
            
            } catch (ParseException e) {
                e.printStackTrace();
            }

			
      float num =  Float.parseFloat(request.getParameter("inputInforme1"));
			
			mayorSaldoA(request, response, num, fechaInicio, fechaLimite );
		
		
		
		} else if(request.getParameter("btnInforme2")!= null) {
			
			java.util.Date fechaInicio = null;
			java.util.Date fechaLimite = null;
			
			
            try {
                fechaInicio = formatoFecha.parse(request.getParameter("inputFechaInicio2"));
                fechaLimite = formatoFecha.parse(request.getParameter("inputFechaFin2"));
          
            
            } catch (ParseException e) {
                e.printStackTrace();
            }
			
            float num =  Float.parseFloat(request.getParameter("inputInforme2"));
            
            menorSaldoA(request, response,num,fechaInicio,fechaLimite);
		
		
		
		
		
		
		
		
		
		
		} else if(request.getParameter("btnInforme3")!= null) {
			clientesPorProvincia(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void mayorSaldoA (HttpServletRequest request, HttpServletResponse response, float num, java.util.Date fechaInicio, java.util.Date fechaLimite ) throws ServletException, IOException {
		ClienteSaldoDao csDao = new ClienteSaldoDao();
		ArrayList<ClienteSaldo> listadoClientesPorSaldo = new ArrayList <ClienteSaldo>();
		listadoClientesPorSaldo = csDao.obtenerClientesConSaldoMayor(num, true, fechaInicio, fechaLimite);
		request.setAttribute("listadoClientesPorSaldo", listadoClientesPorSaldo);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminInformes.jsp");   
		requestDispatcher.forward(request, response);
		
	}

	private void menorSaldoA (HttpServletRequest request, HttpServletResponse response, float num, java.util.Date fechaInicio, java.util.Date fechaLimite) throws ServletException, IOException {
		ClienteSaldoDao csDao = new ClienteSaldoDao();
		ArrayList<ClienteSaldo> listadoClientesPorSaldo = new ArrayList <ClienteSaldo>();
		listadoClientesPorSaldo = csDao.obtenerClientesConSaldoMayor(num, false, fechaInicio, fechaLimite);
		request.setAttribute("listadoClientesPorSaldo", listadoClientesPorSaldo);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminInformes.jsp");   
		requestDispatcher.forward(request, response);
		
	}

	private void clientesPorProvincia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProvinciaDao pDao = new ProvinciaDao();
		ArrayList<Provincia> listadoProvincias = (ArrayList<Provincia>) pDao.getListaProvinciasConCantidadDeClientes();
		request.setAttribute("listadoProvincias", listadoProvincias);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminInformes.jsp");   
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
