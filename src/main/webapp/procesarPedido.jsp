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
		<title>Pedidos</title>
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
			<h1>Procesar Pedidos</h1>
		</div>   
		<div class="container-fluid">
			<div class="row">
				<form name="dropLibro" action="shopping" method = "POST"> 
				  	<div class="col-12 text-center mb-3">
					  	<h3>Selecciona el pedido que quieres procesar</h3>
				  	</div>
				  	<div class="col-12 text-center mb-3">
					  	<label for="stock">Pedidos:</label>
					  	<select name="selectPedido">     
					<%
	     				// Scriplet 1: Carga los pedidos en el SELECT  
	     							for (int i = 0; i < modeloPedidos.tamano(); i++) 
									    {
									        if(modeloPedidos.getFechaEnvioPedido(i)== null){
									        	out.println("<option value='" + i + "'>");
									        	out.println(modeloPedidos.getIdPedido(i) + " | " + modeloPedidos.getFechaPedido(i) + " | " + modeloPedidos.getUsuarioPedido(i));
												out.println("</option>");  
									        }
										} 
	     			%>    
						</select>
				  	</div>
				  	<div class="col-12 text-center mb-3">
				  		<input type="hidden" name="processed" value="agregarFechaProcesado"> 
					  	<input type="submit" value="Procesar pedido" class="bg-success border border-0"> 
				  	</div>
				</form>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 text-center"> 
					<form name="volverPedido" action="shopping" method = "POST">
					  	<input type="hidden" name="processed" value="volverProcesPedido"> 
						<input type="submit" value="Volver" class="bg-warning border border-0"> 
					</form>
				</div>
			</div>
		</div>
	</body> 
</html>