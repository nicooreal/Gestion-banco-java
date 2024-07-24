package Dao;

import java.sql.*;
import java.util.ArrayList;
import Dominio.Cliente;
import Dominio.ClienteSaldo;

public class ClienteSaldoDao {
    private static final String sp_buscar_saldos_mayores  = "CALL sp_buscar_saldos_mayores(?)";
    private static final String sp_buscar_saldos_menores  = "CALL sp_buscar_saldos_menores(?)";


    public ArrayList<ClienteSaldo> obtenerClientesConSaldoMayor(float saldoAComparar, boolean esMayor) {
    	        
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
			
			statement.setFloat(1,saldoAComparar);
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