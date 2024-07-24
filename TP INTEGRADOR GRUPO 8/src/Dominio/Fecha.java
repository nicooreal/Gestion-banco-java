package Dominio;

public class Fecha {
public int dia;
public int mes;
public int anio;




public Fecha() {
	super();

}
public Fecha(int dia, int mes, int anio) {
	super();
	this.dia = dia;
	this.mes = mes;
	this.anio = anio;
}
public int getDia() {
	return dia;
}
public void setDia(int dia) {
	this.dia = dia;
}
public int getMes() {
	return mes;
}
public void setMes(int mes) {
	this.mes = mes;
}
public int getAnio() {
	return anio;
}
public void setAnio(int anio) {
	this.anio = anio;
}
@Override
public String toString() {
	return "Fecha [dia=" + dia + ", mes=" + mes + ", anio=" + anio + "]";
}




}
