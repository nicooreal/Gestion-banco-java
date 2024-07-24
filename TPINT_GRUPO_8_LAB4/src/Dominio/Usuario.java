package Dominio;



public class Usuario {
	
	enum ACCESO { Administrador, Cliente };
	enum ESTADO { True, False };
	
	private int id;
	private String usuario;
	private String contrasena;
	private ACCESO acceso;
	private Cliente cliente;
	private ESTADO estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getAcceso() {
		if ( this.acceso.equals(ACCESO.Administrador) ) {
			return "Administrador";		
		} else {
			return "Cliente";
		}
	}
	public void setAcceso(String acceso) {
		if (acceso.equals("Administrador")) {
			this.acceso = ACCESO.Administrador;		
		} else {
			this.acceso = ACCESO.Cliente;
		}
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getEstado() {
		if ( this.estado.equals(ESTADO.True) ) {
			return "True";		
		} else {
			return "False";
		}
	}
	public void setEstado(String estado) {
		if (estado.equals("True")) {
			this.estado = ESTADO.True;		
		} else {
			this.estado = ESTADO.False;
		}
	}
	
	public Usuario() {
		super();
		this.usuario = null;
		this.contrasena = null;
		this.acceso = null;
		this.cliente = null;
		this.estado = null;
	}
	
	public Usuario(String usuario, String contrasena, ACCESO acceso, Cliente cliente, ESTADO estado) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.acceso = acceso;
		this.cliente = cliente;
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", contrasena=" + contrasena + ", acceso=" + acceso
				+ ", cliente=" + cliente + ", estado=" + estado + "]";
	}

	/*
	public String getCbu() {
        if (this.cliente != null && !this.cliente.getCuentas().isEmpty()) {
            return this.cliente.getCuentas().get(0).getCbu();  // Retorna el CBU de la primera cuenta
        }
        return null; // Retorna null si no hay cliente o cuentas asociadas
    }
	*/
	
}
