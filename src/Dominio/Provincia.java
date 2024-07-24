package Dominio;

public class Provincia {
	
	private int id_provincia;
	private String nombre_provincia;
	private int cantidad_clientes;

	public Provincia() {
	}

	public int getId_provincia() {
		return id_provincia;
	}

	public int getCantidad_clientes() {
		return cantidad_clientes;
	}

	public void setCantidad_clientes(int cantidad_clientes) {
		this.cantidad_clientes = cantidad_clientes;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getNombre_provincia() {
		return nombre_provincia;
	}

	public void setNombre_provincia(String provincia) {
		this.nombre_provincia = provincia;
	}

	@Override
	public String toString() {
		return nombre_provincia;
	}

}
