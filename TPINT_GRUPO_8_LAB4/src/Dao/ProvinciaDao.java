package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Dominio.Provincia;

public class ProvinciaDao implements iProvinciaDao {

	private static final String selectProvincia = "SELECT * FROM provincias WHERE id_provincia = ?";
	private static final String selectAllProvincias = "SELECT * FROM provincias";
	private static final String selectAllProvinciasConClientes = "SELECT * FROM vw_clientes_por_provincia";
	
	public ProvinciaDao() {
	}

	public Provincia getProvinciaConId(int id_provincia) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Provincia provincia = new Provincia();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectProvincia);
			statement.setInt(1,id_provincia);
			resultSet = statement.executeQuery();
			resultSet.next();
			provincia.setId_provincia(resultSet.getInt("id_provincia"));
			provincia.setNombre_provincia(resultSet.getString("provincia"));		
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
		
		return provincia;
	}

	@Override
	public ArrayList<Provincia> getListaProvincias() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> listadoProvincias = new ArrayList<Provincia>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAllProvincias);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoProvincias.add(getProvincia(resultSet));
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
		
		return listadoProvincias;
	}

	private Provincia getProvincia(ResultSet resultSet) {
		
		Provincia provincia = null;
		
		try {
			provincia = new Provincia();
			provincia.setId_provincia(resultSet.getInt("id_provincia"));
			provincia.setNombre_provincia(resultSet.getString("provincia"));	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return provincia;
	}
	
	public List<Provincia> getListaProvinciasConCantidadDeClientes() {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Connection conexion = null;
			PreparedStatement statement;
			ResultSet resultSet;
			ArrayList<Provincia> listadoProvincias = new ArrayList<Provincia>();
			
			
			try {
				conexion = conexionDB.getConnection();
				statement = conexion.prepareStatement(selectAllProvinciasConClientes);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					Provincia provincia = new Provincia();
					provincia.setId_provincia(resultSet.getInt("id_provincia"));
					provincia.setNombre_provincia(resultSet.getString("provincia"));
					provincia.setCantidad_clientes(Integer.parseInt(resultSet.getString("ClientesXProvincia")));
					listadoProvincias.add(provincia);
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
			
			return listadoProvincias;
		}
}
	