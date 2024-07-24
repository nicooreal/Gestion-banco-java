package Dominio;

public class ClienteSaldo {
	
	 private Cliente cliente;
	 private float saldoTotal;

	    public ClienteSaldo(Cliente cliente, float saldoTotal) {
	        this.cliente = cliente;
	        this.saldoTotal = saldoTotal;
	    }

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public void setCliente(Cliente cliente) {
	        this.cliente = cliente;
	    }

	    public float getSaldoTotal() {
	        return saldoTotal;
	    }

	    public void setSaldoTotal(float saldoTotal) {
	        this.saldoTotal = saldoTotal;
	    }
	    
	    @Override
	    public String toString() {
	        return "ClienteSaldo{idCliente=" + cliente + ", saldoTotal=" + saldoTotal + "}";
	    }
}

