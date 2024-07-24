<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="Dominio.Cliente"%>
<%@ page import="Dao.ClienteDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Crear cliente</h1>
	<form method="get" action="adminClientesServlet">
	
		<%
			Cliente cAux = null;
			if(request.getParameter("clienteId") != null){
			ClienteDao cDao = new ClienteDao();
			int idBuscado = Integer.parseInt(request.getParameter("clienteId"));
			cAux = cDao.buscar_con_id(idBuscado);}%>
		
		<label>DNI</label>
		<input type="text" name="dni" id="dni" <% if (cAux != null) { %> value="<%= cAux.getDni() %>" <% } %> >
		<br>
		<label>CUIL</label>
		<input type="text" name="cuil" id="cuil" <% if (cAux != null) { %> value="<%= cAux.getCuil() %>" <% } %>>
		<br>
		<label>Nombre</label>
		<input type="text" name="nombre" id="nombre" <% if (cAux != null) { %> value="<%= cAux.getNombre() %>" <% } %>>
		<br>
		<label>Apellido</label>
		<input type="text" name="apellido" id="apellido" <% if (cAux != null) { %> value="<%= cAux.getApellido() %>" <% } %>>
		<br>
		<label>Sexo</label>
		<input type="text" name="sexo" id="sexo" <% if (cAux != null) { %> value="<%= cAux.getSexo() %>" <% } %>>
		<br>
		<label>Nacionalidad</label>
		<input type="text" name="nacionalidad" id="nacionalidad" <% if (cAux != null) { %> value="<%= cAux.getNacionalidad() %>" <% } %>>
		<br>
		<label>Fecha Nacimiento</label>
		<input type="date" name="fechaNacimiento" id="fechaNacimiento" <% if (cAux != null) { %> value="<%= cAux.getFechaNacimiento().toString() %>" <% } %>>
		<br>
		<label>Direccion</label>
		<input type="text" name="direccion" id="direccion" <% if (cAux != null) { %> value="<%= cAux.getDireccion() %>" <% } %>>
		<br>
		<label>Localidad</label>
		<input type="text" name="localidad" id="localidad" <% if (cAux != null) { %> value="<%= cAux.getLocalidad() %>" <% } %>>
		<br>
		<label>Provincia</label>
		<input type="text" name="provincia" id="provincia" <% if (cAux != null) { %> value="<%= cAux.getProvincia().getNombre_provincia() %>" <% } %>>
		<br>
		<label>E-mail</label>
		<input type="text" name="email" id="email" <% if (cAux != null) { %> value="<%= cAux.getCorreoElectronico() %>" <% } %>>
		<br>

		<%
		if(request.getParameter("clienteId") != null){
			
		%>
		<button type="submit" class="btn btn-primary" name="modificarCliente" id="modificarCliente">Modificar</button>
		<input type="hidden" name="idModificar" value="<%= cAux.getId() %>" />
		<%}
		 else { %>
		
		<button type="submit" class="btn btn-primary" name="SubmitCliente" id="SubmitCliente">Aceptar</button>
		
		<%} %>
	</form>
</body>
</html>

