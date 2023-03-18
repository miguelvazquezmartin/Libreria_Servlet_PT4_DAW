<%-- Página de confirmación del pedido --%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page session="true" import="java.util.*, es.studium.practica4.*" %>  
<!DOCTYPE html> 
<html lang=”es”>  
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<!-- Bootstrap links -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">  
		<title>Confirmación</title>
		<style>
 			@import url('https://fonts.googleapis.com/css2?family=Tilt+Prism&display=swap');
 			
 			body{
 				background-image: url("https://unsplash.com/es/fotos/o0Qqw21-0NI");
 			}
 			
 			h1{
	 			font-size: 50px;
	  			font-weight: bold;
	  			color: #646464;
	  			font-family: Tilt Prism;
	  			margin-bottom: 20px;
 			}
 			
 			a{
 			color: #00BC39;
 			}
		</style>  
	</head>   
	<body>   
		<h1 class="">Confirmación del pedido </h1>   
		<br/>   
		<p class="text-muted px-5"><strong>Has comprado los siguientes libros:</strong></p>   
		<table border="1" class="table table-striped text-center">    
			<tr class="table-dark">     
				<th>Autor</th>
				<th>Título</th>
				<th>Precio</th>
				<th>Cantidad</th>    
			</tr>    
			<%     
				// Muestra los elementos del carrito     
				ArrayList<ElementoPedido> cesta = (ArrayList<ElementoPedido>) session.getAttribute("carrito");     
				for(ElementoPedido item:cesta)     
				{    
			%>     
			 <tr>       
				 <td><%= item.getTitulo() %></td>       
				 <td><%= item.getAutor() %></td>       
				 <td align="center"><%= item.getPrecio() %> €</td>      
				 <td align="center"><%= item.getCantidad() %></td>      
			 </tr>    
			<%     
				}
				session.removeAttribute("carrito");    
			%>    
			<tr>     
				<th align="right" colspan="2">Total</th>     
				<th align="right"><%= request.getAttribute("precioTotal") %></th>     
				<th align="right"><%= request.getAttribute("cantidadTotal") %></th>    
			</tr>   
		</table>   
		<br/>   
		<a href="shopping" class="text-center text-decoration-none">Pulsa aquí para realizar otro pedido</a>  
	</body> 
</html> 