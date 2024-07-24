package Dominio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {
	
	public enum ESTADO { True, False };
	public enum SEXO { Masculino, Femenino };

	private int id; 
	private String dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private SEXO sexo;
	private Pais nacionalidad;
	private java.util.Date fechaNacimiento; 
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private String correoElectronico;
	private Telefono telefono1;
	private Telefono telefono2;
	private ArrayList<Cuenta> cuentas;
	private ESTADO estado;
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Pais getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public int getId() {
		return id;
	}
	public String getDni() {
		return dni;
	}
	public String getCuil() {
		return cuil;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public SEXO getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		if (sexo.equals("Masculino")) {
			this.sexo = SEXO.Masculino;		
		} else {
			this.sexo = SEXO.Femenino;
		}
	}
	//public String getNacionalidad() {
	//		return nacionalidad;
	//}
//public void setNacionalidad(String nacionalidad) {
	//		this.nacionalidad = nacionalidad;
		//	}
	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.util.Date dateNacimiento) {
		this.fechaNacimiento = dateNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Telefono getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(Telefono telefono1) {
		this.telefono1 = telefono1;
	}
	public Telefono getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(Telefono telefono2) {
		this.telefono2 = telefono2;
	}
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	public ESTADO getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		if (estado.equals("True")) {
			this.estado = ESTADO.True;		
		} else {
			this.estado = ESTADO.False;
		}
	}
	
	public Cliente() {
		super();
		this.dni = null;
		this.cuil = null;
		this.nombre = null;
		this.apellido = null;
		this.sexo = null;
		this.nacionalidad = null;
		this.fechaNacimiento = null;
		this.direccion = null;
		this.localidad = null;
		this.provincia = null;
		this.correoElectronico = null;
		this.telefono1 = null; // TO DO: Arreglar esto para inicializarlo como unla lista 
		this.telefono2 = null;
		this.cuentas = new ArrayList<>();
		
		this.estado = null;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", sexo=" + sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento
				+ ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia
				+ ", correoElectronico=" + correoElectronico + ", telefono1=" + telefono1 + ", telefono2=" + telefono2
				+ ", cuentas=" + cuentas + ", estado=" + estado + "]";
	}
	


	
}
