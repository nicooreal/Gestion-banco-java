package Dominio;

import Dominio.Prestamo.ESTADO;

public class Pago {
	public enum ESTADO { ATiempo, Vencido , Pagado};
	
	private int id;
	private java.util.Date fecha;
	private float importeApagar;
	private java.util.Date fechaVencimiento;
	private int idPrestamo;
	private int idCuenta;
	private int idCliente;
	private ESTADO estado;
	
	
	public void setEstado(String estado) {
		if (estado.equals("ATiempo")) {
			this.estado = ESTADO.ATiempo;		
		}
		if (estado.equals("Vencido")) {
			this.estado = ESTADO.Vencido;		
		}
	
		if (estado.equals("Pagado")) {
			this.estado = ESTADO.Pagado;		
		}	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public float getImporteApagar() {
		return importeApagar;
	}
	public void setImporteApagar(float importeApagar) {
		this.importeApagar = importeApagar;
	}
	public java.util.Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(java.util.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	@Override
	public String toString() {
		return "Pago [id=" + id + ", fecha=" + fecha + ", importeApagar=" + importeApagar + ", fechaVencimiento="
				+ fechaVencimiento + ", idPrestamo=" + idPrestamo + ", idCuenta=" + idCuenta + ", idCliente="
				+ idCliente + "]";
	}

	public ESTADO getEstado() {
		return estado;
	}

	
	
	
	
	
	
	
	
	
}
