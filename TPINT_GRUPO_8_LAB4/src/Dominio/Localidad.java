package Dominio;

public class Localidad {

	private int id;
	private String nombre;
	private int id_provincia;
	
	
	public Localidad(int id, String nombre, int id_provincia) {
        this.id = id;
        this.nombre = nombre;
        this.id_provincia = id_provincia;
    }
	
	public Localidad() {
    }
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int id_provincia() {
		return id_provincia;
	}
	public void id_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}
	
	
	@Override
	public String toString() {
		return "Localidad [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
	
	
	
}
