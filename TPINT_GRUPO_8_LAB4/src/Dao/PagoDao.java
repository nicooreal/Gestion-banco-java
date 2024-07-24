package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import Dominio.Pago;
import Dominio.Prestamo;

public class PagoDao {

	private static final String selectAll = "SELECT * FROM pagos";
	private static final String selectPagoPorId = "SELECT * FROM pagos WHERE id = ?";
	private static final String selectPagoPorIdPrestamo = "SELECT * FROM pagos WHERE id_prestamo = ?";
	
	private static final String updateEstadoPagado = "UPDATE pagos SET estado = 'Pagado' WHERE id = ?";
	private static final String updateEstadoVencido = "UPDATE pagos SET estado = 'Vencido' WHERE id = ?";
	private static final String insertPago = "INSERT INTO pagos (fecha , importe, fecha_vencimiento, id_prestamo, id_cuenta, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String selectWhereIdCliente = "SELECT * FROM pagos WHERE id_cliente = ?";
	private static final String selectWhereIdCuenta = "SELECT * FROM pagos WHERE id_cuenta = ?";
	
	
	
	/*
	public int realizarPago(Pago pagoRealizado) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		
		try
		{
			conexion = conexionDB.getConnection();
			
			//fecha
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    //importe
		    float pagoMensual = prestamoNuevo.getImporteApagar()/prestamoNuevo.getCuotas();
		    
		    // Fecha de vencimiento (seria 30 dias mas la fecha actual para cada pago)  multiplicado por la cantidad de meses
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(utilDate);
		    calendar.add(Calendar.DAY_OF_YEAR, 30 * prestamoNuevo.getCuotas());
		    java.sql.Date fecha_vencimiento = new java.sql.Date(calendar.getTimeInMillis());
		    

			statement = conexion.prepareStatement(insertPago);
			statement.setDate(1, sqlDate);
			statement.setFloat(2, pagoMensual);
			statement.setDate(3, fecha_vencimiento);
			
			statement.setInt(4, prestamoNuevo.getIdPrestamo());
			statement.setInt(5, prestamoNuevo.getIdCuentaDestino());
			statement.setString(6, "ATiempo");
			
			if(statement.executeUpdate() > 0)
			{
				filas = 1;
				System.out.println("El PAGO fue Insertado correctamente...");
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
	}*/
	
	//agrega el registro para pagar luego
	public int agregarPagoABase(Prestamo prestamoNuevo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		
		try
		{
			conexion = conexionDB.getConnection();
			
			
		    //importe
		    float pagoMensual = prestamoNuevo.getImporteApagar()/prestamoNuevo.getCuotas();
		    
		    for (int i = 1; i <= prestamoNuevo.getCuotas(); i++) {
		    	
		    	// Fecha de vencimiento (seria 30 dias mas la fecha actual para cada pago)  multiplicado por la cantidad de meses
			    Calendar calendar = Calendar.getInstance();
			    java.util.Date utilDate = new java.util.Date();
			    calendar.setTime(utilDate);
		    	calendar.add(Calendar.DAY_OF_YEAR, 30 * i);
			    java.sql.Date fecha_vencimiento = new java.sql.Date(calendar.getTimeInMillis());

				statement = conexion.prepareStatement(insertPago);
				statement.setDate(1, null);
				statement.setFloat(2, pagoMensual);
				statement.setDate(3, fecha_vencimiento);
				
				statement.setInt(4, prestamoNuevo.getIdPrestamo());
				statement.setInt(5, prestamoNuevo.getIdCuentaDestino());
				statement.setString(6, "ATiempo");
				
				if(statement.executeUpdate() > 0)
				{
					filas++;
					System.out.println("El PAGO fue Insertado correctamente...");
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
		System.out.println("Total de pagos insertados: " + filas);
		return filas;
	}	
	
	
	
	public ArrayList<Pago> ListarPorIdPrestamo(int id_prestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Pago> listadoPagosPorPrestamo = new ArrayList<Pago>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectPagoPorIdPrestamo);
			statement.setInt(1, id_prestamo);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoPagosPorPrestamo.add(getPago(resultSet));
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
		return listadoPagosPorPrestamo;
	}
	
	
	private Pago getPago(ResultSet resultSet) {
		
		Pago pago = new Pago();
		
		try {
			pago.setId(resultSet.getInt("id"));
			pago.setIdCuenta(resultSet.getInt("id_cuenta"));
			pago.setIdPrestamo(resultSet.getInt("id_prestamo"));
			pago.setFecha(resultSet.getDate("fecha"));
			pago.setFechaVencimiento(resultSet.getDate("fecha_vencimiento"));
			pago.setImporteApagar(resultSet.getFloat("importe"));
			pago.setEstado(resultSet.getString("estado"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pago;
	}
	
	
}
