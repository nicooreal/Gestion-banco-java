<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Portal bancario UTN</title>
		<link rel="stylesheet" type="text/css" href="css/errorpage.css"/>
	</head>
	<body>

		<div class="container">
		    <div class="header">
		        <h1>Fallo en la aplicacion</h1>
		        <p><%= request.getAttribute("exception") %></p>
		    </div>
		    <form method="post" action="<%= request.getAttribute("paginaOrigen") %>">
		        <input type="submit" name="botonVolver" value="VOLVER" class="button" />
		    </form>
		</div>

	</body>
</html>
