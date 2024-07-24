package Dominio;

public class Localidad {
    private int idLocalidad;
    private int idProvincia;
    private String localidad;

    // Constructor
    public Localidad(int idLocalidad, int idProvincia, String localidad) {
        this.idLocalidad = idLocalidad;
        this.idProvincia = idProvincia;
        this.localidad = localidad;
    }

    
    
    
    public Localidad() {
		super();
		// TODO Auto-generated constructor stub
	}




	// Getters y Setters
    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    // Método toString para una representación legible de la Localidad
    @Override
    public String toString() {
        return "Localidad{" +
                "idLocalidad=" + idLocalidad +
                ", idProvincia=" + idProvincia +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
