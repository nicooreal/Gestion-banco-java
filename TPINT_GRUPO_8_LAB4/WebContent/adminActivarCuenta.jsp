<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
     <style>
           .navbar .right {
            float: right;
    </style>

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


<h3>¿SEGURO QUE QUIERE ACTIVAR ESTA CUENTA?</h3>


<%

String cuentaId = request.getParameter("cuentaId");
int idCuenta = Integer.parseInt(cuentaId);

if (cuentaId!= null && !cuentaId.isEmpty()) {



    %>
    
    <p>ID de cuenta a Activar: <%= idCuenta %></p>

    <form action="adminCuentasServlet" method="get">
    <input type="hidden" name="CuentaId" value="<%= idCuenta %>" />
    <input type="submit" name="btnActivar" value="CONFIRMAR ALTA " /><br>
    <a class="nav-link" aria-current="page" href="adminCuentas.jsp">Cancelar</a>
    </form>










<%}  else  {  

  %>
  <p>NO HAY NINGUNA CUENTA PARA ACTIVAR</p>

<%
}
%>



























</body>
</html>