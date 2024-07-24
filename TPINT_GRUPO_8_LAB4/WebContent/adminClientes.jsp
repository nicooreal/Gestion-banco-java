<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Dominio.Cliente"%>
    <%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
        }
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
	<h2>Administracion de Clientes</h2>
	<form action="adminClientesServlet" method="get">
		<input type="submit" value="Mostrar Listado completo" id="botonMostrar" name="botonMostrar">
	    <input type="submit" value="Mostrar Clientes eliminados" id="botonMostrarEliminados" name="botonMostrarEliminados">
	     <input type="submit" value="Mostrar Clientes activados" id="botonMostrarActivados" name="botonMostrarActivados">
	</form>
		<a id="botonAgregar" name="botonAgregar" href="adminClientesServlet?botonAgregar=true">Agregar</a>
	<table>
	        <thead>
	            <tr>
	                <th>ID Cliente</th>
	                <th>DNI</th>
	                <th>CUIL</th>
	                <th>Nombre</th>
	                <th>Apellido</th>
	                <th>Telefono 1</th>
	                <th>Telefono 2</th>
	                <th>Sexo</th>
	                <th>Nacionalidad</th>
	                <th>Fecha Nacimiento</th>
	                <th>Direccion</th>
	                <th>Localidad</th>
	                <th>Provincia</th>
	                <th>E-mail</th>
	                <th>Estado</th>
	            </tr>
	        </thead>
	        <tbody>
	            <% 
	            ArrayList<Cliente> listadoClientes = (ArrayList<Cliente>)request.getAttribute("listadoClientes");
	            	if(listadoClientes != null) {
	            		for(Cliente cliente : listadoClientes) {
	            %>
	            		
	                <tr>
	                    <td><%= cliente.getId()%></td>
	                    <td><%= cliente.getDni()%></td>
	                    <td><%= cliente.getCuil()%></td>
	                    <td><%= cliente.getNombre()%></td>
	                    <td><%= cliente.getApellido()%></td>
	                    <td><%= cliente.getTelefono1().getTelefono()   %></td>
	                    <td><%= cliente.getTelefono2().getTelefono()   %></td>
	                    <td><%= cliente.getSexo()%></td>
	                    <td><%= cliente.getNacionalidad().getNombre()%></td>
	                    <td><%= cliente.getFechaNacimiento().toString()%></td>
	                    <td><%= cliente.getDireccion()%></td>
	                    <td><%= cliente.getLocalidad().getNombre() %></td>
	                    <td><%= cliente.getProvincia().getNombre_provincia() %></td>
	                    <td><%= cliente.getCorreoElectronico()%></td>
	                    <td><%= cliente.getEstado()%></td>
	                

    <td class"action-buttons" >
    <div style="display:flex">
      
       <% if (cliente.getEstado() == Cliente.ESTADO.True ) {%>
    	   
    	   
    	   
      
      
        <form action="adminEliminarCliente.jsp" method="post">
            <input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
            <input type="submit" name="btnEliminar" value="ELIMINAR" />
        </form>
       
       <%}  else {%>
       
       
               <form action="adminActivarCliente.jsp" method="post">
            <input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
            <input type="submit" name="btnActivar" value="ACTIVAR" />
        </form>
       
     <%}%> 
       
        <form action="adminClientesServlet" method="get">
        	<input type="hidden" name="clienteId" value="<%= cliente.getId() %>" />
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