package es.studium.practica4;

public class LibroMod 
{
	
		private int id;  
		private String titulo;  
		private String nombreAutor;  
		private double precio; 
		private int almacenLibro;
		private int idAutor;
		private int idEditorial;
		private String nombreEditorial;
		
		
		public LibroMod()  
		{   
			setId(0);   
			titulo = ""; 
			nombreAutor = ""; 
			precio = 0.0;
			almacenLibro=0;
			idAutor =0;
			idEditorial = 0;
			nombreEditorial = "";
		} 

		public LibroMod(int i, String t, double p, int al, int ida, String a, int ide, String ne) {
			setId(i);   
			titulo = t; 
			nombreAutor = a;
			precio = p;
			almacenLibro = al;
			idAutor =ida;
			idEditorial = ide;
			nombreEditorial = ne;
			
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

		public int getIdAutor() {
			return idAutor;
		}

		public int getIdEditorial() {
			return idEditorial;
		}

		public String getNombreEditorial() {
			return nombreEditorial;
		} 
}


