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
		<title>Modificar Libro</title>
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
		<%
			//obtenemos la posisición del libro sleecionado en el select para pasarlo por parametros para obtener sus datos
			int idTituloLibro = Integer.parseInt(request.getParameter("selectLibro"));
		System.out.println(idTituloLibro);
			
		%>

		<div class="text-center">
			<h1>Modificar Libro</h1>
		</div>   
		<div class="text-center">   
			<p class="text-muted"><strong>Modifica el libro</strong></p> 
			<br/>   
			<form name="modificarform" action="shopping" method = "POST">    	
				<input type="hidden" name="modify" value="confirmarModificar">       
				<p class="text-muted ">Título: </p>
				<input type="text" name="nombreTitulo" value="<%= modificarLibro.getTitulo(idTituloLibro)%>">        
				<p class="text-muted ">Precio: </p>
				<input type="text" name="precio" value="<%= modificarLibro.getPrecio(idTituloLibro) %>">       
				<p class="text-muted ">Stock: </p>
				<input type="text" name="stock" value="<%= modificarLibro.getAlmacenLibro(idTituloLibro) %>">
				  <br>
				<p class="text-muted ">Autor: </p>  
				<select name="selectAutor">     
					<%      
						String Autor = modificarLibro.getNombreAutor(idTituloLibro);
					System.out.println(Autor);
					
	     							for(int i = 0; i < modeloAutor.tamano(); i++)      
	     							{       
	     								out.println("<option value='" + i);
	     								if (modeloAutor.getNombreAutor(i) == Autor)
	     								{
	     									out.print("selected");
	     								}
	     								out.println("'>");
	     								out.println(modeloAutor.getNombreAutor(i));       
	     								out.println("</option>");      
	     							}
	     			%>    
				</select>
				<p class="text-muted ">Editorial: </p> 
				<select name="selectEditorial">     
					<%      
						String Editorial = modificarLibro.getNombreEditorial(idTituloLibro);
					
	     							for(int i = 0; i < modeloEditorial.tamano(); i++)      
	     							{       
	     								out.println("<option value='" + i);
	     								if (modeloEditorial.getNombreEditorial(i) == Editorial)
	     								{
	     									out.print("selected");
	     								}
	     								out.println("'>");
	     								out.println(modeloEditorial.getNombreEditorial(i));       
	     								out.println("</option>");      
	     							}
	     			%>    
				</select>
				         <br>
				<input type="submit" value="Confirmar" class="bg-warning border border-0 mt-3"> 
			</form>
		</div>
	</body> 
</html> 