package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dominio.TiposCuenta;

public class TiposCuentaDao implements iTiposCuentaDao {
	
	private static final String selectTipoCuenta = "SELECT * FROM tipos_cuenta WHERE id_tipocuenta = ?";

	public TiposCuentaDao() {
	}

	@Override
	public TiposCuenta getTipoCuenta(int idTipoCuenta) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		TiposCuenta tipoCuenta = new TiposCuenta();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectTipoCuenta);
			statement.setInt(1,idTipoCuenta);
			resultSet = statement.executeQuery();
			resultSet.next();
			tipoCuenta.setIdTipoCuenta(resultSet.getInt("id_tipocuenta"));
			tipoCuenta.setTipoCuenta(resultSet.getString("tipo_cuenta"));			
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
		
		return tipoCuenta;
	}

}
