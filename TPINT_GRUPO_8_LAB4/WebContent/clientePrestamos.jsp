<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	    <div class="container">
	        <div class="header">
	            <h1>Préstamos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Solicitar Préstamo</h2>
	            <form action="PrestamosServlet" method="post">
	                <label for="monto">Monto:</label>
	                <input type="number" id="monto" name="monto" required><br><br>
	                
	                <label for="cuotas">Cantidad de Cuotas:</label>
	                <select name="cuotas" id="cuotas">
	                    <option value="12">12</option>
	                    <option value="24">24</option>
	                    <option value="36">36</option>
	                </select><br><br>
	                
	                <label for="cuentaDestino">Cuenta Destino:</label>
	                <select name="cuentaDestino" id="cuentaDestino">
	                    <option value="cuenta1">Cuenta 1</option>
	                    <option value="cuenta2">Cuenta 2</option>
	                    <!-- Agrega más cuentas si es necesario -->
	                </select><br><br>
	                
	                <input type="submit" value="Solicitar Préstamo">
	            </form>
	        </div>
	    </div>
	</body>
</html>