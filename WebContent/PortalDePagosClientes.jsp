<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Pago"%>
<%@ page import="Dao.PagoDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Pagos</title>
		<link rel="stylesheet" type="text/css" href="css/navbar.css"/>	
		<style>
		table {
		   width: 100%;
		   border: 1px solid #000;
		
		}
		th, td {
		   width: 25%;
		   text-align: center;
		   vertical-align: top;
		   border: 1px solid #000;
		   border-spacing: 0;
		   border-collapse: collapse;
		   background: #fff;
		   color: #000;
		}
		
		caption {
		   padding: 0.3em;
		}        
    </style>
	</head>
	<body>

		<jsp:include page="navbarClientes.html"></jsp:include>
           <jsp:include page="ClienteNombreApellido.jsp"></jsp:include>
	    <div class="container">
	        <div class="header">
	            <h1>Pagos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Pago para el Prestamo: <%= request.getAttribute("idPrestamo") %> </h2>
	            
	            <table>
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Fecha</th>
	                <th>Importe</th>
	                <th>Fecha de Vencimiento</th>
	                <th>ID Prestamo Vinculado</th>
	                <th>ID Cuenta afectada</th>
	                
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<Pago> listadoPagos = (ArrayList<Pago>)request.getAttribute("listaDePagosPorPrestamo");
	            	if(listadoPagos != null) {
	            		for(Pago pago : listadoPagos) {
	            %>
	            		
	                <tr>
	                    <td><%= pago.getId() %></td>
	                    <td> <%if(pago.getFecha()!=null){ %> <%= pago.getFecha() %>   <%}else{ %>N/A<%} %> </td>
	                    <td><%= pago.getImporteApagar() %></td>
	                    <td><%= pago.getFechaVencimiento() %></td>  
	                    <td><%= pago.getIdPrestamo() %></td>
	                    <td><%= pago.getIdCuenta() %></td>
	                    
					    <td class"action-buttons" >
					    <div style="display:flex">
				            <input type="submit" value="Efectuar Pago (deco?)" />
					    </div>
					    </td>
					</tr>	
				<% }
	            			
				} %>

	        </tbody>
	    </table>
	            
	            
	            
	            
	        </div>
	    </div>
	</body>
</html>