<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Transferencia Exitosa</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

    		<jsp:include page="navbarClientes.html"></jsp:include>
           <jsp:include page="ClienteNombreApellido.jsp"></jsp:include>
    
    <div class="container mt-5">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">¡Transferencia Exitosa!</h4>
            <p>La transferencia se ha realizado correctamente.</p>
            <hr>
            <p class="mb-0">Gracias por utilizar nuestros servicios.</p>
        </div>
        <a href="clienteTransferencias.jsp" class="btn btn-primary">Realizar Otra Transferencia</a>
        <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
    </div>
</body>
</html>