package ar.edu.davinci.dvds20231cg4.domain;

public interface EstadoPrendaStrategy {

	public default int calcularPrecioVenta() {
		return 0;
		
	}
	
}
