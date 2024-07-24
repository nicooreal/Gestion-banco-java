package Dao;

import Dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public interface iClienteDao {
	
	Cliente buscar_con_id(int id);
	List<Cliente> Listar();
	ArrayList<Cliente> ListarConEstadoFalse();
	int eliminarCliente(int id_cliente_borrar);
	int agregarCliente(Cliente clienteNuevo);
	int BajaLogicaCliente(int idClienteBaja);
	int ModificacionCliente(Cliente clienteModificar);

}
