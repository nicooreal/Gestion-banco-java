package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Localidad;
import Dominio.Pais;

public class LocalidadDao {

	
	private static final String selectLocalidad = "SELECT * FROM localidades WHERE id_localidad = ?";
	private static final String selectAllLocalidad = "SELECT * FROM localidades";

	


	
	public Localidad getLocalidadConId(int id_localidad) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
	Localidad localidad = new Localidad();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectLocalidad);
			statement.setInt(1,id_localidad);
			resultSet = statement.executeQuery();
			resultSet.next();
			localidad.setIdLocalidad ((resultSet.getInt("id_localidad")));
			localidad.setLocalidad((resultSet.getString("localidad")));		
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
		
		return localidad;
	}


	public ArrayList<Localidad> getListaLocalidades() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> listadoLocalidad = new ArrayList<Localidad>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAllLocalidad);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoLocalidad.add(getLocalidad(resultSet));
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
		
		return listadoLocalidad;
	}

	private Localidad getLocalidad(ResultSet resultSet) {
		
		Localidad localidad= null;
		
		try {
			localidad = new Localidad();
			localidad.setIdLocalidad(resultSet.getInt("id_localidad"));
			localidad.setLocalidad(resultSet.getString("localidad"));	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return localidad;
	}
	
	
	
	
	
	
	
	
	
	
	
}
