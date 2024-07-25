<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Dominio.Cuenta"%>
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
        .info-section {
            margin-bottom: 20px;
        }
        .info-section h2 {
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .info-section p {
            margin: 10px 0;
        }
        .info-section table {
            width: 100%;
            border-collapse: collapse;
        }
        .info-section table, .info-section th, .info-section td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .info-section th {
            background-color: #f2f2f2;
        }
        table {
		   width: 100%;
		   border: 1px solid #000;
		}
		th, td {
		   width: 15%;
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
    


	<h2>Administracion de Cuentas</h2>
	<form action="adminCuentasServlet" method="get">
		<input type="submit" value="Mostrar Listado completo" id="botonMostrar" name="botonMostrar">
	  
	</form>
	
		<form action="adminCuentasServlet" method="get">
			<input type="submit" id="botonAgregar" name="botonAgregar" value="Agregar Cuenta">
		</form>
		
		
	<table border="1" id="table_id" class="display">
	        <thead>
	            <tr>
	                <th>ID Cuenta</th>
	                <th>Cliente</th>
	                <th>Numero de cuenta</th>
	                <th>Fecha</th>
	                <th>Tipo de cuenta</th>
	                <th>CBU</th>
	                <th>Saldo</th>
	                <th>Estado</th>
                    <th></th>

	            </tr>
	        </thead>
<tbody>

<% 
	            ArrayList<Cuenta> listadoCuentas = (ArrayList<Cuenta>)request.getAttribute("listadoCuentas");
	            	if(listadoCuentas != null) {
	            		for(Cuenta cuenta : listadoCuentas) {
	            %>
	            		
	                <tr>
	                    <td><%= cuenta.getIdCuenta()  %></td>
	                    <td><%= cuenta.getCliente().getApellido()%>  <%= cuenta.getCliente().getNombre()%></td>
	                   <td><%= cuenta.getNumeroCuenta()  %></td>
	                    <td><%= cuenta.getFecha()%></td>
	                    <td><%= cuenta.getTipoCuenta().toString()%></td>
	                    <td><%= cuenta.getCbu()%></td>
	                    <td>$<%= cuenta.getSaldo()%></td>
	                    <td><%= cuenta.getEstado()%></td>

                 
    <td class"action-buttons" >
    <div style="display:flex">
      
       <% if (cuenta.getEstado() == Cuenta.ESTADO.True ) {%>
    	   
    	   
    	   
      
      
        <form action="adminEliminarCuenta.jsp" method="post">
            <input type="hidden" name="cuentaId" value="<%= cuenta.getIdCuenta() %>" />
            <input type="submit" name="btnEliminar" value="ELIMINAR" />
        </form>
       
       <%}  else {%>
       
               <form action="adminActivarCuenta.jsp" method="get">
        	<input type="hidden" name="cuentaId" value="<%= cuenta.getIdCuenta() %>" />
            <input type="submit" name="btnModificar" value="ACTIVAR " />
        </form>

       
     <%}%> 
       
        <form action="adminCrearCuenta.jsp" method="get">
        	<input type="hidden" name="cuentaId" value="<%= cuenta.getIdCuenta() %>" />
            <input type="submit" name="btnModificar" value="MODIFICAR" />
        </form>
    </div>
    </td>
</tr>	
				
				
				
				<% }
	            			
				} %>
                 
                 
                 
                 
                 
                 
           
   
   



	        </tbody>
	    </table>










</body>






















</html>