package Dominio;

public class Telefono {
	
	public Telefono(String telefono) {
		this.telefono = telefono;
	}

	private String telefono;
	
	

	
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	
	public Telefono() {
		super();
	}

	@Override
	public String toString() {
		return "Telefono [telefono=" + telefono +"]";
	}
	
}
