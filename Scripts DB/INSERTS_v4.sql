USE bd_banco;
-- ------------------INSERTS USUARIOS ADMINS
INSERT INTO `bd_banco`.`usuarios`
(`usuario`, `contrasenia`, `acceso`, `id_cliente`, `estado`)
VALUES
("nruiz", "Nruiz1234", "Administrador", null, "True"),
("mmachi", "Mmachi1234", "Administrador", null, "True"),
("bsablich", "Bsablich1234", "Administrador", null, "True"),
("nreal", "Nreal1234", "Administrador", null, "True"),
("mvisgarra", "Mvisgarra1234", "Administrador", null, "True"),
("admin", "admin", "Administrador", null, "True");

-------- INSERTS PROVINCIAS
INSERT INTO provincias (id_provincia, provincia) VALUES
(1, 'Buenos Aires'),
(2, 'Capital Federal'),
(3, 'Catamarca'),
(4, 'Chaco'),
(5, 'Chubut'),
(6, 'Córdoba'),
(7, 'Corrientes'),
(8, 'Entre Ríos'),
(9, 'Formosa'),
(10, 'Jujuy'),
(11, 'La Pampa'),
(12, 'La Rioja'),
(13, 'Mendoza'),
(14, 'Misiones'),
(15, 'Neuquén'),
(16, 'Río Negro'),
(17, 'Salta'),
(18, 'San Juan'),
(19, 'San Luis'),
(20, 'Santa Cruz'),
(21, 'Santa Fe'),
(22, 'Santiago del Estero'),
(23, 'Tierra del Fuego'),
(24, 'Tucumán');


-------- INSERTS LOCALIDADES (ALGUNAS DE EJEMPLO)
INSERT INTO localidades (id_localidad, id_provincia, localidad) VALUES
(1, 1, 'La Plata'),  -- Capital de Buenos Aires
(2, 1, 'Mar del Plata'),
(3, 1, 'Bahía Blanca'),
(4, 1, 'Zona Sur'),
(5, 1, 'Zona Norte'),
(6, 1, 'Olavarría'),
(7, 2, 'Ciudad Autónoma de Buenos Aires'),  -- Capital Federal
(8, 3, 'San Fernando del Valle de Catamarca'),  -- Capital de Catamarca
(9, 4, 'Resistencia'),  -- Capital de Chaco
(10, 5, 'Rawson'),  -- Capital de Chubut
(11, 6, 'Córdoba'),  -- Capital de Córdoba
(12, 7, 'Corrientes'),  -- Capital de Corrientes
(13, 8, 'Paraná'),  -- Capital de Entre Ríos
(14, 9, 'Formosa'),  -- Capital de Formosa
(15, 10, 'San Salvador de Jujuy'),  -- Capital de Jujuy
(16, 11, 'Santa Rosa'),  -- Capital de La Pampa
(17, 12, 'La Rioja'),  -- Capital de La Rioja
(18, 13, 'Mendoza'),  -- Capital de Mendoza
(19, 14, 'Posadas'),  -- Capital de Misiones
(20, 15, 'Neuquén'),  -- Capital de Neuquén
(21, 16, 'Viedma'),  -- Capital de Río Negro
(22, 17, 'Salta'),  -- Capital de Salta
(23, 18, 'San Juan'),  -- Capital de San Juan
(24, 19, 'San Luis'),  -- Capital de San Luis
(25, 20, 'Río Gallegos'),  -- Capital de Santa Cruz
(26, 21, 'Santa Fe'),  -- Capital de Santa Fe
(27, 22, 'Santiago del Estero'),  -- Capital de Santiago del Estero
(28, 23, 'Ushuaia'),  -- Capital de Tierra del Fuego
(29, 24, 'San Miguel de Tucumán');  -- Capital de Tucumán


-------- INSERTS DE CLIENTES (REVISAR SIEMPRE CONSISTENCIA CON ID CLIENTE Y USUARIOS CREADOS)
INSERT INTO clientes (DNI, CUIL, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo_electronico, estado)
VALUES ('12345678', '20-12345678-9', 'Juan', 'Pérez', 'Masculino', 'Argentina', '1985-04-23', 'CFalsa 123', 1 , 1, 'juan.perez@example.com', 'True');

INSERT INTO clientes (DNI, CUIL, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo_electronico, estado)
VALUES ('87654321', '27-87654321-3', 'María', 'Gómez', 'Femenino', 'Argentina', '1990-11-15', 'Siempreviva 74', 11, 6, 'maria.gomez@example.com', 'True');

INSERT INTO clientes (DNI, CUIL, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo_electronico, estado)
VALUES ('45678912', '23-45678912-1', 'Carlos', 'Sánchez', 'Masculino', 'Argentina', '1988-07-30', 'San Martín 456',26 ,21 , 'carlos.sanchez@example.com', 'True');

-- ------------------INSERTS USUARIOS CLIENTES (CONTRASEÑAS SIMPLIFICADAS, POR DEFECTO VAN A IR CON EL DNI)
INSERT INTO `bd_banco`.`usuarios` (`usuario`, `contrasenia`, `acceso`, `id_cliente`, `estado`)
VALUES ("juan.perez@example.com", "12345678", "Cliente", 1, "True"),
("maria.gomez@example.com", "1234", "Cliente", 2, "True"),
("carlos.sanchez@example.com", "1234", "Cliente", 3, "True");

-------- INSERTS TIPOS DE CUENTA
INSERT INTO tipos_cuenta (tipo_cuenta) VALUE ('Cuenta Corrriente');
INSERT INTO tipos_cuenta (tipo_cuenta) VALUE ('Caja Ahorro');


-------- INSERTS CUENTAS
INSERT INTO cuentas (id_cliente, fecha, id_tipo_cuenta, numero_cuenta, CBU, saldo, estado)
VALUES (1, '2024-06-27 12:00:00', 1, '0001234567', '0000003100000001234567', 1000.00, 'True');

INSERT INTO cuentas (id_cliente, fecha, id_tipo_cuenta, numero_cuenta, CBU, saldo, estado)
VALUES (2, '2024-06-27 12:00:00', 2, '0007654321', '0000003200000007654321', 2500.50, 'True');

INSERT INTO cuentas (id_cliente, fecha, id_tipo_cuenta, numero_cuenta, CBU, saldo, estado)
VALUES (3, '2024-06-27 12:00:00', 1, '0004567891', '0000003300000004567891', 3000.75, 'True');

INSERT INTO cuentas (id_cliente, fecha, id_tipo_cuenta, numero_cuenta, CBU, saldo, estado)
VALUES (1, '2024-06-27 12:00:00', 1, '0009876543', '0000003400000009876543', 1500.00, 'True');

INSERT INTO cuentas (id_cliente, fecha, id_tipo_cuenta, numero_cuenta, CBU, saldo, estado)
VALUES (2, '2024-06-27 12:00:00', 2, '0001928374', '0000003500000001928374', 500.25, 'True');


-------- INSERTS PRESTAMOS
INSERT INTO prestamos (id_cliente, fecha, importe_a_pagar, importe_solicitado, plazo, monto_mensual, cuotas, id_cuenta_destino, estado)
VALUES 
(1, '2024-06-27 10:00:00', 12000.00, 10000.00, 12, 1000.00, 12, 1, 'Aprobado'),
(2, '2024-06-28 11:00:00', 24000.00, 20000.00, 24, 1000.00, 24, 2, 'Solicitado'),
(3, '2024-06-29 12:00:00', 6000.00, 5000.00, 6, 1000.00, 6, 3, 'Aprobado'),
(1, '2024-06-30 13:00:00', 36000.00, 30000.00, 36, 1000.00, 36, 4, 'Solicitado'),
(3, '2024-07-01 14:00:00', 48000.00, 40000.00, 48, 1000.00, 48, 5, 'Solicitado');


-------- INSERTS DE CUOTAS DEL PRESTAMOS
-- Cuotas para el primer préstamo aprobado (id_prestamo = 1)
INSERT INTO cuotas_prestamos (id_prestamo, numero_cuota, monto_cuota, fecha_vencimiento, fecha_pago, estado)
VALUES 
(1, 1, 1000.00, '2024-07-27 00:00:00', NULL, 'Pendiente'),
(1, 2, 1000.00, '2024-08-27 00:00:00', NULL, 'Pendiente'),
(1, 3, 1000.00, '2024-09-27 00:00:00', NULL, 'Pendiente'),
(1, 4, 1000.00, '2024-10-27 00:00:00', NULL, 'Pendiente'),
(1, 5, 1000.00, '2024-11-27 00:00:00', NULL, 'Pendiente'),
(1, 6, 1000.00, '2024-12-27 00:00:00', NULL, 'Pendiente'),
(1, 7, 1000.00, '2025-01-27 00:00:00', NULL, 'Pendiente'),
(1, 8, 1000.00, '2025-02-27 00:00:00', NULL, 'Pendiente'),
(1, 9, 1000.00, '2025-03-27 00:00:00', NULL, 'Pendiente'),
(1, 10, 1000.00, '2025-04-27 00:00:00', NULL, 'Pendiente'),
(1, 11, 1000.00, '2025-05-27 00:00:00', NULL, 'Pendiente'),
(1, 12, 1000.00, '2025-06-27 00:00:00', NULL, 'Pendiente');

-- Cuotas para el segundo préstamo aprobado (id_prestamo = 3)
INSERT INTO cuotas_prestamos (id_prestamo, numero_cuota, monto_cuota, fecha_vencimiento, fecha_pago, estado)
VALUES 
(3, 1, 1000.00, '2024-07-29 00:00:00', NULL, 'Pendiente'),
(3, 2, 1000.00, '2024-08-29 00:00:00', NULL, 'Pendiente'),
(3, 3, 1000.00, '2024-09-29 00:00:00', NULL, 'Pendiente'),
(3, 4, 1000.00, '2024-10-29 00:00:00', NULL, 'Pendiente'),
(3, 5, 1000.00, '2024-11-29 00:00:00', NULL, 'Pendiente'),
(3, 6, 1000.00, '2024-12-29 00:00:00', NULL, 'Pendiente');


-------- INSERTS TIPOS DE MOVIMIENTO
INSERT INTO tipos_movimiento (tipo_movimiento) VALUE ('ingreso');
INSERT INTO tipos_movimiento (tipo_movimiento) VALUE ('egreso');

-------- INSERTS MOVIMIENTO
INSERT INTO movimientos (importe, id_tipo_movimiento, id_cuenta_origen, id_cuenta_destino)
VALUES 
(100.00, 1, 1, 2),
(200.50, 2, 2, 3),
(50.75, 1, 3, 4),
(300.20, 2, 4, 5),
(400.00, 1, 5, 6),
(150.35, 2, 6, 7),
(250.45, 1, 7, 8),
(350.60, 2, 8, 9),
(450.75, 1, 9, 10),
(500.90, 2, 10, 1);