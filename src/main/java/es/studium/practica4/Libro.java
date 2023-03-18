package es.studium.practica4;

/**  
 *   
 * @author Miguel  
 * Libro  
 * Representa un libro  
 * Incluye identificador del libro, título, precio y autor.  
 * 
*/ 
public class Libro 
{  
	private int id;  
	private String titulo;  
	private String nombreAutor;  
	private double precio; 
	private int almacenLibro;
	
	
	public Libro()  
	{   
		setId(0);   
		titulo = ""; 
		nombreAutor = ""; 
		precio = 0.0;
		almacenLibro=0;
	}
	
	public Libro(int i, String t, String a, double p, int al)  
	{   
		setId(i);   
		titulo = t; 
		nombreAutor = a;
		precio = p;
		almacenLibro = al;
		
	}   

	public String getTitulo()  
	{   
		return titulo;  
	}
	
	public String getNombreAutor()  
	{   
		return nombreAutor;  
	} 
	
	public double getPrecio()  
	{   
		return precio;  
	} 
	
	public int getAlmacenLibro()  
	{   
		return almacenLibro;  
	}
	
	public int getId()  
	{   
		return id;  
	}  
	
	public void setId(int id)  
	{   
		this.id = id;  
	} 
}
