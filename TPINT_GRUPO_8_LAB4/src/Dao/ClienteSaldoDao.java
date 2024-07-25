package Dao;

import java.sql.*;
import java.util.ArrayList;
import Dominio.Cliente;
import Dominio.ClienteSaldo;
import servlets.clienteCreaTransferenciaServlet;

public class ClienteSaldoDao {
    private static final String sp_buscar_saldos_mayores  = "CALL sp_buscar_saldos_mayores(?, ?, ?)";
    private static final String sp_buscar_saldos_menores  = "CALL sp_buscar_saldos_menores(?, ?, ?)";


    public ArrayList<ClienteSaldo> obtenerClientesConSaldoMayor(float saldoAComparar, boolean esMayor, java.util.Date fechaInicio, java.util.Date fechaLimite) {
    	        
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	Connection conexion = null;
		CallableStatement statement;
		ResultSet resultSet;
		ArrayList<ClienteSaldo> listadoSaldosPorClientes = new ArrayList<ClienteSaldo>();

		
		
		try {
			conexion = conexionDB.getConnection();
			
			//pregunta si es para obtener mayor o no
			if(esMayor) statement = conexion.prepareCall(sp_buscar_saldos_mayores);
			else statement = conexion.prepareCall(sp_buscar_saldos_menores);
			
			
	        java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicio.getTime());
	        java.sql.Date sqlFechaLimite = new java.sql.Date(fechaLimite.getTime());
			
			
	        statement.setFloat(1, saldoAComparar);
	        statement.setDate(2, sqlFechaInicio);
	        statement.setDate(3, sqlFechaLimite);
			
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ClienteDao cd = new ClienteDao();
				Cliente cAux = cd.buscar_con_id(resultSet.getInt("id_cliente"));
				
				//se setea desde el constructor
				ClienteSaldo csAux = new ClienteSaldo(cAux, resultSet.getFloat("total_saldo"));
				
				listadoSaldosPorClientes.add(csAux);
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
		
		return listadoSaldosPorClientes;
    }
}