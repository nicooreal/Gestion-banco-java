<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perfil de Cliente</title>
<style>
.profile-label-container {
   float: right; /* Coloca el contenedor a la derecha */
    display: inline-block;
    padding: 8px 16px; /* Espacios internos */
    background-color: #f44336; /* Color de fondo rojo */
    border-radius: 20px; /* Borde redondeado */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Sombra */
    color: #fff; /* Color de texto blanco */
    font-size: 16px; /* Tamaño de fuente */
    font-weight: bold; /* Negrita */
    text-transform: uppercase; /* Texto en mayúsculas */
}

.profile-label-icon {
    margin-right: 10px;
    font-size: 20px; /* Tamaño del ícono */
    vertical-align: middle; /* Alineación vertical */
}
</style>
</head>
<body>

<div class="profile-label-container">
    <i class="profile-label-icon fas fa-user"></i>
    <span>
        <%= session.getAttribute("perfilClienteNombre") %>
    </span>
</div>

</body>
</html>
