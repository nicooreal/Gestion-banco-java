<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Cliente"%>
<%@ page import="Dominio.Usuario"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<link rel="stylesheet" type="text/css" href="css/adminClientes.css"/>
	</head>
	<body>
	
         <% 
         	Usuario usuario = (Usuario)session.getAttribute("usuario");
         	if(usuario == null) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");   
				requestDispatcher.forward(request, response);
         	}
         %>
	
	     
	  
	</body>
</html>