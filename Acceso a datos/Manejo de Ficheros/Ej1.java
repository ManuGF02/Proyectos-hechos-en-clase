package Ejs1_4U1Package;

import java.io.*;

public class Ej1 {
	public static void main(String[] args)throws IOException {
		DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(new File("Departamentos.dat"), false));
		
		int numDptos[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String nombres[] = {"Ventas", "Producción", "Investigación", "Desarrollo", "Contabiolidad", "RRHH", "Marketing", "Relaciones Públicas", "Mejoras", "Reparación"};
		String localidades[] = {"Madrid", "VAlencia", "Barcelona", "Logroño", "Zaragoza", "Sevilla", "Granada", "Vigo", "Toledo", "España"};
		
		dataOS.writeUTF("DEPARTAMENTOS:");
		dataOS.writeUTF("\n");
		for(int i = 0; i<=localidades.length; i++){
			int a = numDptos[i];
			String b = nombres[i];
			String c = localidades[i];
			
			dataOS.writeUTF("Número del Dpto: ");
			dataOS.writeByte(a);
			dataOS.writeUTF("Nombre: ");
			dataOS.writeUTF(b);
			dataOS.writeUTF("Localidad: ");
			dataOS.writeUTF(c);
			dataOS.writeUTF("\n");
			
		}
	}
}

/**Realiza un programa Java que cree un fichero binario para guardar datos de departamentos, dale el nombre Departamentos.dat. Introduce varios departamentos.
Los datos por cada departamento son:

Número de departamento: entero.
Nombre: String.
Localidad: String.**/

