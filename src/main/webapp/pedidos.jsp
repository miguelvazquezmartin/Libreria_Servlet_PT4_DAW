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
			<h1>Pedidos</h1>
		</div>  
		<div class="text-center">
			<h4>Listado de pedidos pendientes de procesar</h4>
		</div>  
		<div class="text-center">
			<table class="table table-striped"> 
				<thead>
			      <tr class="table-dark">
			      	<td>ID</td>
			      	<td class="text-end">Fecha pedido pendiente</td>
			      	<td class="text-end">Fecha pedido procesado</td>    
			      	<td class="text-end">Usuario</td>    
			      </tr>             
			   	</thead>
		        <tbody class="text-center">
		         	<%
					    for (int i = 0; i < modeloPedidos.tamano(); i++) 
					    {
					        if(modeloPedidos.getFechaEnvioPedido(i) == null){
					        	out.println("<tr>");
						        out.println(" <td class='text-start'>" + modeloPedidos.getIdPedido(i) + "<td>");
						        out.println(" <td class='text-start'>" + modeloPedidos.getFechaPedido(i) + "<td>");
								out.println(" <td class='text-start'>" + modeloPedidos.getFechaEnvioPedido(i) + "<td>");
								out.println(" <td class='text-start'>" + modeloPedidos.getUsuarioPedido(i) + "<td>");
								out.println("</tr>");		
					        }
						} 
					%>      
			               		
		       	</tbody>          
		    </table>
		    <div class="text-center mt-5">
			<h4>Listado de pedidos Procesados</h4>
		</div>  
		<div class="text-center">
			<table class="table table-striped"> 
				<thead>
			      <tr class="table-dark">
			      	<td>ID</td>
			      	<td class="text-end">Fecha pedido procesado</td>  
			      	<td class="text-end">Usuario</td>        
			      </tr>             
			   	</thead>
		        <tbody class="text-center">
		         	<%
					    for (int i = 0; i < modeloPedidos.tamano(); i++) 
					    {
					        if(modeloPedidos.getFechaEnvioPedido(i)!= null){
					        	out.println("<tr>");
						        out.println(" <td class='text-start'>" + modeloPedidos.getIdPedido(i) + "<td>");
						        out.println(" <td class='text-start'>" + modeloPedidos.getFechaPedido(i) + "<td>");
								out.println(" <td class='text-start'>" + modeloPedidos.getFechaEnvioPedido(i) + "<td>");
								out.println(" <td class='text-start'>" + modeloPedidos.getUsuarioPedido(i) + "<td>");
								out.println("</tr>");	
					        }
						} 
					%>      
			               		
		       	</tbody>          
		    </table>
		    <div class="container-fluid">
				<div class="row text-center">
					<div class="col-6 py-5">
						<form name="volver" action="shopping" method="POST">     
							<input type="hidden" name="processOrder" value="volverPedido">     
							<input type="submit" value="Volver" class="bg-warning border border-0">  
						</form> 
					</div>
					<div class="col-6 py-5">
						<form name="volver" action="shopping" method="POST">     
							<input type="hidden" name="processOrder" value="procesarPedido">     
							<input type="submit" value="Procesar Pedido" class="bg-success text-white border border-0">  
						</form> 
					</div>
				</div>
			</div> 
		</div>
	</body> 
</html>