<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    </style>
	</head>
	<body>

		<h1>Portal Administradores</h1>

		<jsp:include page="navbarAdministradores.html"></jsp:include>
    
	</body>
</html>