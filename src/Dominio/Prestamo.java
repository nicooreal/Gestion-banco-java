package Dominio;

public class Prestamo {
	
	public enum ESTADO { Solicitado, Aprobado, Rechazado,Finalizado };
	private int idPrestamo;
	private java.util.Date fechaAlta;
	private Cliente cliente;
	private float importeApagar;
	private float importeSolicitado;
	private int plazo;
	private float montoMensual;
	private int cuotas;
	private int idCuentaDestino;
	private ESTADO estado;
	
	public Prestamo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prestamo(int idPrestamo, java.util.Date fecha, Cliente cliente, float importeApagar, float importeSolicitado,
			int plazo, float montoMensual, int cuotas, int idCuentaDestino, ESTADO estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.fechaAlta = fecha;
		this.cliente = cliente;
		this.importeApagar = importeApagar;
		this.importeSolicitado = importeSolicitado;
		this.plazo = plazo;
		this.montoMensual = montoMensual;
		this.cuotas = cuotas;
		this.idCuentaDestino = idCuentaDestino;
		this.estado = estado;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public java.util.Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(java.util.Date fecha) {
		this.fechaAlta = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getImporteApagar() {
		return importeApagar;
	}

	public void setImporteApagar(float importeApagar) {
		this.importeApagar = importeApagar;
	}

	public float getImporteSolicitado() {
		return importeSolicitado;
	}

	public void setImporteSolicitado(float importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public float getMontoMensual() {
		return montoMensual;
	}

	public void setMontoMensual(float montoMensual) {
		this.montoMensual = montoMensual;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public int getIdCuentaDestino() {
		return idCuentaDestino;
	}

	public void setIdCuentaDestino(int idCuentaDestino) {
		this.idCuentaDestino = idCuentaDestino;
	}

	public ESTADO getEstado() {
		return estado;
	}
	
		
	public void setEstado(String estado) {
		if (estado.equals("Solicitado")) {
			this.estado = ESTADO.Solicitado;		
		}
			if (estado.equals("Aprobado")) {
				this.estado = ESTADO.Aprobado;		
			}
		
			if (estado.equals("Rechazado")) {
				this.estado = ESTADO.Rechazado;		
			}	
			
			if (estado.equals("Finalizado")) {
				this.estado = ESTADO.Finalizado;		
			}
		
		}

		@Override
		public String toString() {
			return "Prestamo [idPrestamo=" + idPrestamo + ", fecha=" + fechaAlta + ", cliente=" + cliente
					+ ", importeApagar=" + importeApagar + ", importeSolicitado=" + importeSolicitado + ", plazo="
					+ plazo + ", montoMensual=" + montoMensual + ", cuotas=" + cuotas + ", idCuentaDestino="
					+ idCuentaDestino + ", estado=" + estado + "]";
		}




}
