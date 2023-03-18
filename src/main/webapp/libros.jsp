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
			<h1>Libros</h1>
		</div>   
		<div class="text-center">
			<table class="table table-striped "> 
				<thead>
			      <tr class="table-dark">
			      	<td>Nombre</td>
			      	<td class="text-end">Precio</td> 
			      	<td class="text-end">Stock</td>       
			      </tr>             
			   	</thead>
		        <tbody>
		         	<%
					    for (int i = 0; i < Libreria.tamano(); i++) 
					    {
					        out.println("<tr>");
					        out.println(" <td>" + Libreria.getTitulo(i) + "<td>");
					        out.println(" <td class="+"text-center"+">" + Libreria.getPrecio(i) + "<td>");
					        out.println(" <td class="+"text-center"+">" + Libreria.getAlmacenLibro(i) + "<td>");
							out.println("</tr>");		
						} 
					%>      
			               		
		       	</tbody>          
		    </table>
		</div>
		<div class="container-fluid">
			<div class="row text-center">
				<div class="col-4">
					<h5>Agregar Libro</h5>
					<form name="do" action="shopping" method="POST">     
						<input type="hidden" name="crud" value="alta">     
						<input type="submit" value="altaLibro" class="bg-success border border-0">  
					</form> 
				</div>
				<div class="col-4">
					<h5>Borrar Libro</h5>
					<form name="do" action="shopping" method="POST">     
						<input type="hidden" name="crud" value="baja">     
						<input type="submit" value="bajaLibro" class="bg-success border border-0">  
					</form> 
				</div>
				<div class="col-4">
					<h5>Modificar Libro</h5>
					<form name="do" action="shopping" method="POST">     
						<input type="hidden" name="crud" value="modificar">     
						<input type="submit" value="modificarLibro" class="bg-success border border-0">  
					</form> 
				</div>
			</div>
		</div> 
		<div class="container-fluid">
			<div class="row text-center">
				<div class="col-12 py-5">
					<form name="do" action="shopping" method="POST">     
						<input type="hidden" name="crud" value="volver">     
						<input type="submit" value="Volver" class="bg-warning border border-0">  
					</form> 
				</div>
			</div>
		</div> 
	</body> 
</html>