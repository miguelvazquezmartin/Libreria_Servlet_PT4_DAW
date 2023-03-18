package es.studium.practica4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class modeloPedidos 
{

	private static DataSource pool;
	
	static ArrayList<pedidos> pedido = new ArrayList<pedidos>(); 
	
	public static void listaPedidos()  
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
				String sqlStr = "SELECT * FROM Pedidos ORDER BY fechaPedido";   
				ResultSet rs = stmt.executeQuery(sqlStr); 
				pedidos newPedido;
				pedido.clear();
				while(rs.next())
				{
					newPedido = new pedidos(rs.getInt("idPedido"),rs.getDate("fechaPedido"),rs.getDate("fechaEnvioPedido"), rs.getString("idUsuarioFK"));
					pedido.add(newPedido);
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
	* Devuelve el número de pedidos obtenidos   
	*/  
	public static int tamano()  
	{   
		return pedido.size();  
	}  
	/**   * Devuelve la id del pedido identificado con   */  
	public static int getIdPedido(int idPedido)  
	{   
		return pedido.get(idPedido).getId();  
	} 
	
	/**   * Devuelve el dato de fecha del pedido identificado con idPedido   */  
	public static Date getFechaPedido(int idPedido)  
	{   
		return pedido.get(idPedido).getFechaPedido();  
	}  
	/**   * Devuelve el dato de fecha del pedido identificado con idPedido   */  
	public static Date getFechaEnvioPedido(int idPedido)  
	{   
		return pedido.get(idPedido).getFechaEnvioPedido();  
	}  
	public static String getUsuarioPedido (int idPedido)  
	{   
		return pedido.get(idPedido).getUsuarioPedido();  
	}
}
