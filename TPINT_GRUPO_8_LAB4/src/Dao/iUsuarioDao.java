package Dao;

import Dominio.Usuario;

public interface iUsuarioDao {
	
	Usuario Validar(String usuario, String contraseņa);

	int agregarUsuario(Usuario usuarioNuevo);
	
}
