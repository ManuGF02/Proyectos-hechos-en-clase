package Ejs1_4U1Package;

import java.io.*;

public class Ej1 {
	public static void main(String[] args)throws IOException {
		DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(new File("Departamentos.dat"), false));
		
		int numDptos[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String nombres[] = {"Ventas", "Producci�n", "Investigaci�n", "Desarrollo", "Contabiolidad", "RRHH", "Marketing", "Relaciones P�blicas", "Mejoras", "Reparaci�n"};
		String localidades[] = {"Madrid", "VAlencia", "Barcelona", "Logro�o", "Zaragoza", "Sevilla", "Granada", "Vigo", "Toledo", "Espa�a"};
		
		dataOS.writeUTF("DEPARTAMENTOS:");
		dataOS.writeUTF("\n");
		for(int i = 0; i<=localidades.length; i++){
			int a = numDptos[i];
			String b = nombres[i];
			String c = localidades[i];
			
			dataOS.writeUTF("N�mero del Dpto: ");
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

N�mero de departamento: entero.
Nombre: String.
Localidad: String.**/

