package Dao;

import Dominio.Usuario;

public interface iUsuarioDao {
	
	Usuario Validar(String usuario, String contraseña);

	int agregarUsuario(Usuario usuarioNuevo);
	
}
