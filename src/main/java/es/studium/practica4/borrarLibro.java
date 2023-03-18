package es.studium.practica4;

import java.sql.Connection;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class borrarLibro {

	private static DataSource pool;
	
	public static void dropLibro(int i)  
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
				String sqlStr = "DELETE FROM libros WHERE idLibro = " + i + ";";   
				boolean rs = stmt.execute(sqlStr); 
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

	public static void dropLibro() {
		// TODO Auto-generated method stub
		
	} 
}
