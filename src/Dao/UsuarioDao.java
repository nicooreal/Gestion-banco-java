package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Usuario;

public class UsuarioDao implements iUsuarioDao {
	
	private static final String selectValidate = "SELECT `usuarios`.`id_usuario`, `usuarios`.`usuario`, `usuarios`.`contrasenia`, `usuarios`.`acceso`, `usuarios`.`id_cliente`, `usuarios`.`estado` FROM `bd_banco`.`usuarios` WHERE `usuarios`.`usuario` = ? AND `usuarios`.`contrasenia` = ?;";
	private static final String insertUsuario = "INSERT INTO `bd_banco`.`usuarios`(usuario, contrasenia, acceso, id_cliente, estado) VALUES (?, ?, ?, ?, ?)";

	
	
	public int agregarUsuario(Usuario usuarioNuevo) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    int filas = 0;
	    
	    try {
	        conexion = conexionDB.getConnection();
	        
	        statement = conexion.prepareStatement(insertUsuario);
	        statement.setString(1, usuarioNuevo.getUsuario());
	        statement.setString(2, usuarioNuevo.getContrasena());
	        statement.setString(3, usuarioNuevo.getAcceso());
	        statement.setInt(4, usuarioNuevo.getCliente().getId());
	        statement.setString(5, usuarioNuevo.getEstado());

	        if(statement.executeUpdate() > 0) {
	            filas = 1;
	        }
	    }catch (SQLException e) 
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
	
	
	
	public Usuario Validar(String usuarioAux, String contrasenia) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Usuario usuario = null;
		
		try 
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectValidate);
			statement.setString(1, usuarioAux);
			statement.setString(2, contrasenia);
			resultSet = statement.executeQuery();
			resultSet.next();
			usuario = getUsuario(resultSet);				

		} 
		catch (SQLException e) 
		{
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
		return usuario;
	}
	
	private Usuario getUsuario(ResultSet resultSet) {
		
		Usuario usuario = null;
		Cliente cliente = null;
		ClienteDao clienteDao;
		CuentaDao cuentaDao;
		ArrayList<Cuenta> cuentasDeCliente = new ArrayList<>();
		
		try {
			usuario = new Usuario();
			usuario.setId(resultSet.getInt("id_usuario"));
			usuario.setUsuario(resultSet.getString("usuario"));
			usuario.setContrasena(resultSet.getString("contrasenia"));
			usuario.setAcceso(resultSet.getString("acceso"));
			int id_cliente = resultSet.getInt("id_cliente");
			
			if (id_cliente != 0 ) {
				
				clienteDao = new ClienteDao();
				cliente = clienteDao.buscar_con_id(id_cliente);
				cuentaDao = new CuentaDao();
				cuentasDeCliente =  (ArrayList<Cuenta>)cuentaDao.getListaCuentasPorCliente(id_cliente);
				cliente.setCuentas(cuentasDeCliente);
				
				
				
				usuario.setCliente(cliente);				
			
		
				
				
			
			} else {
				usuario.setCliente(null);
			}
			usuario.setEstado(resultSet.getString("estado"));

		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
