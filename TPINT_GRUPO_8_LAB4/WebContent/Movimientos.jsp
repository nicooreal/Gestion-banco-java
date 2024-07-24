<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Movimiento"%>
<%@ page import="Dao.MovimientoDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movimientos Cuenta</title>
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
	            <h1>Movimientos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Movimientos de la cuenta</h2>
	            
	            <table>
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Fecha</th>
	                <th>Concepto</th>
	                <th>Importe</th>
	                <th>Tipo de movimiento</th>
	                <th>Cuenta origen Id</th>
	                <th>Cuenta destino Id</th>
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<Movimiento> listadoMovimientos = (ArrayList<Movimiento>)request.getAttribute("listadoMovimientosPorCuenta");
	            	if(listadoMovimientos != null) {
	            		for(Movimiento movimiento : listadoMovimientos) {
	            %>
	            		
	                <tr>
	                    <td><%= movimiento.getId() %></td>
	                    <td><%= movimiento.getFecha() %></td>
	                    <td><%= movimiento.getConcepto() %></td>
	                    <td><%= movimiento.getImporte() %></td>
	                    <td><%= movimiento.getTipoMovimiento().getDescripcion() %></td>  
	                    <td><%= movimiento.getId_cuenta_origen() %></td>
	                    <td><%= movimiento.getId_cuenta_destino() %> </td>
	                    
					    <td class"action-buttons" >
					    <div style="display:flex">
				            <input type="submit" value="VER COMPROBANTE (deco)" />
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