package es.studium.practica4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;  

/**
 * 
 *
 *   
 * Encapsula la comunicación con la base de datos  
 * Almacena títulos, autores y precios en tres tablas 
 * 
 */ 

public class claseModelo 
{   
	
	private static DataSource pool;
	
	public static void insertarFecha (String userNombre, LocalDate fecha) throws SQLException 
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
					String sqlStr = "SELECT idUsuario FROM Usuarios WHERE nombreUsuario='" + userNombre + "';";   
					ResultSet rs = stmt.executeQuery(sqlStr); 
					int	identificador = 0;
					while(rs.next())    
					{     
						identificador = rs.getInt("idUsuario"); 
					} 
					if(identificador != 0) {
						System.out.println("Estoy depues del while");
						String sqlInsert= "INSERT INTO pedidos (fechaPedido, idUsuarioFK) VALUES ('" + fecha + "', '" + identificador + "') ";   
						stmt.executeUpdate(sqlInsert);
					}else {
						throw new SQLException("El id del suario no está disponible");
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
}
				
		
