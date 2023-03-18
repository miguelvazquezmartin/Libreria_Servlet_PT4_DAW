package es.studium.practica4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;  

/**
 * 
 *
 *   
 * Encapsula la comunicación con la base de datos  
 * Almacena títulos, autores y precios en tres tablas 
 * 
 */ 

public class Libreria 
{  
	static ArrayList<Libro> tabla = new ArrayList<Libro>();  
	
	private static DataSource pool;
	
	public static void cargarDatos()  
	{   
		// Creamos objetos para la conexión   
		Connection conn = null;   
		Statement stmt = null; 
		
		try   
		{    
			// Crea un contexto para poder luego buscar el recurso DataSource    
			InitialContext ctx = new InitialContext();    
			// Busca el recurso DataSource en el contexto    
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendaLibro");    
			if(pool == null)    
			{     
				    //aqui debe ir un mensaje de error
			}   
		}   
		catch(NamingException ex){} 
		
		try   
		{    
			// Obtener una conexión del pool    
			conn = pool.getConnection();    
			
			// Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement    
			stmt = conn.createStatement();    
			
			// Paso 4: Ejecutar las sentencias    
			String sqlStr = "SELECT Libros.*, Autores.nombreAutor FROM Libros INNER JOIN Autores ON Libros.idAutorFK = idAutor;";    
			ResultSet rs = stmt.executeQuery(sqlStr);    
			Libro libro; 
			
			//limpia la tabla cada vez que se carga para no tener elementos repetidos 
			tabla.clear();
			while(rs.next())    
			{     
				libro = new Libro(rs.getInt("idLibro"), rs.getString("tituloLibro"), rs.getString("nombreAutor"), rs.getDouble("precioLibro"), rs.getInt("almacenLibro"));     
				tabla.add(libro);  
			}   
		}   
		catch(Exception ex)   
		{    
			ex.printStackTrace();   
		}   
		finally   
		{    
			try    
			{     
				// Cerramos el resto de recursos     
				if(stmt != null)     
				{      
					stmt.close();     
				}     
				if(conn != null)     
				{      
					conn.close();     
				}    
			}    
			catch(Exception ex)    
			{     
				ex.printStackTrace();    
			}   
		}
	} 
	
	/**   
	* Devuelve el número de libros obtenidos   
	*/  
	public static int tamano()  
	{   
		return tabla.size();  
	}  
	/**   * Devuelve el id del libro identificado con idLibro   */  
	public static int getIdLibro(int idLibro)  
	{   
		return tabla.get(idLibro).getId();  
	}  
	
	/**   * Devuelve el título del libro identificado con idLibro   */  
	public static String getTitulo(int idLibro)  
	{   
		return tabla.get(idLibro).getTitulo();  
	}  
	
	/**   * Devuelve el precio del libro identificado con idLibro   */  
	public static double getPrecio(int idLibro)  
	{   
		return tabla.get(idLibro).getPrecio();  
	} 
	
	/**   * Devuelve el autor del libro identificado con idLibro   */ 
	public static String getNombreAutor(int idLibro)  
	{   
		return tabla.get(idLibro).getNombreAutor();  
	}
	
	/**   * Devuelve el la cantiad del libros en alamcen   */ 
	public static int getAlmacenLibro(int idLibro)  
	{   
		return tabla.get(idLibro).getAlmacenLibro();  
	}
}
			
				
				
			
		
		
			
		
	
