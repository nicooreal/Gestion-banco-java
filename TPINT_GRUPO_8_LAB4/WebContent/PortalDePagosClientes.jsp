<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Dominio.Pago"%>
<%@ page import="Dao.PagoDao"%>
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
	            <h2> Pago para el Prestamo: <%= request.getAttribute("idPrestamo") %> </h2>
	            
	            <table  border="1" id="table_id" class="display">
	        <thead>
	            <tr>
	                <th> ID </th>
	                <th> Fecha </th>
	                <th> Importe </th>
	                <th> Fecha de Vencimiento </th>
	                <th> ID Prestamo Vinculado </th>
	                <th> ID Cuenta afectada </th>
	                <th> Opciones </th>
	                
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
	                    
	                    <% if(pago.getEstado() == Pago.ESTADO.ATiempo) { %>
	                    
					    <td class"action-buttons" >
					    <div style="display:flex">
				            <form action="PortalPagosBancoServlet" method="get">
				            	<input type="hidden" value="<%= pago.getId() %>" name="idPagoReferido" />
				            	<input type="hidden" value="<%= request.getAttribute("idPrestamo") %>" name="idPrestamo" />
				            	<input type="submit" value="Efectuar Pago" id="btnEfectuarPago" name="btnEfectuarPago" />
							</form>
					    </div>
					    </td>
					    
					   <% }if (pago.getEstado() == Pago.ESTADO.Pagado) {%>
					   
       					<td class"action-buttons" >
						    <div style="display:flex">
				               	<form action="PortalPagosBancoServlet" method="get">
						            <input type="submit" name="btnVerMovimientos" value="Ver Movimiento" />
						        </form>
							</div>
					    </td>
				       
				     <%}%> 
       
					</tr>	
				<% }
	            			
				} %>

	        </tbody>
	    </table>
	            
	            
	            
	            
	        </div>
	    </div>
	</body>
</html>