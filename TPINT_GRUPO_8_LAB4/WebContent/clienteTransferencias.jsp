<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Dominio.Cuenta"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Transferencia de Dinero</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Transferencia de Dinero</h1>
        <form action="TransferenciasServlet" method="post">
            <div class="form-group">
                <label for="cbuOrigen">CBU de Origen:</label>
				<select name="cbuOrigen" id="cbuOrigen">
				<% if(request.getAttribute("listaCuentas") != null){ 
					ArrayList<Cuenta> listadoCuentas =  (ArrayList<Cuenta>)request.getAttribute("listaCuentas");
				for (Cuenta c : listadoCuentas){
					%>
				
				<option value="<%= c.getCbu() %>"><%= c.getCbu() %></option>
				
				<%}
				}%>
				</select>
		
		
		
            </div>
            <div class="form-group">
                <label for="cbuDestino">CBU de Destino:</label>
                <input type="text" class="form-control" id="cbuDestino" name="cbuDestino" required>
            </div>
            <div class="form-group">
                <label for="monto">Monto:</label>
                <input type="number" class="form-control" id="monto" name="monto" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Realizar Transferencia</button>
        </form>
    </div>
</body>
</html>