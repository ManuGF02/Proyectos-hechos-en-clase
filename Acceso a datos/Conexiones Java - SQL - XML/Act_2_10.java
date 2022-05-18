import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Act_2_10 {
	public static void main(String[] args) {
		  try
		  {
			  Scanner scanner = new Scanner(System.in);
			  
			  System.out.println("Introduce un número de Empleado:");
			  int numEmpleado = 0;
			  numEmpleado = scanner.nextInt();
			  System.out.println("\nIntroduce un Apellido:\n");
			  String apellido = "";
			  apellido = scanner.nextLine();
			  System.out.println("\nIntroduce un Oficio:\n");
			  String oficio = "";
			  oficio = scanner.nextLine();
			  System.out.println("\nIntroduce un número de Jefe:\n");
			  int jefe = 0;
			  jefe = scanner.nextInt();
			  System.out.println("\nIntroduce una fecha de Alta:\n");
			  String fechaAlta = "29/09/2002";
			  fechaAlta = scanner.nextLine();
			  System.out.println("\nIntroduce un Sueldo:\n");
			  int sueldo = 0;
			  sueldo = scanner.nextInt();
			  System.out.println("\nIntroduce una Comisión:\n");
			  int comision = 0;
			  comision = scanner.nextInt();
			  System.out.println("\nIntroduce un número de Departamento:\n");
			  int numDepartamento = 0;
			  numDepartamento = scanner.nextInt();
			  
			  
			 //Cargar el driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 // Establecemos la conexion con la BD
			 Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/ejemplo","root", "root1234");  
			 
			 
			 String sql1 = "SELECT deptno " + 
					 	  "FROM dept " + 
					 	  "WHERE deptno = ?";
			 PreparedStatement sentenciaExisteDept = conexion.prepareStatement(sql1);
			 sentenciaExisteDept.setInt(1, numDepartamento);
		     ResultSet rset1 = sentenciaExisteDept.executeQuery();
		     if(rset1.first() == true) {
		    	 System.out.println("No existe Dicho departamento");
		    	 System.exit(0);
		     }else{
		    	 //Siguiente Condición
		    	 String existeNumeroEmpleado = "SELECT empno " +
		    	 							   "FROM emp " +
		    	 							   "WHERE empno = ?";
		    	 PreparedStatement sentenciaExisteNumEmpleado = conexion.prepareStatement(existeNumeroEmpleado);
		    	 sentenciaExisteNumEmpleado.setInt(1, numEmpleado);
		    	 ResultSet rs2 = sentenciaExisteNumEmpleado.executeQuery();
		    	 if (rs2.first() == true) {
		    		 System.out.println("El número de empleado ya existe");
		    		 System.exit(0);
		    	 }else {
		    		 String sentenciaExisteJefe = "SELECT jefe " +
		    				 					  "FROM emp " + 
		    				 					  "WHERE jefe = ?";
		    		 PreparedStatement sentenciaJefe = conexion.prepareStatement(sentenciaExisteJefe);
		    		 sentenciaJefe.setInt(1, jefe);
		    		 ResultSet rs3 = sentenciaJefe.executeQuery();
		    		 if(rs3.first() == true) {
		    			 //String crearEmpleado = "INSERT INTO emp VALUES (" + numEmpleado + ", '"+ apellido + "', '" + oficio + "', " + jefe + ", str_to_date('"+ fechaAlta + "', '%d/%m/%y'), " + sueldo + ", " + comision + ", " + numDepartamento + ")";
		    			 
		    			 String crearEmpleado = "INSERT INTO emp VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    			 PreparedStatement creacionEmpleado = conexion.prepareStatement(crearEmpleado);
		    			 
		    			 creacionEmpleado.setInt(1, numEmpleado);
		    			 creacionEmpleado.setString(2,  apellido);
		    			 creacionEmpleado.setString(3, oficio);
		    			 creacionEmpleado.setInt(4, jefe);
		    			 creacionEmpleado.setTimestamp(5, Timestamp.valueOf(fechaAlta));
		    			 creacionEmpleado.setInt(6, sueldo);
		    			 creacionEmpleado.setInt(7, comision);
		    			 creacionEmpleado.setInt(8, numDepartamento);
		    			 
		    			 int filas = creacionEmpleado.executeUpdate();
		    		 }else {
		    			 System.out.println("El Jefe no existe");
		    			 System.exit(0);
		    		 }
		    	 }
		    	 
		     }
			 
			 /*
			 //recuperar parametros de main  
			 String dep=args[0];	
			 String dnombre=args[1];
			 String loc=args[2];		
			//construir orden INSERT
			 String sql= "INSERT INTO departamentosS VALUES ( ?, ?, ?)";
			 System.out.println(sql);
			 // Preparamos la sentencia
			 PreparedStatement sentencia = conexion.prepareStatement(sql);
			 
			 sentencia.setInt(1,Integer.parseInt(dep));
			 sentencia.setString(2,dnombre);
			 sentencia.setString(3,loc);
			 
			 int filas = sentencia.executeUpdate();  
			 System.out.println("Filas afectadas: "+filas); 
			 
			 		 
			
	  	     // Cerrar Statement
			 sentencia.close();
			 */
	 	     //Cerrar conexion
			 conexion.close();   
	    }		  	   
		 catch (ClassNotFoundException cn) {cn.printStackTrace();} 
	     catch (SQLException e) {e.printStackTrace();}		

		}//fin de main
}
