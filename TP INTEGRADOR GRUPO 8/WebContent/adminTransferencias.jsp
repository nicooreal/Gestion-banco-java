<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Portal de Transferencias</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 50%;
            margin: 0 auto;
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
        .form-group {
            margin: 20px 0;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        select, input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
                 .navbar .right {
            float: right;
    </style>
</head>
<body>

<div class="navbar">
    <a href="PortalAdministradores.jsp">Inicio</a>
    <a href="adminClientesServlet">Clientes</a>
    <a href="adminCuentasServlet">Cuentas</a>
    <a href="adminPrestamosServlet">Prestamos</a>
    <a href="adminInformes.jsp">Informes</a>
    <a href="Login.jsp" class="right">Salir de sesion</a>
</div>
    <div class="container">
        <div class="header">
            <h1>Transferencias</h1>
        </div>
        <form action="TransferenciaServlet" method="post">
            <div class="form-group">
                <label for="cuentaOrigen">Cuenta Origen</label>
                <select id="cuentaOrigen" name="cuentaOrigen">
                    <option value="CA XXXX-00000000">XXXX--000001</option>
                    <!-- Agrega más opciones según sea necesario -->
                </select>
            </div>
            <div class="form-group">
                <label for="tipoTransferencia">Tipo de transferencia</label>
                <input type="radio" id="propias" name="tipoTransferencia" value="propias" checked>
                <label for="propias">Cuentas propias</label><br>
                <input type="radio" id="otras" name="tipoTransferencia" value="otras">
                <label for="otras">Otras cuentas</label>
            </div>
            <div class="form-group">
                <label for="cuentaDestino">Cuenta Destino</label>
                <select id="cuentaDestino" name="cuentaDestino">
                    <!-- Opciones de cuenta destino -->
                </select>
            </div>
            <div class="form-group">
                <label for="importe">Importe a Transferir</label>
                <input type="text" id="importe" name="importe" placeholder="Ingrese el importe">
            </div>
            <button type="submit">Aceptar</button>
        </form>
    </div>
</body>
</html>