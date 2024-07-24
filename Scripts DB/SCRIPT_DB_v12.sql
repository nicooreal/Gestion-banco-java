DROP DATABASE IF EXISTS  bd_banco;

CREATE DATABASE IF NOT EXISTS bd_banco
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

USE bd_banco;

CREATE TABLE tipos_cuenta
(
	id_tipocuenta INT NOT NULL AUTO_INCREMENT,
    tipo_cuenta VARCHAR(20) UNIQUE NOT NULL,
    PRIMARY KEY (id_tipocuenta)
);

CREATE TABLE tipos_movimiento
(
	id_tipomovimiento INT NOT NULL AUTO_INCREMENT,
    tipo_movimiento VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id_tipomovimiento)
);

CREATE TABLE provincias
(
 id_provincia int NOT NULL AUTO_INCREMENT,
 provincia VARCHAR(100),
 PRIMARY KEY (id_provincia)
);

CREATE TABLE localidades
(
id_localidad int NOT NULL AUTO_INCREMENT,
id_provincia int NOT NULL,
localidad varchar(150) NOT NULL,
PRIMARY KEY (id_localidad),
FOREIGN KEY (id_provincia) REFERENCES provincias(id_provincia)
);

CREATE TABLE clientes
(
	id_cliente INT NOT NULL AUTO_INCREMENT,
	DNI VARCHAR(8) UNIQUE NOT NULL, 
    CUIL VARCHAR(13) UNIQUE NOT NULL, 
    nombre VARCHAR(20) NOT NULL, 
    apellido VARCHAR(20) NOT NULL,  
    sexo ENUM('Masculino', 'Femenino') NOT NULL,
    nacionalidad VARCHAR(20) NOT NULL,
	fecha_nacimiento DATE NOT NULL, 
    direccion VARCHAR(20) NOT NULL, 
    localidad INT NOT NULL, 
    provincia INT NOT NULL, 
    correo_electronico VARCHAR(60) NOT NULL, 
    estado ENUM('True', 'False') NOT NULL,
	PRIMARY KEY (id_cliente),
    FOREIGN KEY (provincia) REFERENCES provincias(id_provincia),
    FOREIGN KEY (localidad) REFERENCES localidades(id_localidad)
);

CREATE TABLE telefonos
(
    id_telefono INT NOT NULL AUTO_INCREMENT,
    telefono1 VARCHAR(20) UNIQUE NOT NULL,
    telefono2  VARCHAR(20) UNIQUE DEFAULT NULL,
    id_cliente INT NOT NULL,
    PRIMARY KEY (id_telefono),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE usuarios
(
	id_usuario INT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(60) UNIQUE NOT NULL, 
    contrasenia VARCHAR(20) NOT NULL,
	acceso ENUM('Administrador', 'Cliente') NOT NULL DEFAULT 'Cliente',
    id_cliente INT null,
	estado ENUM('True', 'False') NOT NULL DEFAULT 'True',
	PRIMARY KEY (id_usuario),
	FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE cuentas
(
	id_cuenta INT NOT NULL AUTO_INCREMENT,
	id_cliente INT NOT NULL, 
    fecha DATETIME NOT NULL, 
    id_tipo_cuenta INT NOT NULL, 
    numero_cuenta CHAR(10) UNIQUE NOT NULL, 
    CBU CHAR(22) UNIQUE NOT NULL,
    saldo FLOAT(10,2) NOT NULL CHECK (saldo >= 0),
	estado ENUM('True', 'False') NOT NULL,
    PRIMARY KEY (id_cuenta),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_tipo_cuenta) REFERENCES tipos_cuenta(id_tipocuenta)
);

CREATE TABLE movimientos
(
	id_movimiento INT NOT NULL AUTO_INCREMENT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    concepto VARCHAR(50) NULL DEFAULT 'Varios',
    importe FLOAT(10,2) NOT NULL CHECK (importe >= 0),
    id_tipo_movimiento INT NOT NULL,
    id_cuenta_origen INT NOT NULL,
    id_cuenta_destino INT NOT NULL,
    PRIMARY KEY (id_movimiento),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES tipos_movimiento(id_tipomovimiento),
	FOREIGN KEY (id_cuenta_origen) REFERENCES cuentas(id_cuenta),
    FOREIGN KEY (id_cuenta_destino) REFERENCES cuentas(id_cuenta)
);


CREATE TABLE prestamos
(
	id_prestamo INT NOT NULL AUTO_INCREMENT,
	id_cliente INT NOT NULL, 
    fecha DATETIME NOT NULL, 
    importe_a_pagar FLOAT(10,2) NOT NULL CHECK (importe_a_pagar >= 0), 
    importe_solicitado FLOAT(10,2) NOT NULL,
    plazo INT UNSIGNED NOT NULL, 
    monto_mensual FLOAT(10,2) NOT NULL CHECK (monto_mensual >=0), 
    cuotas INT UNSIGNED NOT NULL,
	id_cuenta_destino INT NOT NULL,
    estado ENUM("Solicitado","Aprobado","Rechazado","Finalizado") NOT NULL DEFAULT "Solicitado",
    PRIMARY KEY (id_prestamo),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
	FOREIGN KEY (id_cuenta_destino) REFERENCES cuentas(id_cuenta)
);

CREATE TABLE cuotas_prestamos
(
	id_cuota INT NOT NULL AUTO_INCREMENT,
    id_prestamo INT NOT NULL,
    numero_cuota INT NOT NULL,
    monto_cuota FLOAT (10,2) NOT NULL CHECK(monto_cuota >= 0),
    fecha_vencimiento DATETIME NOT NULL,
    fecha_pago DATETIME NULL,
    estado ENUM("Pagada","Vencida","Prestamo Cancelado","Pendiente") NOT NULL DEFAULT "Pendiente",
    PRIMARY KEY (id_cuota),
    FOREIGN KEY (id_prestamo) REFERENCES prestamos(id_prestamo)
);

USE bd_banco;

DELIMITER //

CREATE PROCEDURE TransferirDinero(IN p_cbu_origen VARCHAR(22), IN p_cbu_destino VARCHAR(22), IN p_monto DECIMAL(10,2))
BEGIN
    DECLARE saldo_origen DECIMAL(10,2);

    SELECT saldo INTO saldo_origen FROM cuentas WHERE CBU = p_cbu_origen;

    IF saldo_origen >= p_monto THEN
        UPDATE cuentas SET saldo = saldo - p_monto WHERE CBU = p_cbu_origen;
        UPDATE cuentas SET saldo = saldo + p_monto WHERE CBU = p_cbu_destino;

        -- Opcional: Se pueden Insertar registros de movimiento aca
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay suficiente saldo para realizar la transferencia';
    END IF;
END //

DELIMITER;
