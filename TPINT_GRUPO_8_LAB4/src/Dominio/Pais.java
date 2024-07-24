

	package Dominio;

	public class Pais {
	    
		private int idPais;
	    private String nombre;


	    public Pais() {
	    }


	    public Pais(int idPais, String nombre) {
	        this.idPais = idPais;
	        this.nombre = nombre;
	    }


	    public int getIdPais() {
	        return idPais;
	    }

	    public void setIdPais(int idPais) {
	        this.idPais = idPais;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }


	    @Override
	    public String toString() {
	        return "Pais{" +
	                "idPais=" + idPais +
	                ", nombre='" + nombre + '\'' +
	                '}';
	    }


	    @Override
	    public int hashCode() {
	        int result = idPais;
	        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;

	        Pais pais = (Pais) obj;

	        if (idPais != pais.idPais) return false;
	        return nombre != null ? nombre.equals(pais.nombre) : pais.nombre == null;
	    }
	
	
	}

	

	
	
	
	
	
	
	
	
	
	
	
	

