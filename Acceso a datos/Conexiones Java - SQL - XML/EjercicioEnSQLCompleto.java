import java.sql.*;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EjercicioEnSQLCompleto {
	
	// Establecemos la conexion con la BD
	static Connection conexion;
	//Cargamos el Scanner
	static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			 conexion = DriverManager.getConnection("jdbc:mysql://localhost/Unidad2", "Unidad2",
						"Unidad2");
			System.out.println("Seleccione una acci�n");
			System.out.println("1) mysql");
			System.out.println("2) ORACLE");
			System.out.println("3) sqlite");
			int dato = sc.nextInt();

			switch (dato) {
			case 1:
			//----------------------------------
			//MYSQL
			//----------------------------------
				System.out.println("Seleccione acci�n");
				System.out.println("1) A�ADIR");
				System.out.println("2) CONSULTAR");
				System.out.println("3) BORRAR");
				dato = sc.nextInt();
				sc.nextLine();
				
				switch(dato) {
					case 1:
						System.out.println("Seleccione acci�n");
						System.out.println("1) A�ADIR PRODUCTO");
						System.out.println("2) A�ADIR CLIENTE");
						System.out.println("3) A�ADIR VENTA");
						dato = sc.nextInt();
						sc.nextLine();
						
						switch(dato){
							case 1:
								a�adirProducto();
								break;
							case 2:
								a�adirCliente();
								break;
							case 3:
								break;
							default:
								System.out.println("ERROR");
								System.exit(0);
								break;
						}
						break;
					case 2:
					//---------------------------------------------
					//CONSULTAR DATOS
					//---------------------------------------------
						System.out.println("Seleccione acci�n");
						System.out.println("1) VER PRODUCTOS");
						System.out.println("2) VER CLIENTES");
						System.out.println("3) VER VENTAS");
						dato = sc.nextInt();
						sc.nextLine();
						
						switch(dato) {
						case 1:
							consultarProductos();
							break;
						case 2:
							consultarClientes();
							break;
						case 3:
							consultarVentas();
							break;
						default:
							System.err.println("ERROR");
							System.exit(0);
							break;
						}
						break;
					case 3:
					//---------------------------------------------
					//BORRAR DATOS
					//---------------------------------------------
						System.out.println("Seleccione acci�n");
						System.out.println("1) BORRAR PRODUCTOS");
						System.out.println("2) BORRAR CLIENTES");
						System.out.println("3) BORRAR VENTAS");
						dato = sc.nextInt();
						sc.nextLine();
						
						switch(dato) {
						case 1:
							borrarProductos();
							
							
							break;
						case 2:
							
							borrarClientes();
							
							
							break;
						case 3:
							
							borrarVentas();
							
							
							break;
						default:
							
						}
						
						break;
					default:
						System.out.println("ERROR");
						System.exit(0);
						break;
				}
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("ERROR");
				System.exit(0);
				break;
			}

			conexion.close();
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void a�adirProducto(){
		try {
			// Cargar el driver		

			System.out.println("Inserta los siguientes Datos del nuevo PRODUCTO");
			System.out.println("ID-Num�rico");
			int id = sc.nextInt();
			sc.nextLine();
			
			String sql = "SELECT id " + "FROM PRODUCTOS " + "WHERE id = ?";
			System.out.println("BRUH");
			PreparedStatement sentenciaExisteIdProducto = conexion.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			sentenciaExisteIdProducto.setInt(1, id);
			ResultSet rs1 = sentenciaExisteIdProducto.executeQuery();
			if (rs1.last()) {
				System.out.println("El Producto ya existe");
				System.exit(0);

				rs1.close();
				sentenciaExisteIdProducto.close();
			}
			else {
				System.out.println("Descripci�n-String");
				String descripcion = sc.nextLine();
				System.out.println("Stock Actual-Num�rico");
				int stockActual = sc.nextInt();
				sc.nextLine();
				System.out.println("Stock Minimo-Num�rico");
				int StockMinimo = sc.nextInt();
				sc.nextLine();
				System.out.println("PVP-Num�rico");
				int pvp = sc.nextInt();
				sc.nextLine();
				String a�adirProducto = "INSERT INTO PRODUCTOS VALUES (?, ?, ?, ?, ?)";
				PreparedStatement statementA�adirProducto = conexion.prepareStatement(a�adirProducto);
				statementA�adirProducto.setInt(1, id);
				statementA�adirProducto.setString(2, descripcion);
				statementA�adirProducto.setInt(3, stockActual);
				statementA�adirProducto.setInt(4, StockMinimo);
				statementA�adirProducto.setInt(5, pvp);
	
				statementA�adirProducto.executeUpdate();
				
				conexion.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void a�adirCliente(){
		try{
			System.out.println("Inserta los datos del nuevo CLIENTE");
			System.out.println("ID Cliente: Num�rico");
			int id = sc.nextInt();
			sc.nextLine();
			
			String sql = "SELECT id " + 
						 "FROM CLIENTES " + 
						 "WHERE id = ?";
			PreparedStatement existeCliente = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			existeCliente.setInt(1, id);
			existeCliente.execute();
			System.out.println("HOLA");
			ResultSet rs1 = existeCliente.getResultSet();
			if(rs1.first()) {
				System.err.println("YA EXISTE ESTE EMPLEADO");
				
				rs1.close();
				existeCliente.close();
			}else {
				System.out.println("Nombre: String");
				String nombre = sc.nextLine();
				System.out.println("Direcci�n: String");
				String direccion = sc.nextLine();
				System.out.println("Poblaci�n: String");
				String poblacion = sc.nextLine();
				System.out.println("Tel�fono: String");
				String telefono = sc.nextLine();
				System.out.println("NIF: String");
				String nif = sc.nextLine();
				
				String sql2 = "INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement a�adirCliente = conexion.prepareStatement(sql2);
				a�adirCliente.setInt(1, id);
				a�adirCliente.setString(2, nombre);
				a�adirCliente.setString(3, direccion);
				a�adirCliente.setString(4, poblacion);
				a�adirCliente.setString(5, telefono);
				a�adirCliente.setString(6, nif);
				
				a�adirCliente.executeUpdate();
				
				a�adirCliente.close();
				rs1.close();
				existeCliente.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void a�adirVenta() {
		try{
			System.out.println("Inserta los datos de la nueva VENTA");
			System.out.println("ID Venta: Num�rico");
			//ID Venta
			int idVenta = sc.nextInt();
			sc.nextLine();
			
			String sql = "SELECT idventa " + 
						 "FROM VENTAS " + 
						 "WHERE idventa = ?";
			PreparedStatement existeVenta = conexion.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
			existeVenta.setInt(1, idVenta);
			
			ResultSet rs1 = existeVenta.getResultSet();
			if(rs1.last()) {
				System.err.println("YA EXISTE ESTA VENTA");
			}else {
				//FECHA DE VENTA
				System.out.println("Fecha de Venta: String");
				String fechaVenta = sc.nextLine();
				
				//ID Cliente
				System.out.println("ID Cliente: Num�rico");
				int idCliente = sc.nextInt();
				sc.nextLine();
				
				String sql1 = "SELECT idcliente " + 
						 	  "FROM CLIENTES " + 
						 	  "WHERE idcliente = ?";
				PreparedStatement existeCliente = conexion.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
				existeCliente.setInt(1, idCliente);
				
				ResultSet rs0 = existeCliente.getResultSet();
				if(rs0.last()) {
					System.err.println("YA EXISTE ESTE CLIENTE");
				}else {
					//ID Producto
					System.out.println("ID Producto: Num�rico");
					int idProducto = sc.nextInt();
					sc.nextLine();
					
					String sql2 = "SELECT idproducto " +
								  "FROM PRODUCTOS " + 
								  "WHERE idproducto = ?";
					PreparedStatement existeProducto = conexion.prepareStatement(sql2);
					ResultSet rs3 = existeProducto.getResultSet();
					
					if(rs3.last()) {
						System.err.println("ERROR");
						System.exit(0);
					}else {
						System.out.println("Cantidad: Num�rico");
						int cantidad = sc.nextInt();
						sc.nextLine();
						
						//VER VENTAS
						String sql4 = "INSERT INTO VENTAS VALUES (?, ?, ?, ?, ?)";
						PreparedStatement a�adirCliente = conexion.prepareStatement(sql4);
						a�adirCliente.setInt(1, idVenta);
						a�adirCliente.setString(2, fechaVenta);
						a�adirCliente.setInt(3, idCliente);
						a�adirCliente.setInt(4, idProducto);
						a�adirCliente.setInt(5, cantidad);
						
						a�adirCliente.executeUpdate();

						a�adirCliente.close();
						rs1.close();
						existeCliente.close();
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void consultarProductos() {
		try{
			conexion = conectarMySQL();
			
			String sql = "SELECT * FROM PRODUCTOS";
			PreparedStatement statementVerProductos = conexion.prepareStatement(sql);
			ResultSet rs = statementVerProductos.executeQuery(sql);
			
			System.out.println("|-----------------------PRODUCTOS------------------------|");
			System.out.println("|ID|DESCRIPCI�N|STOCK ACTUAL|STOCK M�NIMO|PVP\t|");
			System.out.println("|--|-----------|------------|------------|--------|");
			while (rs.next()) {
				System.out.printf(
						"%d |%s\t|%d\t|%d\t|%d   |\n--|-----------|------------|------------|--------|\n",
						rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
			
			rs.close();
			statementVerProductos.close();
			conexion.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void consultarClientes() {
		
		
		try {
			conexion = conectarMySQL();
			
			String sql = "SELECT * FROM CLIENTES";
			PreparedStatement statementVerEmpleados = conexion.prepareStatement(sql);
			ResultSet rs = statementVerEmpleados.executeQuery(sql);
			
			System.out.println("|--------------------------------------------CLIENTES-------------------------------------------------|");
			System.out.println("|ID|NOMBRE   |DIRECCI�N\t\t|POBLACI�N\t\t|TEL�FONO      |NIF\t\t|");
			System.out.println("|--|---------|-----------------------|-------------------|-------------|-------------|");
			while(rs.next()) {
				System.out.printf("%d |%s  |%s    \t\t|%s   \t\t|%s\t|%s\t\t|\n|--|---------|-----------------------|-------------------|-------------|-------------|", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
			
			rs.close();
			statementVerEmpleados.close();
			conexion.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void consultarVentas() {
		try {
			conexion = conectarMySQL();
			
			String sql = "SELECT * FROM VENTAS";
			PreparedStatement statementConsultarVentas = conexion.prepareStatement(sql);
			ResultSet rs = statementConsultarVentas.executeQuery(sql);
			
			System.out.println("|--------------VENTAS-----------------------------------|");
			System.out.println("|ID VENTA|FECHA DE VENTA|ID CLIENTE|ID PRODUCTO|CANTIDAD|");
			System.out.println("|--------|--------------|----------|-----------|--------|");
			while(rs.next()) {
				System.out.printf("%d     |%s\t|%d       |%d         |%d        |\n|--------|--------------|----------|-----------|--------|", rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
			
			rs.close();
			statementConsultarVentas.close();
			conexion.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void borrarProductos(){
		try{
			conexion = conectarMySQL();
			
			while(true) {
				System.out.println("Dime el ID del Producto a borrar");
				int dato = sc.nextInt();
				sc.nextLine();
				
				String sql1 = "SELECT id " + "FROM PRODUCTOS " + "WHERE id = ?";
				
				PreparedStatement sentenciaExisteIdProducto = conexion.prepareStatement(sql1,
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				sentenciaExisteIdProducto.setInt(1, dato);
				ResultSet rs1 = sentenciaExisteIdProducto.executeQuery();
				if(!rs1.last()) {
					System.err.println("Este Producto no existe");
				}else {
					String sql = "DELETE * FROM PRODUCTOS WHERE id = ?";
					PreparedStatement statementBorrarProductos = conexion.prepareStatement(sql);
					statementBorrarProductos.setInt(1, dato);
					
					statementBorrarProductos.executeUpdate(sql);
					
					System.out.println("Borrado CORRECTAMENTE");
					System.out.println("��Quieres Borrar un nuevo producto?? (SI/NO)");
					
					String respuesta = sc.nextLine();
					if(respuesta.toUpperCase() == "NO") {
						break;
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void borrarClientes(){
		try{
			conexion = conectarMySQL();
			
			while(true) {
				System.out.println("Dime el ID del Cliente a borrar");
				int dato = sc.nextInt();
				sc.nextLine();
				
				String sql1 = "SELECT id " + "FROM CLIENTES " + "WHERE id = ?";
				
				PreparedStatement sentenciaExisteIdCliente = conexion.prepareStatement(sql1,
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				sentenciaExisteIdCliente.setInt(1, dato);
				ResultSet rs1 = sentenciaExisteIdCliente.executeQuery();
				if(!rs1.last()) {
					System.err.println("Este Cliente no existe");
				}else {
					String sql = "DELETE * FROM CLIENTES WHERE id = ?";
					PreparedStatement statementBorrarClientes = conexion.prepareStatement(sql);
					statementBorrarClientes.setInt(1, dato);
					
					statementBorrarClientes.executeUpdate(sql);
					
					System.out.println("Borrado CORRECTAMENTE");
					System.out.println("��Quieres Borrar un nuevo cliente?? (SI/NO)");
					
					String respuesta = sc.nextLine();
					if(respuesta.toUpperCase() == "NO") {
						break;
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	public static void borrarVentas(){
		try{
			conexion = conectarMySQL();
			
			while(true) {
				System.out.println("Dime el ID de la VENTA a borrar");
				int dato = sc.nextInt();
				sc.nextLine();
				
				String sql1 = "SELECT id " + "FROM VENTAS " + "WHERE id = ?";
				
				PreparedStatement sentenciaExisteIdVenta = conexion.prepareStatement(sql1,
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				sentenciaExisteIdVenta.setInt(1, dato);
				ResultSet rs1 = sentenciaExisteIdVenta.executeQuery();
				if(!rs1.last()) {
					System.err.println("Esta VENTA no existe");
				}else {
					String sql = "DELETE * FROM VENTAS WHERE id = ?";
					PreparedStatement statementBorrarVentas = conexion.prepareStatement(sql);
					statementBorrarVentas.setInt(1, dato);
					
					statementBorrarVentas.executeUpdate(sql);
					
					System.out.println("Borrada CORRECTAMENTE");
					System.out.println("��Quieres Borrar una nueva Venta?? (SI/NO)");
					
					String respuesta = sc.nextLine();
					if(respuesta.toUpperCase() == "NO") {
						break;
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	
	public static Connection conectarMySQL() {
		try{
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/Unidad2", "Unidad2","Unidad2");	
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conexion;
	}
}

