<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

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
        .navbar .right {
            float: right; 
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
    

    </style>

	<h1>Portal Administradores</h1>

<div class="navbar">
    <a href="PortalAdministradores.jsp">Inicio</a>
    <a href="adminClientesServlet">Clientes</a>
    <a href="adminCuentasServlet">Cuentas</a>
    <a href="adminPrestamosServlet">Prestamos</a>
    <a href="adminInformes.jsp">Informes</a>
    <a href="Login.jsp" class="right">Salir de sesion</a>
</div>
    </div>
	<h2>Administracion de Clientes</h2>


<h3>¿SEGURO QUE QUIERE ELIMINAR ESTA CUENTA?</h3>


<%

String cuentaId = request.getParameter("cuentaId");
int idCuenta = Integer.parseInt(cuentaId);

if (cuentaId!= null && !cuentaId.isEmpty()) {



    %>
    
    <p>ID de cuenta a Eliminar: <%= idCuenta %></p>

    <form action="adminCuentasServlet" method="get">
    <input type="hidden" name="CuentaId" value="<%= idCuenta %>" />
    <input type="submit" name="btnEliminar" value="CONFIRMAR ELIMINACION " /><br>
    <a class="nav-link" aria-current="page" href="adminCuentas.jsp">Cancelar</a>
    </form>










<%}  else  {  

  %>
  <p>NO HAY NINGUNA CUENTA PARA ELIMINAR</p>

<%
}
%>

































</body>
</html>