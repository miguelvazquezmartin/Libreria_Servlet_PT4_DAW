package es.studium.practica4;

public class Editorial 
{
	
			private int id;  
			private String nombreEditorial;  
			
			
			public Editorial()  
			{   
				setId(0);   
				nombreEditorial = ""; 
			}
			
			public Editorial(int i, String a)  
			{   
				setId(i);   
				nombreEditorial = a;
			}   
			
			
			public String getNombreEditorial()  
			{   
				return nombreEditorial;  
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


