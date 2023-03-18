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
		<title>Libros</title>
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
		<div class="text-center">
			<h1>Modificar un libro</h1>
		</div>   
		<div class="container-fluid">
			<div class="row">
				<form name="modLibro" action="shopping" method = "POST"> 
				  	<div class="col-12 text-center mb-3">
					  	<h3>Elige el libro que quieres modificar</h3>
				  	</div>
				  	<div class="col-12 text-center mb-3">
					  	<label for="stock">Libros:</label>
					  	<select name="selectLibro">     
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
				  	</div>
				  	<div class="col-12 text-center mb-3">
				  		<input type="hidden" name="modify" value="modifyBook"> 
					  	<input type="submit" value="Seleccionar libro" class="bg-primary border border-0"> 
				  	</div>
				</form>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 text-center mb-3">
					<form name="modLibro" action="shopping" method = "POST"> 
				  		<input type="hidden" name="modify" value="volverModificar"> 
					  	<input type="submit" value="Volver" class="bg-warning border border-0"> 
					</form>
				</div>
			</div>
		</div>
	</body> 
</html>
