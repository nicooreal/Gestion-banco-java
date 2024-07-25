package negocio;

import java.util.ArrayList;

import Dao.ClienteDao;
import Dominio.Cliente;


public class NegocioCliente  {

	
	private ClienteDao clienteDao = new ClienteDao();
	
	
	public NegocioCliente(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	public NegocioCliente() {
	}
	
	public ArrayList<Cliente> listarConTrue(){
		return (ArrayList<Cliente>) clienteDao.ListarConEstadoTrue();
	}
	
	public ArrayList<Cliente> listarConFalse(){
		return (ArrayList<Cliente>) clienteDao.ListarConEstadoFalse();
	}
	
	public ArrayList<Cliente> listar() {
		return (ArrayList<Cliente>) clienteDao.Listar();
	}
	
 public int agregarCliente(Cliente clienteNuevo, String nuevaContra) {
	 return clienteDao.agregarCliente(clienteNuevo, nuevaContra);
 }


 public int bajaLogicaCliente( int idParaBorrar) {
	 return  clienteDao.BajaLogicaCliente(idParaBorrar) ;
 }

 public boolean existeDni(String dni) {
	 
	return clienteDao.existeDni(dni);
 }
 
 public boolean existeDni(String dni, int idParamodificar) {
	 
	return clienteDao.existeDni(dni, idParamodificar);
 }
 
 
 public int ModificacionCliente(Cliente clienteModificar) {
 
	 return clienteDao.ModificacionCliente(clienteModificar);
}
 
public int ModificacionTelefonos(String telefonoModificado1, String telefonoModificado2, int id_cliente) {
 
return clienteDao.ModificacionTelefonos(telefonoModificado1, telefonoModificado2, id_cliente);

}
 
public int AltaLogicaCliente(int idClienteAlta) {

return clienteDao.AltaLogicaCliente(idClienteAlta);

}

public Cliente buscar_con_id(int id) {

return clienteDao.buscar_con_id(id);
	
}
 
}



