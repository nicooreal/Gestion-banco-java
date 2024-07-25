package negocio;

import java.util.ArrayList;

import Dao.CuentaDao;
import Dominio.Cuenta;

public class NegocioCuenta {

	
	private CuentaDao cDao = new CuentaDao();
	
	
	public NegocioCuenta(CuentaDao cDao) {
		this.cDao = cDao;
	}
	public NegocioCuenta() {
	}
	
	public ArrayList<Cuenta> listarConTrue(){
		return (ArrayList<Cuenta>) cDao.ListarConEstadoTrue();
	}
	

}
