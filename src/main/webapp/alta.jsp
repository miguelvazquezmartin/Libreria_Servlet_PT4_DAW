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
			<h1>Agregar nuevo Libro</h1>
		</div>   
		<div class="container-fluid">
			<div class="row">
				<form name="AgregarLibro" action="shopping" method = "POST"> 
				  	<div class="col-12 text-center mb-3">
					  	<label for="titulo">Título:</label>
					  	<input type="text" id="titulo" name="titulo" required>
				  	</div>
				  	<div class="col-12 text-center mb-3">
					  	<label for="precio">Precio:</label>
					  	<input type="text" id="precio" name="precio" required>
				  	</div>
				  	<div class="col-12 text-center mb-3">
					  	<label for="stock">Stock:</label>
					  	<input type="text" id="stock" name="stock" required>
				  	</div>
				  	<div class="col-12 text-center mb-3">
					  	<label for="stock">Autor:</label>
					  	<select name="autor" required>     
					<%
	     				//Carga la lista de autores en el select 
	     							for(int i = 0; i < modeloAutor.tamano(); i++)      
	     							{       
	     								out.println("<option value='" + i + "'>");       
	     								out.println(modeloAutor.getNombreAutor(i));       
	     								out.println("</option>");      
	     							}
	     			%>    
						</select>
				  	</div>
				  	<div class="col-12 text-center mb-3">
					  	<label for="stock">Editorial:</label>
					  	<select name="editorial" required>     
					<%
	     				// carga la lsita de editoriales en el select     
	     							for(int i = 0; i < modeloEditorial.tamano(); i++)      
	     							{       
	     								out.println("<option value='" + i + "'>");       
	     								out.println(modeloEditorial.getNombreEditorial(i));       
	     								out.println("</option>");      
	     							}
	     			%>    
						</select>
				  	</div>
				  	<div class="col-12 text-center mb-3">
				  		<input type="hidden" name="send" value="createBook"> 
					  	<input type="submit" value="Agregar libro" class="bg-success border border-0"> 
				  	</div>
				</form>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 text-center"> 
					<form name="AgregarLibro" action="shopping" method = "POST">
					  	<input type="hidden" name="send" value="volverAlta"> 
						<input type="submit" value="Volver" class="bg-warning border border-0"> 
					</form>
				</div>
			</div>
		</div>
	</body> 
</html>
