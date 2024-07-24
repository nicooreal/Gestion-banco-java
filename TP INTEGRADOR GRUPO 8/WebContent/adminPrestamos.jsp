<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="Dominio.Prestamo"%>
    <%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title>Portal bancario UTN</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }
        .container {
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            background-color: #f2f2f2;
            padding: 20px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        .navbar {
            overflow: hidden;
            background-color: #333;
            margin-bottom: 20px;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

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
                  .navbar .right {
            float: right;
    </style>
</head>
<body>

	<h1>Portal Administradores</h1>

<div class="navbar">
    <a href="PortalAdministradores.jsp">Inicio</a>
    <a href="adminClientesServlet">Clientes</a>
    <a href="adminCuentasServlet">Cuentas</a>
    <a href="adminPrestamosServlet">Prestamos</a>
    <a href="adminInformes.jsp">Informes</a>
    <a href="Login.jsp" class="right">Salir de sesion</a>
</div>
    
    <h2>Administracion de Prestamos</h2>



<form action="adminPrestamosServlet" method="get">
		<input type="submit" value="Mostrar Listado completo" id="botonMostrar" name="botonMostrar">
	  
	</form>

	<table>
	        <thead>
	            <tr>
	                <th>ID Prestamo</th>
	                <th>Fecha solicitud</th>
	                <th>Cliente</th>
	                <th>Importe a pagar</th>
	                <th>Importe solicitado</th>
	                <th>Plazo</th>
	                <th>Monto mensual</th>
                    <th>Cuotas</th>
                    <th>Id cuenta destino</th>
                    <th>Estado</th>



	            </tr>
	        </thead>
          <tbody>

<% 
	            ArrayList<Prestamo> listadoPrestamos = (ArrayList<Prestamo>)request.getAttribute("listadoPrestamos");
	            	if(listadoPrestamos != null) {
	            		for(Prestamo prestamo : listadoPrestamos) {
	            %>
	            		
	                <tr>
	                    <td><%= prestamo.getIdPrestamo()  %></td>
	                    <td><%= prestamo.getFechaAlta()  %>  </td>
	                    <td><%= prestamo.getCliente().getNombre() %>  <%= prestamo.getCliente().getApellido() %> </td>
	                    <td><%= prestamo.getImporteApagar()%></td>
	                    <td><%= prestamo.getImporteSolicitado()%></td>
	                    <td><%= prestamo.getPlazo()%></td>
	                    <td><%= prestamo.getMontoMensual()%></td>
                        <td><%= prestamo.getCuotas()%></td>
                        <td><%= prestamo.getIdCuentaDestino()%></td>
                        <td><%= prestamo.getEstado()%></td>

    <td class"action-buttons" >
    <div style="display:flex">
      
       <% if (prestamo.getEstado().name() == "Solicitado") {%>
        <form action="adminPrestamosServlet" method="post">
            <input type="hidden" name="prestamoId" value="<%= prestamo.getIdPrestamo() %>" />
            <input type="submit" name="btnRechazar" value="RECHAZAR" />
        </form>
        
       <form action="adminPrestamosServlet" method="post">
            <input type="hidden" name="prestamoId" value="<%= prestamo.getIdPrestamo() %>" />
            <input type="hidden" name="idCuentaDestino" value="<%= prestamo.getIdCuentaDestino() %>" />
            <input type="submit" name="btnAprobar" value="APROBAR" />
        </form>
      <%}%>
        
        
          


        <% if (prestamo.getEstado().name() == "Rechazado") {%>
   
<!-- 
          <form action="adminPrestamosServlet" method="post">
            <input type="hidden" name="prestamoId" value=" prestamo.getIdPrestamo()" />
            <input type="submit" name="" value="" />
        </form>
  -->   
              <%}%>
        
        
        
        
        
               <% if (prestamo.getEstado().name() == "Finalizado") {%>
  <!--  
          <form action="adminPrestamosServlet" method="post">
            <input type="hidden" name="prestamoId" value="prestamo.getIdPrestamo()" />
            <input type="submit" name="" value="" />
        </form>
  -->   
              <%}%> 
        
        
        
        
        
                     <% if (prestamo.getEstado().name() == "Aprobado") {%>
<!--    
          <form action="adminPrestamosServlet" method="post">
            <input type="hidden" name="prestamoId" value=" prestamo.getIdPrestamo() " />
            <input type="submit" name="" value="" />
        </form>
  -->   
              <%}%>   
        
        
        
        
        
        
            
                 
            </tr>
   



				<% }
	            			
				} %>







	     </tbody>
	    </table>





</body>
</html>