package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dominio.TipoMovimiento;

public class TipoMovimientoDao {

	
	private static final String selectTipoMovimiento = "SELECT * FROM tipos_movimiento WHERE id_tipomovimiento = ?";

	public TipoMovimientoDao() {
	}

	public TipoMovimiento getTipoMovimiento(int idTipoMovimiento) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectTipoMovimiento);
			statement.setInt(1,idTipoMovimiento);
			resultSet = statement.executeQuery();
			resultSet.next();
			tipoMovimiento.setId(resultSet.getInt("id_tipomovimiento"));
			tipoMovimiento.setDescripcion(resultSet.getString("tipo_movimiento"));			
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
		
		return tipoMovimiento;
	}

	
}
