-- VISTA SALDO POR CLIENTE (PARA INFORME 1)
DROP VIEW IF EXISTS vw_saldo_por_cliente;
/*
CREATE VIEW vw_saldo_por_cliente AS
SELECT id_cliente, SUM(saldo) AS total_saldo, fecha 
FROM cuentas
GROUP BY id_cliente
ORDER BY total_saldo DESC;

ESTA VISTA LA HIZO MARIAN PERO NO FILTRA TODAS LAS CUENTAS 

*/

CREATE VIEW vw_saldo_por_cliente AS
SELECT id_cliente, saldo AS total_saldo, fecha 
FROM cuentas






-- INFORME 1: SP BUSCA SALDOS POR CLIENTE MAYORES A
DROP PROCEDURE IF EXISTS sp_buscar_saldos_mayores;
DELIMITER //

CREATE PROCEDURE sp_buscar_saldos_mayores(
    IN num FLOAT,
    IN fecha_inicio DATE,
    IN fecha_fin DATE
)
BEGIN
    SELECT id_cliente, total_saldo
    FROM vw_saldo_por_cliente
    WHERE total_saldo >= num
      AND fecha BETWEEN fecha_inicio AND fecha_fin  -- Filtra por el rango de fechas
    ORDER BY total_saldo ASC;
END //
DELIMITER ;


-- INFORME 2: SP BUSCA SALDOS POR CLIENTE MENORES A
DROP PROCEDURE IF EXISTS sp_buscar_saldos_menores;
DELIMITER //

CREATE PROCEDURE sp_buscar_saldos_menores(
    IN num FLOAT,
    IN fecha_inicio DATE,
    IN fecha_fin DATE
)
BEGIN
    SELECT id_cliente, total_saldo
    FROM vw_saldo_por_cliente
    WHERE total_saldo <= num
      AND fecha BETWEEN fecha_inicio AND fecha_fin  -- Filtra por el rango de fechas
    ORDER BY total_saldo DESC;
END //

DELIMITER ;

-- INFORME 3: CANTIDAD DE CLIENTES POR PROVINCIA
DROP VIEW IF EXISTS vw_clientes_por_provincia;

CREATE VIEW vw_clientes_por_provincia AS
SELECT count(id_cliente) AS ClientesXProvincia, clientes.provincia AS id_provincia, tProv.provincia 
FROM clientes
INNER JOIN provincias AS tProv ON clientes.provincia = tProv.id_provincia 
GROUP BY clientes.provincia
ORDER BY ClientesXProvincia DESC









CREATE DEFINER=`root`@`localhost` PROCEDURE `TransferirDinero`(IN p_cbu_origen VARCHAR(22), IN p_cbu_destino VARCHAR(22), IN p_monto DECIMAL(10,2),
    OUT p_id_cuenta_origen INT,
    OUT p_id_cuenta_destino INT)
BEGIN
    DECLARE saldo_origen DECIMAL(10,2);

    -- Obtener el ID y el saldo de la cuenta de origen
    SELECT id_cuenta, saldo INTO p_id_cuenta_origen, saldo_origen FROM cuentas WHERE CBU = p_cbu_origen;

    -- Obtener el ID de la cuenta de destino
    SELECT id_cuenta INTO p_id_cuenta_destino FROM cuentas WHERE CBU = p_cbu_destino;

    IF saldo_origen >= p_monto THEN
        UPDATE cuentas SET saldo = saldo - p_monto WHERE CBU = p_cbu_origen;
        UPDATE cuentas SET saldo = saldo + p_monto WHERE CBU = p_cbu_destino;

        -- Opcional: Se pueden Insertar registros de movimiento aca
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay suficiente saldo para realizar la transferencia';
    END IF;
END






