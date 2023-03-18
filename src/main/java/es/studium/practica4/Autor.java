package es.studium.practica4;

public class Autor 
{
		private int id;  
		private String nombreAutor;  
		
		
		public Autor()  
		{   
			setId(0);   
			nombreAutor = ""; 
		}
		
		public Autor(int i, String a)  
		{   
			setId(i);   
			nombreAutor = a;
		}   
		
		
		public String getNombreAutor()  
		{   
			return nombreAutor;  
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

