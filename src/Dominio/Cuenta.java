package Dominio;

public class Cuenta {
	
	
	public enum ESTADO { True, False };
	private ESTADO estado;
	private int idCuenta;
	private float saldo;
	private java.util.Date fecha;
	private String cbu;
	private TiposCuenta tipoCuenta;
	private Cliente cliente;
	private long numeroCuenta;
	
  public long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

public Cuenta(ESTADO estado, int idCuenta, float saldo, java.util.Date fecha, TiposCuenta tipoCuenta, String cbu) {
        this.estado = estado;
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.fecha = fecha;
        this.tipoCuenta = tipoCuenta;
        this.cbu = cbu;
    }
	
	public Cuenta() {
	
	}
  
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public TiposCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoDeCuenta(TiposCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
	
}
