package Dominio;

public class TiposCuenta {
	
	private int idTipoCuenta;
	private String tipoCuenta;

	public TiposCuenta() {
		
	}
	
	public TiposCuenta(int idTipoCuenta, String tipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
		this.tipoCuenta = tipoCuenta;
	}

	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String toString() {
		return tipoCuenta;
	}
	

}
