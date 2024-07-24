<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Dominio.Cuenta"%>
<%@ page import="Dao.CuentaDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>	
	</head>
	<body>

		<jsp:include page="navbarClientes.html"></jsp:include>
           <jsp:include page="ClienteNombreApellido.jsp"></jsp:include>
<label>       
<%= session.getAttribute("perfilClienteNombre") %>
</label>		
	    <div class="container">
	        <div class="header">
	            <h1>Préstamos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Solicitar Préstamo</h2>
	            <form action="ClientePrestamoServlet" method="post">
	                <label for="importe_solicitado">Monto solicitado:</label>
	                	<input type="number" id="importe_solicitado" name="importe_solicitado" required><br><br>
	                
	                <label for="cuotas">Cantidad de Cuotas:</label>
		                <select name="cuotas" id="cuotas">
		                	<option value="1">1</option>
			                <option value="3">3</option>
			                <option value="6">6</option>
		                </select><br><br>
	                
	                
	                <label for="id_cuenta_destino">Cuenta Destino:</label>
						<select name="id_cuenta_destino" id="id_cuenta_destino">
						<% if(request.getAttribute("listadoCuentas") != null){ 
							List<Cuenta> listadoCuentas =  (List<Cuenta>)request.getAttribute("listadoCuentas");
						for (Cuenta c : listadoCuentas){
							%>
						
						<option value="<%= c.getIdCuenta()  %>"><%= c.getCbu()%> " - " <%= c.getTipoCuenta().getTipoCuenta()%></option>
						
						<%}
						}%>
						</select> <br><br>
	                
	                <input type="submit" value="Confirmar Préstamo"  id="crearPrestamo" name="crearPrestamo">
	            </form>
	        </div>
	    </div>
	</body>
</html>