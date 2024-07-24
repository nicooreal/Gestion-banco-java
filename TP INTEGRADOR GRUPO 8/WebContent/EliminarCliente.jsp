<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h3>¿SEGURO QUE QUIERE ELIMINAR EL CLIENTE?</h3>


<%

String clienteId = request.getParameter("clienteId");
int idCliente = Integer.parseInt(clienteId);

if (clienteId != null && !clienteId.isEmpty()) {



    %>
    
    <p>ID del Cliente a Eliminar: <%= idCliente %></p>

<%}  else  {  

  %>
  <p>NO HAY NINGUN CLIENTE PARA ELIMINAR</p>

<%
}
%>

    <form action="adminClientesServlet" method="get">
        <input type="hidden" name="ClienteId" value="<%= idCliente %>" />
        <input type="submit" name="btnEliminar" value="CONFIRMAR ELIMINACION " /><br>
        <a class="nav-link" aria-current="page" href="adminClientes.jsp">Cancelar</a>
    </form>



</body>
</html>