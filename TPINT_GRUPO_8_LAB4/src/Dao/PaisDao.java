package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Pais;
import Dominio.Provincia;

public class PaisDao {

	private static final String selectPais = "SELECT * FROM paises WHERE id_pais = ?";
	private static final String selectAllPais = "SELECT * FROM paises";

	


	
	public Pais getPaisConId(int id_pais) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Pais pais= new Pais();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectPais);
			statement.setInt(1,id_pais);
			resultSet = statement.executeQuery();
			resultSet.next();
			pais.setIdPais((resultSet.getInt("id_pais")));
			pais.setNombre((resultSet.getString("nombre")));		
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
		
		return pais;
	}


	public ArrayList<Pais> getListaPaises() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Pais> listadoPais = new ArrayList<Pais>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAllPais);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoPais.add(getPais(resultSet));
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
		
		return listadoPais;
	}

	private Pais getPais(ResultSet resultSet) {
		
		Pais pais= null;
		
		try {
			pais = new Pais();
			pais.setIdPais(resultSet.getInt("id_pais"));
			pais.setNombre(resultSet.getString("nombre"));	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pais;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
