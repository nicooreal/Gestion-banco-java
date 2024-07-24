package Dao;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Statement;

import Dominio.Pago;
import Dominio.Prestamo;


public class PrestamoDao {

	private static final String selectAll = "SELECT * FROM prestamos";
	private static final String RechazarID = "UPDATE prestamos SET estado = 'Rechazado' WHERE id_prestamo = ?";
	private static final String AprobarID = "UPDATE prestamos SET estado = 'Aprobado' WHERE id_prestamo = ?";
	private static final String FinalizarID = "UPDATE prestamos SET estado = 'Finalizado' WHERE id_prestamo = ?";
	private static final String insertPrestamo = "INSERT INTO prestamos (id_cliente, fecha, importe_a_pagar, importe_solicitado, plazo, monto_mensual, cuotas, id_cuenta_destino, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String selectWhereIdCliente = "SELECT * FROM prestamos WHERE id_cliente = ?";
	private static final String selectPrestamoIdAndIdCuenta = "SELECT * FROM prestamos WHERE id_prestamo = ? AND id_cuenta_destino = ?";
	private static final String selectPrestamoPorId = "SELECT * FROM prestamos WHERE id_prestamo = ?";

	
	public int agregarPrestamo(Prestamo prestamoNuevo) {
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
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			statement = conexion.prepareStatement(insertPrestamo, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, prestamoNuevo.getCliente().getId());
			statement.setDate(2, sqlDate);
			statement.setFloat(3, prestamoNuevo.getImporteApagar());
			statement.setFloat(4, prestamoNuevo.getImporteSolicitado());
			statement.setInt(5, prestamoNuevo.getPlazo());
			statement.setFloat(6, prestamoNuevo.getMontoMensual());
			statement.setInt(7, prestamoNuevo.getCuotas());
			statement.setInt(8, prestamoNuevo.getIdCuentaDestino());
			statement.setString(9, prestamoNuevo.getEstado().name());
			
			if(statement.executeUpdate() > 0)
			{
				filas = 1;
				System.out.println("El Prestamo fue Insertado correctamente...");
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
	
	
	public ArrayList<Prestamo> Listar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Prestamo> listadoPrestamos = new ArrayList<Prestamo>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoPrestamos.add(getPrestamo(resultSet));
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
		return listadoPrestamos;
	}

	public ArrayList<Prestamo> ListarPorIdCliente(int id_cliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Prestamo> listadoPrestamosPorCliente = new ArrayList<Prestamo>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectWhereIdCliente);
			statement.setInt(1, id_cliente);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoPrestamosPorCliente.add(getPrestamo(resultSet));
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
		return listadoPrestamosPorCliente;
	}
	
	private Prestamo getPrestamo(ResultSet resultSet) {
	
		Prestamo prestamo = null;
		
		try {
			
			
			ClienteDao cDao = new ClienteDao();
	
			prestamo = new Prestamo();
	        prestamo.setIdPrestamo(resultSet.getInt("id_prestamo"));
	        prestamo.setCliente(cDao.buscar_con_id(resultSet.getInt("id_cliente")));
	        prestamo.setFechaAlta(resultSet.getDate("fecha"));
	        prestamo.setImporteApagar(resultSet.getFloat("importe_a_pagar"));
	        prestamo.setImporteSolicitado(resultSet.getFloat("importe_solicitado"));
	        prestamo.setPlazo(resultSet.getInt("plazo"));
	        prestamo.setMontoMensual(resultSet.getFloat("monto_mensual"));
	        prestamo.setCuotas(resultSet.getInt("cuotas"));
	        prestamo.setIdCuentaDestino(resultSet.getInt("id_cuenta_destino"));
	        prestamo.setEstado(resultSet.getString("estado"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}


	public int rechazarPrestamo(int id_cliente_rechazar){
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
	
	
			statement = conexion.prepareStatement(RechazarID);
			statement.setInt(1, id_cliente_rechazar);
	
			if(statement.executeUpdate() > 0)
			{
				filas = 1;
				System.out.println("El prestamo fue rechazado correctamente...");
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



	public int aprobarPrestamo(int id_prestamo_aprobar, int id_cuenta_destino){
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
			statement = conexion.prepareStatement(AprobarID);
			statement.setInt(1, id_prestamo_aprobar);
			
			if(statement.executeUpdate() > 0)
			{
				filas = 1;
				System.out.println("El prestamo fue aprobado correctamente...");
				Prestamo prestamoAprobado = new Prestamo();
				PagoDao pagoDao = new PagoDao();
				MovimientoDao mDao = new MovimientoDao();
				
				prestamoAprobado = buscarPrestamoACuentaDestino(id_cuenta_destino, id_prestamo_aprobar);
								
				pagoDao.agregarPagoABase(prestamoAprobado);
				mDao.agregarPrestamoAMovimiento(prestamoAprobado);
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

	
	public Prestamo buscarPrestamoACuentaDestino (int id_cuenta_destino, int id_prestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Prestamo prestamo = new Prestamo();
		Connection conexion = null;
		ResultSet resultSet;
		PreparedStatement statement;
		
		try
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectPrestamoIdAndIdCuenta);
			statement.setInt(1, id_prestamo);
			statement.setInt(2, id_cuenta_destino);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				prestamo = (getPrestamo(resultSet));
			}
			
			if(prestamo != null)
			{
				System.out.println("ID Prestamo: " + id_prestamo + " Aprobado al ID cuenta destino: " + id_cuenta_destino);
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
		return prestamo;
	}
	
	
	public Prestamo buscarPrestamoPorId (int id_prestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Prestamo prestamo = new Prestamo();
		Connection conexion = null;
		ResultSet resultSet;
		PreparedStatement statement;
		
		try
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectPrestamoPorId);
			statement.setInt(1, id_prestamo);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				prestamo = (getPrestamo(resultSet));
			}
			
			if(prestamo != null)
			{
				System.out.println("ID Prestamo a pagar: " + id_prestamo);
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
		return prestamo;
	}
	
	

	public int finalizarPrestamo(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		Calendar fechahoy = Calendar.getInstance();
		Calendar fechaVencimiento = Calendar.getInstance();
	
        Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
        int bandera = 0;
			
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(FinalizarID);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
			int plazo = getPrestamo(resultSet).getPlazo();
				
			Date fechaAlta = getPrestamo(resultSet).getFechaAlta();
			fechaVencimiento.setTime(fechaAlta);
			fechaVencimiento.add(Calendar.MONTH, plazo);
			
			
			
			
					if (fechahoy.after(fechaVencimiento)   ) {
						bandera = 1;
								statement.setString(1, "Finalizado");
						
					}
			
			
			}
		
		


		if(statement.executeUpdate() > 0)
		{
			System.out.println("El prestamo fue finalizado correctamente...");
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
		return bandera;
	}







}
