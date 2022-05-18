package Ejs1_4U1Package;

import java.io.*;
import java.util.Scanner;

public class Ejs2a4 {
			private static File ficheroPrimero = new File("Departamentos.dat");
			private static File ficheroAuxiliar = new File("DepartamentosAux.dat");	    
			
			public static void main(String[] args) throws IOException{
				if (!ficheroPrimero.exists()) {
					ficheroPrimero.createNewFile();
					String nombres[] = {"Ventas1", "Producción2", "Investigación3", "Desarrollo4", "Contabiolidad5"};
					String localidades[] = {"Haro", "Logroño", "Zaragoza", "Teruel", "Valencia"};
					DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(ficheroPrimero));
					for (int i=0;i<nombres.length;i++) {
						dataOS.writeInt(i+1);
						dataOS.writeUTF(nombres[i]);
						dataOS.writeUTF(localidades[i]);
					}
					dataOS.close();		
				}
			   
			    //menu
				System.out.println("Elije una opción de las siguientes:");
				System.out.println("1- Modificar departamento");
				System.out.println("2- Borrar departamento");
				System.out.println("3- Copiar contenido de archivos");
				
				Scanner opcion = new Scanner(System.in);
				if(opcion.hasNextInt()){
					int numeroDep = opcion.nextInt();
					
					switch(numeroDep) {
						// EJ 2 
						case 1:
							System.out.println("Porfavor introduzca el ID del departamento:");
							int ID = opcion.nextInt();
							
							System.out.println("Porfavor introduzca el nuevo nombre del departamento:");
							String nuevoNombre = opcion.next();
							
							System.out.println("Porfavor introduzca la nueva localidad del departamento:");
							String nuevaCiudad = opcion.next();
							
							modificarDatos(ID, nuevoNombre, nuevaCiudad);		 				
							break;
						// EJ 3 	
						case 2:
							System.out.println("Porfavor introduzca el ID del departamento a eliminar:");
							int ID2 = opcion.nextInt();
							
							eliminarDatos(ID2);
							break; 
						// EJ 4 
						case 3:
							System.out.println("Introduce el nombre del fichero1:");
							String fichero1 = opcion.nextLine();
							System.out.println("Introduce el nombre del fichero2:");
							String fichero2 = opcion.nextLine();
							copiarArchivos(fichero1, fichero2);
							break;
							
					}
				}

				opcion.close();
			}
			
			
			//readUTF()
			private static boolean existeDepartamento(int id) throws IOException {
				boolean existe = false;	
				DataInputStream dataIS = new DataInputStream(new FileInputStream(ficheroPrimero));
				try {
				    while (true && !existe) {
				    	if (dataIS.readInt() == id)
			        	existe = true;
		    		}
			    } catch (EOFException eo) {
			    	System.out.println("Fin fichero");
			    }
				   dataIS.close();
				   return existe;
			}
			
			private static void copiarArchivos(String arch1, String arch2) throws IOException {
				File fich1 = new File(arch1);
				File fich2 = new File(arch2);
				if (fich1.exists()) {
					try {
						DataInputStream dataIN = new DataInputStream(new FileInputStream(fich1));
						DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(fich2));
						int x;
						while ((x = dataIN.read()) != -1) {
							dataOS.write(x);
						}
						dataIN.close();
						dataOS.flush();
						dataOS.close();
						System.out.println("Fichero copiado.");
					} catch (EOFException eo) {
				    	System.out.println("Fin fichero");
			    }
				} else {
					System.out.println("Fichero no existe. Pruebe a crearlo otra vez.");
				}
			}
			
			private static void modificarDatos(int id, String nombre, String Ciudad) throws IOException{
			   if (existeDepartamento(id)) {	
				    DataInputStream dataIN = new DataInputStream(new FileInputStream(ficheroPrimero));
					DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(ficheroAuxiliar));
					int numDep;
				   try {		
					   while (true) {
						    numDep =  dataIN.readInt();
						    dataOS.writeInt(numDep);
		
					        if (numDep == id) 
					        {
					        	dataOS.writeUTF(nombre);
					        	dataIN.readUTF();
					        	dataOS.writeUTF(Ciudad);
					        	dataIN.readUTF();
					        } else {	
					        	dataOS.writeUTF(dataIN.readUTF()); 
					        	dataOS.writeUTF(dataIN.readUTF()); 
					        }				 					        
					    }	
				    } catch (EOFException eo) {
					    	System.out.println("Fin del fichero");
				    }
				    dataIN.close();
				    dataOS.flush();
				    dataOS.close();
				    if (ficheroPrimero.delete())
				 	   ficheroAuxiliar.renameTo(ficheroPrimero);
				    else
				 	   System.out.println("No se ha borrado");
				} else {
					System.out.println("No existe el departamento que buscas");
				}
			}
			
				
			/*
			 * Elimina la informacion de un departamento poniendolo a -1
			 * @param id del departamento
			 */
			private static void eliminarDatos(int id) throws IOException {
				if (existeDepartamento(id)) {	
				    DataInputStream carga = new DataInputStream(new FileInputStream(ficheroPrimero));
					DataOutputStream dataAux = new DataOutputStream(new FileOutputStream(ficheroAuxiliar));
					int numDep;
				   try {		
					   while (true) {
						    numDep = carga.readInt();
		
					        if (numDep == id && numDep != -1) 
					        {
					        	dataAux.writeInt(-1);
					        	dataAux.writeUTF("borrado");
					        	carga.readUTF();
					        	dataAux.writeUTF("borrado");
					        	carga.readUTF();
					        } else {	
					        	dataAux.writeInt(numDep);
						        dataAux.writeUTF(carga.readUTF()); 
						        dataAux.writeUTF(carga.readUTF()); 
					        }				 					        
					    }	
				    } catch (EOFException eo) {
					    	System.out.println("Fin fichero");
				    }
				    carga.close();
				    dataAux.flush();
				    dataAux.close();
				    if (ficheroPrimero.delete())
				 	   ficheroAuxiliar.renameTo(ficheroPrimero);
				    else
				 	   System.out.println("Borrado no satisfactorio");
				} else {
					System.out.println("No existe departamento con ese ID!");
				}
			}
		
			
}
