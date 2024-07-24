<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="Dao.ClienteDao"%>
<%@ page import="Dominio.*"%>
<%@ page import="Dao.ProvinciaDao"%>
<%@ page import="java.util.List"%>
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

 <script>
        function showMessage(message) {
            alert(message);
        }

        function validateForm() {
            var isValid = true;
            var fields = [
                'dni', 'cuil', 'nombre', 'apellido', 'telefono1', 'telefono2',
                'sexo', 'fechaNacimiento', 'direccion', 'nacionalidad', 'provincia',
                'localidad', 'email'
            ];

            fields.forEach(function(fieldId) {
                var field = document.getElementById(fieldId);
                if (field && field.value.trim() === '') {
                    isValid = false;
                    field.style.borderColor = 'red';
                } else {
                    if (field) {
                        field.style.borderColor = '';
                    }
                }
            });

            if (!isValid) {
                showMessage('Por favor complete todos los campos obligatorios.');
                return false;
            }
            
            // Custom messages based on the button
            var button = document.activeElement;
            if (button.name === 'modificarCliente') {
                showMessage('Modificación del cliente realizada con éxito.');
            } else if (button.name === 'SubmitCliente') {
                showMessage('Cliente aceptado con éxito.');
            }
            
            return true; // Proceed with form submission if validation is successful
        }
    </script>

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
	<h3>Crear cliente</h3>
	<form method="get" action="adminClientesServlet" onsubmit="return validateForm()">
	
		<%
			Cliente cAux = null;
			if(request.getParameter("clienteId") != null){
			ClienteDao cDao = new ClienteDao();
			int idBuscado = Integer.parseInt(request.getParameter("clienteId"));
			cAux = cDao.buscar_con_id(idBuscado);}%>
		
		
		<div class="form-container">
           <div class="form-group">    
		
		<label>DNI</label>
		<input type="text" name="dni" id="dni" pattern="\d+" title="solo números" <% if (cAux != null) { %> value="<%= cAux.getDni() %>" <% } %> >
		<br>
		<label>CUIL</label>
		<input type="text" name="cuil" id="cuil" pattern="\d+" title="solo números" <% if (cAux != null) { %> value="<%= cAux.getCuil() %>" <% } %>>
		<br>
		<label>Nombre</label>
		<input type="text" name="nombre" id="nombre" <% if (cAux != null) { %> value="<%= cAux.getNombre() %>" <% } %>>
		<br>
		<label>Apellido</label>
		<input type="text" name="apellido" id="apellido" <% if (cAux != null) { %> value="<%= cAux.getApellido() %>" <% } %>>
		<br>
		<br>
		<label>Telefono 1</label>
		<input type="text" name="telefono1" id="telefono1" pattern="\d+" title="solo números" <% if (cAux != null) { %> value="<%= cAux.getTelefono1().getTelefono() %>" <% } %>>
		<br>
		
		<br>
		<label>Telefono 2</label>
		<input type="text" name="telefono2" id="telefono2" pattern="\d+" title="solo números" <% if (cAux != null) { %> value="<%= cAux.getTelefono2().getTelefono() %>" <% } %>>
		<br>
		
		
		
		<label>Sexo</label>
		<input type="text" name="sexo" id="sexo" <% if (cAux != null) { %> value="<%= cAux.getSexo() %>" <% } %>>
		<br>
		
		
		<label>Nacionalidad</label>
		<select name="nacionalidad" id="nacionalidad">
		<% if(request.getAttribute("listadoPaises") != null){ 
			List<Pais> listadoPaises =  (List<Pais>)request.getAttribute("listadoPaises");
		for (Pais p : listadoPaises){
			%>
		
        <option value="<%= p.getIdPais() %>" <%= (cAux != null && cAux.getNacionalidad().getIdPais() == p.getIdPais()) ? "selected" : "" %>><%= p.getNombre() %></option>
    <% 
        }
    } 
    %>
		
		</select>
		

		
		
		
		
		<label for="fecha">Fecha nacimiento (YYYY-MM-DD):</label>
		<input type="text" name="fechaNacimiento" id="fechaNacimiento" pattern="\d{4}-\d{2}-\d{2}" title="ingrese la fecha en el formato YYYY-MM-DD" <% if (cAux != null) { %> value="<%= cAux.getFechaNacimiento().toString() %>" <% } %>>
		<br>
		<label>Direccion</label>
		<input type="text" name="direccion" id="direccion" <% if (cAux != null) { %> value="<%= cAux.getDireccion() %>" <% } %>>
		<br>

		<label>Provincia</label>
		<select name="provincia" id="provincia">
		<% if(request.getAttribute("listadoProvincias") != null){ 
			List<Provincia> listadoProvincias =  (List<Provincia>)request.getAttribute("listadoProvincias");
		for (Provincia p : listadoProvincias){


		    %>
	        <option value="<%= p.getId_provincia() %>" <%= (cAux != null && cAux.getProvincia().getId_provincia() == p.getId_provincia()) ? "selected" : "" %>><%= p.getNombre_provincia() %></option>
	    <% 
	        }
	    } 
	    %>
	</select>
			<label>Localidad</label>
		<select name="localidad" id="localidad">
		<% if(request.getAttribute("listadoLocalidades") != null){ 
			List<Localidad> listadoLocalidades =  (List<Localidad>)request.getAttribute("listadoLocalidades");
		for (Localidad L : listadoLocalidades){

			   %>
		        <option value="<%= L.getIdLocalidad() %>" <%= (cAux != null && cAux.getLocalidad().getIdLocalidad() == L.getIdLocalidad()) ? "selected" : "" %>><%= L.getLocalidad() %></option>
		    <% 
		        }
		    } 
		    %>
			
			
			
		</select>
	
				<label>Contraseña</label>
		<input type="password" name="contraseña" id="contraseña" <% if (cAux != null) { %> value="<%  %>" <% } %>>
		<br>
	


		<br>
		<label>E-mail</label>
		<input type="text" name="email" id="email" <% if (cAux != null) { %> value="<%= cAux.getCorreoElectronico() %>" <% } %>>
		<br>

		<%
		if(request.getParameter("clienteId") != null){
			
		%>
		<button type="submit" class="btn btn-primary" name="modificarCliente" id="modificarCliente" >Modificar</button>
		<input type="hidden" name="idModificar" value="<%= cAux.getId() %>" />
		<%}
		 else { %>
		
		<button type="submit" class="btn btn-primary" name="SubmitCliente" id="SubmitCliente" >Aceptar</button>
		
		<%} %>
	
	
	
	
	
	
	    </div>
		</div>
	
	</form>


</body>
</html>

