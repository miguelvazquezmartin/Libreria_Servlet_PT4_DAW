package es.studium.practica4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class modificarLibro {

	
	static ArrayList<LibroMod> modLibro = new ArrayList<LibroMod>();
	private static DataSource pool;
	
	public static void modLibro(int i)  
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
				String sqlStr = "SELECT Libros.*, Autores.*, Editoriales.* FROM Libros INNER JOIN Autores ON Libros.idAutorFK = Autores.idAutor INNER JOIN Editoriales ON Libros.idEditorialFK = Editoriales.idEditorial WHERE idLibro = " + i + ";";      
				ResultSet rs = stmt.executeQuery(sqlStr); 
				LibroMod modificarLibro; 
				//limpia la tabla cada vez que se carga para no tener elementos repetidos 
				modLibro.clear();
				while(rs.next())    
				{     
					modificarLibro = new LibroMod(rs.getInt("idLibro"), rs.getString("tituloLibro"), rs.getDouble("precioLibro"), rs.getInt("almacenLibro"), rs.getInt("idAutor"), rs.getString("nombreAutor"), rs.getInt("idEditorial"),rs.getString("nombreEditorial"));     
					modLibro.add(modificarLibro);  
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
		return modLibro.size();  
	}  
	/**   * Devuelve el id del libro identificado con idLibro   */  
	public static int getIdLibro(int idLibro)  
	{   
		return modLibro.get(idLibro).getId();  
	}  
	
	/**   * Devuelve el título del libro identificado con idLibro   */  
	public static String getTitulo(int idLibro)  
	{   
		return modLibro.get(idLibro).getTitulo();  
	}  
	
	/**   * Devuelve el precio del libro identificado con idLibro   */  
	public static double getPrecio(int idLibro)  
	{   
		return modLibro.get(idLibro).getPrecio();  
	} 
	
	/**   * Devuelve el autor del libro identificado con idLibro   */ 
	public static String getNombreAutor(int idLibro)  
	{   
		return modLibro.get(idLibro).getNombreAutor();  
	}
	
	/**   * Devuelve el la cantiad del libros en alamcen   */ 
	public static int getAlmacenLibro(int idLibro)  
	{   
		return modLibro.get(idLibro).getAlmacenLibro();  
	}
	/**   * Devuelve el la cantiad del libros en alamcen   */ 
	public static String getNombreEditorial(int idLibro)  
	{   
		return modLibro.get(idLibro).getNombreEditorial();  
	}
	public static int getIdAutor(int idLibro)  
	{   
		return modLibro.get(idLibro).getIdAutor();  
	}
	public static int getIdEditorial(int idLibro)  
	{   
		return modLibro.get(idLibro).getIdEditorial();  
	}
	public static void modLibro() {
		// TODO Auto-generated method stub
		
	}
	
	
}
			
				
				
			
		
		
			