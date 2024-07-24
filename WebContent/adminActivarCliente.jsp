<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<style>
 {
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
	<h2>Administracion de Clientes</h2>

<h3>¿SEGURO QUE QUIERE ACTIVAR EL CLIENTE?</h3>


<%

String clienteId = request.getParameter("clienteId");
int idCliente = Integer.parseInt(clienteId);

if (clienteId != null && !clienteId.isEmpty()) {



    %>
    
    <p>ID del Cliente a ACTIVAT: <%= idCliente %></p>

<%}  else  {  

  %>
  <p>NO HAY NINGUN CLIENTE PARA ELIMINAR</p>

<%
}
%>

    <form action="adminClientesServlet" method="get">
        <input type="hidden" name="ClienteId" value="<%= idCliente %>" />
        <input type="submit" name="btnActivar" value="CONFIRMAR ACTIVACION " /><br>
        <a class="nav-link" aria-current="page" href="adminClientes.jsp">Cancelar</a>
    </form>



</body>
</html>