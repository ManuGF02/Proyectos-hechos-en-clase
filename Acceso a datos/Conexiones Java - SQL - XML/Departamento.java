
public class Departamento {
	
	int numero, presupuesto;
	String nombre, localizacion;
	
	public Departamento(int numero, String nombre, String localizacion, int presupuesto) {
		this.numero = numero;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.presupuesto = presupuesto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getLocalizacion() {
		return localizacion;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getPresupuesto() {
		return presupuesto;
	}
	
	public String toString() {
		return "NÚMERO: " + getNumero() + ". NOMBRE: " + getNombre() + ". LOCALIZACIÓN: " + getLocalizacion() + ". PRESUPUESTO: " + getPresupuesto();
	}
}
