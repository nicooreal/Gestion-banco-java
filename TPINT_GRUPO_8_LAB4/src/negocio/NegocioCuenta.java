package negocio;

import java.util.ArrayList;

import Dao.CuentaDao;
import Dominio.Cuenta;

public class NegocioCuenta {

	
	private CuentaDao cuentaDao = new CuentaDao();
	
	
	public NegocioCuenta(CuentaDao cuentaDao) {
		this.cuentaDao = cuentaDao;
	}
	public NegocioCuenta() {
	}
	
	public ArrayList<Cuenta> listarConTrue(){
		return (ArrayList<Cuenta>) cuentaDao.ListarConEstadoTrue();
	}
	
	public long nuevoNumeroDeCuenta () {
		return cuentaDao.generarNumeroCuenta();
	}
	
	public long nuevoNumeroCbu() {
		CuentaDao cuentaDao = new CuentaDao();
		return cuentaDao.generarCbu();
	}
	
	public int cantidadRegistros() {
		CuentaDao cuentaDao = new CuentaDao();
		return cuentaDao.cantidadRegistros();
	}
	
	public int agregarCuenta(Cuenta cuentaNueva) {
		return cuentaDao.agregarCuenta(cuentaNueva);
	}
	
	public int altaCuenta (int id, int id_cliente) {
		return cuentaDao.AltaLogicaCuenta(id, id_cliente);
	}
	
	public int bajaCuenta (int id) {
		return cuentaDao.BajaLogicaCuenta(id);
	}
	
	public ArrayList<Cuenta> listarConFalse(){
		return (ArrayList<Cuenta>) cuentaDao.ListarConEstadoFalse();
	}
	
	public ArrayList<Cuenta> listar(){
		return (ArrayList<Cuenta>) cuentaDao.Listar();
	}
	
	

}
