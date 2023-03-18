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
		<title>Programa gestion</title>
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
			#box{
				border-radius: 15%;
				
			}
		</style>  
	</head>  
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 text-center">
					<h1>Programa de gestión</h1>
				</div>
				<div class="col-12 text-center">
					<h3 class="mb-5">Consulta los diferentes aspectos del programa</h3>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row text-center">
				<div class="col-3">
					<h5>Consulta Autores</h5>
					<form name="goto" action="shopping" method="POST">     
						<input type="hidden" name="make" value="autor">     
						<input type="submit" value="Autores" class="bg-success border border-0">  
					</form> 
				</div>
				<div class="col-3">
					<h5>Consulta Editoriales</h5>
					<form name="goto" action="shopping" method="POST">     
						<input type="hidden" name="make" value="editorial">     
						<input type="submit" value="Editoriales" class="bg-success border border-0">
					</form>
				</div>
				<div class="col-3">
					<h5>Consulta Libros</h5>
					<form name="goto" action="shopping" method="POST">     
						<input type="hidden" name="make" value="libros">     
						<input type="submit" value="Libros" class="bg-success border border-0">
					</form>
				</div>
				<div class="col-3">
					<h5>Consulta Pedidos</h5>
					<form name="goto" action="shopping" method="POST">     
						<input type="hidden" name="make" value="pedidos">     
						<input type="submit" value="Pedidos" class="bg-success border border-0">
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12 text-center my-5 py-5">
				<form name="goto" action="shopping" method="POST">     
					<input type="hidden" name="make" value="logout">     
					<input type="submit" value="Cerrar Sesión" class="bg-danger border border-0 text-white"> 
				</form>
			</div>
		</div>
	</body> 
</html> 