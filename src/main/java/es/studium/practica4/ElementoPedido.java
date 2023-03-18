package es.studium.practica4;

/**  
 *    
 * @author Jorge  
 * ElementoPedido
 * Representa un elemento del pedido  
 * Incluye identificador del libro y cantidad
 *   
 */ 

public class ElementoPedido 
{  
	private int idLibro;  
	private int cantidad;    
	
	public ElementoPedido(int idLibro, int cantidad)  
	{   
		this.idLibro = idLibro;   
		this.cantidad = cantidad;  
	}    
	
	public int getIdLibro()  
	{   
		return idLibro;  
	}  
	
	public void setIdLibro(int idLibro)  
	{   
		this.idLibro = idLibro;  
	}  
	
	public int getCantidad()  
	{   
		return cantidad;  
	} 
	
	public void setCantidad(int cantidad)  
	{   
		this.cantidad = cantidad;  
	}  
	
	public String getAutor()  
	{   
		return Libreria.getNombreAutor(idLibro);  
	}  
	
	public String getTitulo()  
	{   
		return Libreria.getTitulo(idLibro);  
	}
	
	public double getPrecio()  
	{   
		return Libreria.getPrecio(idLibro);  
	} 
} 
	
	
	
	
	
	

