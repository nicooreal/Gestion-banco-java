package Dominio;

import java.util.Date;

public class Movimiento{
	
	private int id;
	private java.util.Date fecha;
	private String concepto;
	private float importe;
	private TipoMovimiento tipo_movimiento;
	private int id_cuenta_origen;
	private int id_cuenta_destino;
	
	//getters y setters
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
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public TipoMovimiento getTipoMovimiento() {
		return tipo_movimiento;
	}
	public void setTipoMovimiento(TipoMovimiento tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	public int getId_cuenta_origen() {
		return id_cuenta_origen;
	}
	public void setId_cuenta_origen(int id_cuenta_origen) {
		this.id_cuenta_origen = id_cuenta_origen;
	}
	public int getId_cuenta_destino() {
		return id_cuenta_destino;
	}
	public void setId_cuenta_destino(int id_cuenta_destino) {
		this.id_cuenta_destino = id_cuenta_destino;
	}

	//to string
	public String toString() {
		return "Movimientos [id_movimiento=" + id + ", fecha=" + fecha + ", concepto=" + concepto
				+ ", importe=" + importe + ", id_tipo_movimiento=" + tipo_movimiento + ", id_cuenta_origen="
				+ id_cuenta_origen + ", id_cuenta_destino=" + id_cuenta_destino + "]";
	}
	
	public Movimiento(int id, Date fecha, String concepto, float importe, TipoMovimiento tipo_movimiento, int id_cuenta_origen,
			int id_cuenta_destino) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.concepto = concepto;
		this.importe = importe;
		this.tipo_movimiento = tipo_movimiento;
		this.id_cuenta_origen = id_cuenta_origen;
		this.id_cuenta_destino = id_cuenta_destino;
	}
	
	public Movimiento() {
		super();
	}
	
	
	
	
}
