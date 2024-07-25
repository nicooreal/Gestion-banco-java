<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.*" %>
<%@ page import="Dominio.Cuenta"%>
<%@ page import="Dominio.Cliente"%>
<%@ page import="Dao.CuentaDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
   <style>
          .form-container {
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            width: 800px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group input[type="text"]:focus {
            border-color: #007BFF;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
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
   
.btn {
        display: inline-block;
        font-weight: 400;
        text-align: center;
        white-space: nowrap;
        vertical-align: middle;
        user-select: none;
        border: 1px solid transparent;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        border-radius: 0.25rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        margin-top: 10px;
    }
.btn-primary {
    color: #fff;
    background-color: #007BFF;
    border-color: #007BFF;
}

.btn-primary:hover {
    color: #fff;
    background-color: #0056b3;
    border-color: #004085;
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
	<h3>Datos de la cuenta</h3>
	<form method="get" action="adminCuentasServlet">
	
		<%

			Cuenta cuentaAux = (Cuenta) request.getAttribute("cuentaAModificar");
		
		%>
	          
	       <div class="form-container">
           <div class="form-group">          

			<label> 
			   <% if (cuentaAux != null) { %> 
                        ID CUENTA: <%= cuentaAux.getIdCuenta() %> 
                    <% } else { %> 
                        ID CUENTA NUEVA: <%= request.getAttribute("idProximoCrear") %>  
               <% } %> 
			</label>
		
		
		<br>
		<label>ID Cliente</label>
		
		
		 <select name="idCliente" id="idCliente">
		 	<% if(cuentaAux != null){%>
		 		<option value="<%= cuentaAux.getCliente().getId()  %>" selected><%= cuentaAux.getCliente().getId() %></option>
		 	<% }
		 	   if (request.getAttribute("listadoIdClientes") != null && cuentaAux == null){
			 		ArrayList<Cliente> listadoIdClientes =  (ArrayList<Cliente>)request.getAttribute("listadoIdClientes");
			 		for (Cliente c : listadoIdClientes){   %>
			 			<option value="<%= c.getId() %>">
			               ID - Nombre: <%= c.getId() %> - <%= c.getNombre() %> <%= c.getApellido() %>
			            </option>
			 	 <% }
		 	   } %>
		 </select>
		 
		<br>
		<label>Tipo De Cuenta</label>


            <select name="tipoCuenta" id="tipoCuenta">
            	<% if(cuentaAux != null){%>
            		<% if(cuentaAux.getTipoCuenta().getTipoCuenta().equals("Caja Ahorro")){ %>
            	 		<option value="Caja Ahorro" selected >Caja de Ahorro</option>
            	 		<option value="Cuenta Corrriente" >Cuenta Corrriente</option>
            	 	<% }else if (cuentaAux.getTipoCuenta().getTipoCuenta().equals("Cuenta Corriente")){ %>   
            	 		<option value="Cuenta Corrriente" selected >Cuenta Corrriente</option>
            	 		<option value="Caja Ahorro" >Caja de Ahorro</option>
            	 	<% } %>
            	<% }else{ %>
	            	 <option value="Caja Ahorro" selected >Caja de Ahorro</option>
	            	 <option value="Cuenta Corrriente" >Cuenta Corrriente</option>
            	<% } %>
            </select>
            
		
		<br>
		<label>CBU</label>
        <input type="text" name="cbu" id="cbu" <% if (cuentaAux != null)  { %> value="<%= cuentaAux.getCbu() %>" readonly <% } else { %> value="<%= request.getAttribute("CbuProximoACrear")  %>"readonly <% } %> />
		<br>
		<label>Saldo</label>
		<input type="text" name="saldo" id="saldo" pattern="\d+([.,]\d{1,2})?" title="solo números" <% if (cuentaAux != null) { %> value="<%= cuentaAux.getSaldo() %>" <% } else { %> value="<%=10000 %>"readonly <% }  %>>

        <input type="hidden" name="fecha"   <% if (cuentaAux != null) { %>        value="<%= cuentaAux.getFecha() %>"   <% } %>  />
        <input type="hidden" name="estado"  <% if (cuentaAux != null) { %>        value="<%= cuentaAux.getEstado() %>"  <% } %>  />
		<input type="hidden" name="numeroCuenta"  <% if (cuentaAux != null) { %>  value="<%= cuentaAux.getNumeroCuenta() %>"  <% } %>    />
		<input type="hidden" name="idCuenta"    <% if (cuentaAux != null) { %>  value="<%= cuentaAux.getIdCuenta() %>"    <% } %>            />
		
		</div>
		
		</div>
		
		<%
		if(cuentaAux != null){
			
		%>
		<button type="submit" class="btn btn-primary" name="btnModificarCuenta" id="modificarCuenta">Modificar</button>
		<input type="hidden" name="idModificar" value="<%= cuentaAux.getIdCuenta() %>" />
		<%}
		    else { %>
		
		<button type="submit" class="btn btn-primary" name="SubmitCuenta" id="SubmitCuenta">Crear Cuenta</button>
		
		<% } %>
	
	</form>
</body>
</html>