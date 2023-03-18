package es.studium.practica4;

import java.util.Date;

public class pedidos 
{
	
	private int id;  
	private Date fechaPedido;  
	private Date fechaEnvioPedido; 
	private String usuarioPedido;  
	
			
			public pedidos()  
			{   
				setId(0);   
				fechaPedido = null; 
				fechaEnvioPedido = null;
				usuarioPedido = "";
			}
			
			public pedidos(int i, Date fp, Date fep, String up)  
			{   
				setId(i);   
				fechaPedido = fp;
				fechaEnvioPedido = fep;
				usuarioPedido = up;
			}   
			
			
			public Date getFechaPedido()  
			{   
				return fechaPedido;  
			} 
			
			public Date getFechaEnvioPedido()  
			{   
				return fechaEnvioPedido;  
			}  
			
			public String getUsuarioPedido()  
			{   
				return usuarioPedido;  
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

