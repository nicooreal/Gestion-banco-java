<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Dominio.ClienteSaldo"%>
    <%@ page import="Dominio.Provincia"%>
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
    
    <h2>Administracion de Informes</h2>
    
    <div>
	    <form method="get" action="adminInformesServlet">
	    	<label>INFORME 1: Clientes con saldo mayor a: </label>
		    <input type="number" id="informe1" name="inputInforme1" pattern="\d+" title="solo números" required>
		   <label>  Fecha de inicio    </label>
		   <input type="text" id="inputFechaInicio1" name="inputFechaInicio1" pattern="\d{4}-\d{2}-\d{2}"  title="ingrese la fecha en el formato YYYY-MM-DD"required>
		   <label>  Fecha limite    </label>
		   <input type="text" id="inputFechaFin1" name="inputFechaFin1" pattern="\d{4}-\d{2}-\d{2}" title="ingrese la fecha en el formato YYYY-MM-DD" required>
		    <input type="submit" id="btnInforme1" name="btnInforme1" value="Buscar"> <!-- sp_saldo_por_cliente -->
		    <br>
		 </form>
		
		
		
		
		
		 <form method="get" action="adminInformesServlet">
		    <label>INFORME 2: Clientes con saldo menor a: </label>
		    <input type="number" id="informe2" name="inputInforme2" pattern="\d+" title="solo números" required>
		   		 		   <label>  Fecha de inicio    </label>
		   		   <input type="text" id="inputFechaInicio2" name="inputFechaInicio2" pattern="\d{4}-\d{2}-\d{2}" title="ingrese la fecha en el formato YYYY-MM-DD" required>
		 		   <label>  Fecha limite    </label>
		   <input type="text" id="inputFechaFin2" name="inputFechaFin2" pattern="\d{4}-\d{2}-\d{2}" title="ingrese la fecha en el formato YYYY-MM-DD" required>
		    <input type="submit" id="btnInforme2" name="btnInforme2" value="Buscar"> <!-- sp_saldo_por_cliente -->
		    <br>
		 </form>
		




<form method="get" action="adminInformesServlet">
		    <label>INFORME 3: Cantidad de clientes por provincias: </label>
		    <input type="submit" id="btnInforme3" name="btnInforme3" value="Ver"> <!-- vw_clientes_por_provincia -->
		    <br>
		    <a href="adminInformes.jsp">LIMPIAR</a>
	    </form>
    </div>
    
		    <!-- TABLA PARA MOSTRAR INFORME 1 Y 2 -->
		    <%  ArrayList<ClienteSaldo> listadoClientesPorSaldo = (ArrayList<ClienteSaldo>)request.getAttribute("listadoClientesPorSaldo");
		    if(listadoClientesPorSaldo != null){ %>
<table border="1" id="table_id" class="display">
	        <thead>
	            <tr>
	                <th>DNI</th>
	                <th>Nombre</th>
	                <th>Cuil</th>
	                <th>Saldo</th>
	                <th>Estado</th>
	            </tr>
	        </thead>
		<tbody>
		    	<% for(ClienteSaldo cs : listadoClientesPorSaldo){ %>
			<tr>
				<td><%=cs.getCliente().getDni() %> </td>
				<td><%=cs.getCliente().getNombre() %> </td>
				<td><%=cs.getCliente().getCuil() %> </td>
				<td><%=cs.getSaldoTotal() %> </td>
				<td><%=cs.getCliente().getEstado() %> </td>    
		    </tr>
		    <% } %>
		</tbody>
	</table>
		   <%}%>
		   <!-- HASTA ACÁ TABLA PARA MOSTRAR INFORME 1 Y 2 -->
		   
		   
		   <!-- TABLA PARA MOSTRAR INFORME 3 -->
		    <%  ArrayList<Provincia> listadoProvincias = (ArrayList<Provincia>)request.getAttribute("listadoProvincias");
		    if(listadoProvincias != null){ %>
    <table>
	        <thead>
	            <tr>
	                <th>Provincia</th>
	                <th>Cantidad de Clientes</th>
	            </tr>
	        </thead>
		<tbody>
		    	<% for(Provincia p : listadoProvincias){ %>
			<tr>
				<td><%=p.getNombre_provincia() %> </td>
				<td><%=p.getCantidad_clientes() %> </td>

		    </tr>
		    <% } %>
		</tbody>
	</table>
		   <%}%>
		   <!-- HASTA ACÁ TABLA PARA MOSTRAR INFORME 3 -->
	
</body>
</html>