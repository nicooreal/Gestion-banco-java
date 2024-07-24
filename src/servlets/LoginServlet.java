package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UsuarioDao;
import Dominio.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsuarioDao usuarioDao ;
		Usuario usuario = null;

		try
		{
	        String usuarioAux = request.getParameter("username");
	        String contraseniaAux = request.getParameter("password");
			usuarioDao = new UsuarioDao();
			usuario = usuarioDao.Validar(usuarioAux, contraseniaAux);
			
			
					if( usuario.getId() == 0) {
						
						System.out.println("Se fue por usuario nulo");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorSesion.jsp");
						requestDispatcher.forward(request, response);
					}
					
			
			        if (usuario.getAcceso() == "Administrador") {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/PortalAdministradores.jsp");   
						requestDispatcher.forward(request, response);	
					}
			
					if (usuario.getAcceso() == "Cliente") {
						request.getSession().setAttribute("usuario", usuario);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/clientePerfilServlet");   
						requestDispatcher.forward(request, response);
					}
			
		
					if (usuario != null && usuario.getId() != 0) {
					    HttpSession session = request.getSession();
					    session.setAttribute("usuario", usuario);
					    // Redirecciona según el tipo de acceso...
					}
		
		
		
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorSesion.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
