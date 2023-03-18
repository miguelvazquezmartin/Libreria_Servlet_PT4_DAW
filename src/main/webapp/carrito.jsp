<%-- Página de órdenes de pedido --%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page session="true" import="java.util.*, es.studium.practica4.*" %> 
<!DOCTYPE html> 
<html lang=”es”>  
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<!-- Bootstrap links -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">  
		<title>Pedido</title>
		<style>
 			@import url('https://fonts.googleapis.com/css2?family=Tilt+Prism&display=swap');
 			
 			body{
 				background-image: url("https://unsplash.com/es/fotos/o0Qqw21-0NI");
 			}
 			
 			h1{
	 			font-size: 80px;
	  			font-weight: bold;
	  			color: #646464;
	  			font-family: Tilt Prism;
	  			margin-bottom: 20px;
 			}
		</style>  
	</head>  
	<body>
		<div>
			<div class="text-center">
				<h1>Librería</h1>
			</div> 
			<div class="text-end mx-5">
				<form name="AgregarForm" action="shopping" method = "POST">    	
					<input type="hidden" name="todo" value="logout">      
					<input type="submit" value="Cerrar Sesión" class="bg-danger border border-0 text-white">   
				</form> 
			</div>
		</div>  
		<div class="text-center">   
			<p class="text-muted"><strong>Elegir un libro y la cantidad:</strong></p> 
			<br/>   
			<form name="AgregarForm" action="shopping" method = "POST">    	
				<input type="hidden" name="todo" value="add">   
				<p class="text-muted ">Título: </p>    
				<select name="idLibro">     
					<%
	     				// Scriplet 1: Carga los libros en el SELECT      
	     							for(int i = 0; i < Libreria.tamano(); i++)      
	     							{       
	     								out.println("<option value='" + i + "'>");       
	     								out.println(Libreria.getTitulo(i) + " | " + Libreria.getNombreAutor(i) + " | "  + Libreria.getPrecio(i) + " $ | " + Libreria.getAlmacenLibro(i));       
	     								out.println("</option>");      
	     							}
	     			%>    
				</select>
				<br/>     
				<p class="text-muted ">Cantidad: </p>  
					<input type="text" name="cantidad" size="10" value="1">    
					<input type="submit" value="Añadir a la cesta" class="bg-warning border border-0">   
			</form>   
		</div>   
		<br/>   
	
			<%     
			// Scriplet 2: Chequea el contenido de la cesta    
			ArrayList<ElementoPedido> cesta = (ArrayList<ElementoPedido>) session.getAttribute("carrito");   
			if(cesta != null && cesta.size() > 0)   
			{   
			%>  
			<div class="text-center">  
				<p><strong>Tu cesta contiene:</strong></p>
				<table border="1" class="table table-success table-striped">     
					<tr class="table-dark">      
						<th>Autor</th>
						<th>Título</th>
						<th>Precio</th>
						<th>Cantidad</th>
						<th>&nbsp;</th>     
					</tr>     
					<%      
						// Scriplet 3: Muestra los libros del carrito      
						for(int i = 0; i<cesta.size(); i++)      
						{       
							ElementoPedido elementoPedido = cesta.get(i);    
					%>       
					<tr>        
						<form name="borrarForm" action="shopping" method="POST">         
							<input type="hidden" name="todo" value="remove">         
							<input type="hidden" name="indiceElemento" value="<%= i%>">         
							<td><%= elementoPedido.getTitulo() %></td>         
							<td><%= elementoPedido.getAutor() %></td>         
							<td align="right"><%= elementoPedido.getPrecio() %> $</td>         
							<td align="right"><%= elementoPedido.getCantidad() %></td>         
							<td><input type="submit" value="Eliminar de la cesta" class="bg-danger border border-0"></td>        
						</form>       
					</tr>      
					<%      
						}      
					%>    
				</table>    
				<br/>    
				<form name="checkoutForm" action="shopping" method="POST">     
					<input type="hidden" name="todo" value="checkout">     
					<input type="submit" value="Confirmar compra" class="bg-success border border-0">    
				</form> 
			</div>  
			<%
			}   
			%>  
	</body> 
</html> 