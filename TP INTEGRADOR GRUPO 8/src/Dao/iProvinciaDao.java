package Dao;

import java.util.List;

import Dominio.Provincia;

public interface iProvinciaDao {
	Provincia getProvinciaConId(int id_provincia);
	List<Provincia> getListaProvincias();
}
