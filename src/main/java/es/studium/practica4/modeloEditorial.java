package es.studium.practica4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class modeloEditorial 
{

		
		private static DataSource pool;
		static ArrayList<Editorial> editoriales = new ArrayList<Editorial>(); 
		
		public static void listaEditoriales()  
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
					String sqlStr = "SELECT * FROM Editoriales ORDER BY nombreEditorial ASC";   
					ResultSet rs = stmt.executeQuery(sqlStr); 
					Editorial editorial;
					editoriales.clear();
					while(rs.next())
					{
						editorial = new Editorial(rs.getInt("idEditorial"), rs.getString("nombreEditorial"));
						editoriales.add(editorial);
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
		* Devuelve el número de autores obtenidos   
		*/  
		public static int tamano()  
		{   
			return editoriales.size();  
		}  
		/**   * Devuelve el nombre del autor identificado con idAutor   */  
		public static int getIdEditorial(int getIdEditorial)  
		{   
			return editoriales.get(getIdEditorial).getId();  
		} 
		
		/**   * Devuelve el nombre del autor identificado con idAutor   */  
		public static String getNombreEditorial(int idEditorial)  
		{   
			return editoriales.get(idEditorial).getNombreEditorial();  
		}  
}

