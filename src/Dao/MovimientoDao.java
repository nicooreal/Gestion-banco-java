package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Movimiento;
import Dominio.Prestamo;
import Dominio.Usuario;

public class MovimientoDao implements iMovimientoDao {

	private static final String selectAll = "SELECT * FROM cuentas";
	private static final String movimientoPorCuenta = "SELECT * FROM `bd_banco`.`movimientos` WHERE id_cuenta_origen = ? OR id_cuenta_destino = ?";   
	
	private static final String insertMovimientoDesdePrestamo = "INSERT INTO `bd_banco`.`movimientos`(fecha, concepto, importe, id_tipo_movimiento, id_cuenta_origen, id_cuenta_destino) VALUES(?, ?, ?, ?, ?, ?)";
			
			
			

	public int agregarPrestamoAMovimiento(Prestamo prestamoAprobado) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		CuentaDao cDao = new CuentaDao();
		Cuenta cuentaAfectada = new Cuenta();
		
		cuentaAfectada = cDao.buscar_con_id(prestamoAprobado.getIdCuentaDestino());
		
		
		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		String concepto = "PRESTAMO";
		int idTipoMovimiento = 1;
		int cuentaBanco = 1;
		float montoNuevo = prestamoAprobado.getImporteSolicitado() + cuentaAfectada.getSaldo();
		
		try
		{
			conexion = conexionDB.getConnection();
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			statement = conexion.prepareStatement(insertMovimientoDesdePrestamo);
			statement.setDate(1, sqlDate);
			statement.setString(2, concepto);
			statement.setFloat(3, prestamoAprobado.getImporteSolicitado());
			statement.setInt(4, idTipoMovimiento);
			statement.setInt(5, cuentaBanco);
			statement.setInt(6, prestamoAprobado.getIdCuentaDestino());

			filas = statement.executeUpdate();
			
			if(filas > 0)
			{
				System.out.println("PRESTAMO convertido a movimiento bancario correctamente a la cuenta: " + prestamoAprobado.getIdCuentaDestino());
				if(cDao.modificarMontoACuenta(montoNuevo, prestamoAprobado.getIdCuentaDestino()) > 0) {
					System.out.println("PRESTAMO Transferido a la cuenta: " + prestamoAprobado.getIdCuentaDestino());
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return filas;
	}
	
	
	public ArrayList<Movimiento> ListarMovimientosPorCuenta(int id_cuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> listadoMovimiento = new ArrayList<Movimiento>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(movimientoPorCuenta);
			statement.setInt(1, id_cuenta);
			statement.setInt(2, id_cuenta);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoMovimiento.add(getMovimiento(resultSet));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoMovimiento;
	}
	
	
	public ArrayList<Movimiento> Listar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> listadoMovimiento = new ArrayList<Movimiento>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoMovimiento.add(getMovimiento(resultSet));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoMovimiento;
	}
	
	
	private Movimiento getMovimiento(ResultSet resultSet) {
		
		Movimiento movimiento = null;
		
		try {
			TipoMovimientoDao tmDao = new TipoMovimientoDao();
			movimiento = new Movimiento();
			
			movimiento.setId(resultSet.getInt("id_movimiento"));
			movimiento.setFecha(resultSet.getDate("fecha"));
			movimiento.setConcepto(resultSet.getString("concepto"));;
			movimiento.setImporte(resultSet.getFloat("importe"));
			movimiento.setTipoMovimiento(tmDao.getTipoMovimiento(resultSet.getInt("id_tipo_movimiento")));
			movimiento.setId_cuenta_origen(resultSet.getInt("id_cuenta_origen"));
			movimiento.setId_cuenta_destino(resultSet.getInt("id_cuenta_destino"));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movimiento;
	}
	
	
	
	
	
}
