<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Prestamo"%>
<%@ page import="Dao.PrestamoDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#table_id').DataTable();
});
</script>

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
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
	            <h1>Pago de Préstamos</h1>
	        </div>
	
	        <div class="info-section">
	            <h2>Prestamos Pendientes</h2>
	            
	            <table  border="1" id="table_id" class="display">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>ID Cliente</th>
	                <th>Fecha de solicitud</th>
	                <th>Importe solicitado</th>
	                <th>Importe a pagar</th>
	                <th>Cantidad de Cuotas</th>
	                <th>Plazo restante</th>
	                <th>Monto Mensual</th>
	                <th>Estado</th>
	                <th>Opciones</th>
	                
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<Prestamo> listadoPrestamos = (ArrayList<Prestamo>)request.getAttribute("listadoPrestamosPorCliente");
	            	if(listadoPrestamos != null) {
	            		for(Prestamo prestamo : listadoPrestamos) {
	            %>
	            		
	                <tr>
	                    <td><%= prestamo.getIdPrestamo() %></td>
	                    <td><%= prestamo.getCliente().getId() %></td>
	                    <td><%= prestamo.getFechaAlta().toString() %></td>
	                    <td><%= prestamo.getImporteSolicitado() %></td>
	                    <td><%= prestamo.getImporteApagar() %></td>
	                    <td><%= prestamo.getCuotas() %></td>
	                    <td><%= prestamo.getPlazo() %> Dias</td>
	                    <td><%= prestamo.getMontoMensual() %></td>
	                    <td><%= prestamo.getEstado().name() %></td>
	                    
	                    <td class"action-buttons" >
	                    
	                    <% if(prestamo.getEstado().name() == "Aprobado"){%>
					    <div style="display:flex">
					        <form action="PortalPagosBancoServlet" method="get">
					        	<input type="hidden" name="prestamoId" value="<%= prestamo.getIdPrestamo() %>" />
					            <input type="submit" name="btnPagarPrestamo" value="PAGAR" />
					        </form>
					    </div>
					    
					    
	                    <%}%>
	                    
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