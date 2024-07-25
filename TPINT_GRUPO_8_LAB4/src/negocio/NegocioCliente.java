package negocio;

import java.util.ArrayList;

import Dao.ClienteDao;
import Dominio.Cliente;

public class NegocioCliente {

	
	private ClienteDao clienteDao = new ClienteDao();
	
	
	public NegocioCliente(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	public NegocioCliente() {
	}
	
	public ArrayList<Cliente> listarConTrue(){
		return (ArrayList<Cliente>) clienteDao.ListarConEstadoTrue();
	}
	

	
	
}
