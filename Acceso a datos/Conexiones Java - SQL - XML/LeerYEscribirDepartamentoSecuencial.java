import java.io.*;
import java.util.*;

public class LeerYEscribirDepartamentoSecuencial {

	private static File fichero = new File("Departamentos.dat");
	
	private static int numsDepartamentos[] = {10, 20, 30, 40, 50};
	private static String nombres[] = {"Finanzas","Ausencias","RRHH","Ventas","Marketing"};
	private static String localizaciones[] = {"Haro", "París", "Londres", "New York", "Logroño"};
	private static int presupuestos[] = {23000, 12000, 34000, 50000, 10000};
	
	public static ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	
	public static void main(String[] args) throws IOException {
		try {
				System.out.println("Leyendo Datos...");
				leerDatos();
				Thread.sleep(1000);
				System.out.println("Escribiendo Datos...");
				Thread.sleep(1000);
				escribirDatos();
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public static void leerDatos() throws IOException {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero));
			for(int i = 0; i < numsDepartamentos.length; i++) {
				dos.writeInt(numsDepartamentos[i]);
				dos.writeUTF(nombres[i]);
				dos.writeUTF(localizaciones[i]);
				dos.writeInt(presupuestos[i]);
			}
			
			dos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("NO SE ENCUENTRA EL ARCHIVO");
			e.printStackTrace();
		}
	}
	
	public static void escribirDatos() throws IOException {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(fichero));
			
			int numDepartamento;
			String nombre;
			String localizacion;
			int presupuesto;
			
			
			
			for(int i = 0; i < numsDepartamentos.length; i++) {
				
				numDepartamento = dis.readInt();
				nombre = dis.readUTF();
				localizacion = dis.readUTF();
				presupuesto = dis.readInt();
				
				listaDepartamentos.add(new Departamento(numDepartamento, nombre, localizacion, presupuesto));
			} 
			
			for(Departamento departamento: listaDepartamentos) {
				System.out.println(departamento);
			}
			
			dis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("NO SE ENCUENTRA EL ARCHIVO");
		
			e.printStackTrace();
		}	
	}

}
