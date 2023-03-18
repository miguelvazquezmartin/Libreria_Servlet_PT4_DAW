package es.studium.practica4;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.Formatter; 
import java.util.Iterator; 
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletConfig; 
import javax.servlet.ServletContext; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/shopping") 
public class ServletControlador extends HttpServlet  
{  
	private static final long serialVersionUID = 1L; 
	
	//clase modelo
	claseModelo modelo = new claseModelo();
	public void init(ServletConfig conf) throws ServletException
	{    
		super.init(conf);  
		
		Libreria.cargarDatos();
		modeloAutor.listaAutores();
		modeloEditorial.listaEditoriales();
		borrarLibro.dropLibro();
		modificarLibro.modLibro();
		modeloPedidos.listaPedidos();
		modeloPedidoFecha.agregarFechaPedido();
		
		
	}  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   
	{   
		doPost(request, response);  
	}   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   
	{   
		request.setCharacterEncoding("UTF-8");   
		
		// Recupera la sesión actual o crea una nueva si no existe   
		HttpSession session = request.getSession(true);    
		
		// Recupera el carrito de la sesión actual   
		@SuppressWarnings("unchecked")   
		ArrayList<ElementoPedido> elCarrito = (ArrayList<ElementoPedido>) session.getAttribute("carrito");    
		
		// Determina a qué página jsp se redirigirá   
		String nextPage = "";   
		String todo = request.getParameter("todo"); 
		String make = request.getParameter("make");
		String crud = request.getParameter("crud");
		String send = request.getParameter("send");
		String drop = request.getParameter("drop");
		String modify = request.getParameter("modify");
		String processOrder = request.getParameter("processOrder");
		String processed = request.getParameter("processed");
		
		if(todo==null)   
		{    
			// Primer acceso, redirigir a carrito.jsp    
			nextPage = "/carrito.jsp";   
		}   
		else if(todo.equals("add"))   
		{    
			//creamos las variables que nos serviran para establcer si la cantidad de libros que el cliente esta pidiendo se encuentran disponibles o no
			int id = Integer.parseInt(request.getParameter("idLibro"));
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			int almacen = Libreria.getAlmacenLibro(id);
			
			//establecemos la relación para prabar si hay libros suficientes 
			if(cantidad <= almacen) 
			{
				// Mandado por carrito.jsp con los parámetros idLibro y cantidad    
				// Creamos un elementoPedido y lo añadimos al carrito    
				ElementoPedido nuevoElementoPedido = new ElementoPedido(Integer.parseInt(request.getParameter("idLibro")), Integer.parseInt(request.getParameter("cantidad")));    
				if(elCarrito==null) 
				{     
					// El carrito está vacío     
					elCarrito = new ArrayList<>();     
					elCarrito.add(nuevoElementoPedido);     
					
					// Enlazar el carrito con la sesión     
					session.setAttribute("carrito", elCarrito);     
				}    
				else    
				{     
					boolean encontrado = false;     
					Iterator<ElementoPedido> iter = elCarrito.iterator();     
					// Comprueba si el libro está ya en el carrito
					while(!encontrado&&iter.hasNext())      
					{      
						ElementoPedido unElementoPedido = (ElementoPedido)iter.next(); 
						
						// Si lo está, actualizamos la cantidad     
						if(unElementoPedido.getIdLibro() == nuevoElementoPedido.getIdLibro())      
						{      
							int nuevaCantidad = unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad();
							// comapramos la nueva cantidad con el almacen 
							if(nuevaCantidad <= almacen)
							{
								unElementoPedido.setCantidad(nuevaCantidad);
								encontrado = true;
							}
							else
							{
								//si la cantidad es superior a la de almacen lanza un error 
								throw new ServletException("La cantidad de libros que quiere superan nuestras existencias actualmente");
							}	     
						}     
					}    
					// Si no está, lo añadimos     
					if(!encontrado)     
					{      
						// Lo añade al carrito      
						elCarrito.add(nuevoElementoPedido);    
					}    
				}    
				
				// Volvemos a carrito.jps para seguir con la compra    
				nextPage = "/carrito.jsp"; 
			}
			else 
			{
				//si la cantidad es superior a la de almacen lanza un error 
				throw new ServletException("La cantidad de libros que quiere supera nuestras existencias actualmente");
			}
			   
		}   
		else if(todo.equals("remove"))   
		{    
			// Enviado por carrito.jsp con el parámetro indiceElemento
			// Borra el elemento indiceElemento del carrito   
			int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));    
			elCarrito.remove(indiceCarrito);    
			
			// Vuelve a carrito.jsp para seguir con la compra    
			nextPage = "/carrito.jsp";   
		}   
		else if (todo.equals("checkout"))   
		{    
			// Enviado por carrito.jsp    
			// Calcula el precio total de todos los elementos del carrito    
			double precioTotal = 0;    
			int cantidadTotalOrdenada = 0;    
			for(ElementoPedido item: elCarrito)    
			{     
				double precio = item.getPrecio();     
				int cantidadOrdenada = item.getCantidad();     
				precioTotal += precio * cantidadOrdenada;     
				cantidadTotalOrdenada += cantidadOrdenada;    
			}    
			
			// Da formato al precio con dos decimales    
			StringBuilder sb = new StringBuilder();    
			Formatter formatter = new Formatter(sb);    
			formatter.format("%.2f", precioTotal);    
			formatter.close();    
			
			//obtener usuario de la sesion 
			String user = (String) session.getAttribute("usuario");
			//obtener la fecha
			LocalDate date = LocalDate.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy MM dd");
			String text = date.format(formato);
			LocalDate parsedDate = LocalDate.parse(text, formato);
			//llamar a la clase
			try {
				claseModelo.insertarFecha(user, parsedDate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Coloca el precioTotal y la cantidadtotal en el request    
			request.setAttribute("precioTotal", sb.toString());    
			request.setAttribute("cantidadTotal", cantidadTotalOrdenada+"");    
			
			// Redirige a checkout.jsp    
			nextPage = "/checkout.jsp";   
		} 
		else if(todo.equals("logout"))   
		{     
			HttpSession sessionOff = request.getSession(false);
			if (sessionOff != null) {
				sessionOff.invalidate();
			}
			nextPage = "/index.jsp";
		}
		
		
		//paquete de redirección a las paginas que se indican desde la pagina de gestión
		if(make==null)   
		{    
			System.out.println("no debo hacer nada make");
		}
		else if(make.equals("autor"))  
		{ 
			// acceso a lista de autores   
			nextPage = "/autor.jsp";
		}
		else if(make.equals("editorial"))  
		{ 
			// acceso a lista de editoriales   
			nextPage = "/Editorial.jsp";
		}
		else if(make.equals("pedidos"))  
		{ 
			// acceso a lista de pedidos     
			nextPage = "/pedidos.jsp";
		}
		else if(make.equals("libros"))  
		{ 
			// acceso a lista de libros   
			nextPage = "/libros.jsp";
		}
		else if(make.equals("logout"))  
		{     
			HttpSession sessionOffGestion = request.getSession(false);
			if (sessionOffGestion != null) {
				sessionOffGestion.invalidate();
			}
			nextPage = "/index.jsp";
		}
		
		//paquete de redirección a las paginas que se indican desde la pagina de libros
		if(crud==null)   
		{    
			System.out.println("no debo hacer nada crud");
		}
		else if(crud.equals("alta"))  
		{ 
			// acceso a la página de alta de libro   
			nextPage = "/alta.jsp";
		}
		else if(crud.equals("baja"))  
		{ 
			// acceso a la página de baja de libro    
			nextPage = "/baja.jsp";
		}
		else if(crud.equals("modificar"))  
		{ 
			// acceso a la página de modificar libro   
			nextPage = "/modificar.jsp";
		}	
		else if(crud.equals("volver"))  
		{ 
			// volver a la pagina de gestion desde la pagina de libros
			nextPage = "/gestion.jsp";
		}
		else if(crud.equals("volverAutor"))  
		{ 
			// volver a la pagina de gestion desde la pagina de Autores
			nextPage = "/gestion.jsp";
		}
		else if(crud.equals("volverEditorial"))  
		{ 
			// volver a la pagina de gestion desde la pagina de Autores
			nextPage = "/gestion.jsp";
		}
		
		//obtener los datos para la funcion de agregar Editorial 
		if(send == null)
		{
			System.out.println("no hacer nada send");
		}
		else if(send.equals("createBook")) 
		{	
			//obtenemos los parametros introducidos por el cliente para agregar un nuevo libro a la biblioteca
			String tituloLibro = request.getParameter("titulo");
			int precioLibro = Integer.parseInt(request.getParameter("precio"));
			int stockLibro = Integer.parseInt(request.getParameter("stock"));
			int idAutorLibro = Integer.parseInt(request.getParameter("autor"));
			int idEditorialLibro = Integer.parseInt(request.getParameter("editorial"));
			
			//pasamos los elementos ya procesados para ser utilizados directamente
			altaLibro.createLibro(tituloLibro, precioLibro, stockLibro, modeloAutor.getIdAutor(idAutorLibro), modeloEditorial.getIdEditorial(idEditorialLibro));
			//volvemos a la pagina de libros
			Libreria.cargarDatos();
			nextPage = "/libros.jsp";
			
		}
		else if(send.equals("volverAlta")) 
		{
			//vovlemos a la pagina de libros desde la página de alta de libros
			nextPage = "/libros.jsp";
		}
		
		//obtener los datos para la funcion de borrar libro 
		if(drop == null)
		{
			System.out.println("no hacer nada drop");
		}
		else if(drop.equals("dropBook")) 
		{
			//obtenemos la posición del elemento seleccionado en el select
			int idTituloLibro = Integer.parseInt(request.getParameter("selectLibro"));
			//tomamos el id del elemento seleccionado y lo pasamos 
			borrarLibro.dropLibro(Libreria.getIdLibro(idTituloLibro));
			//volvemos a la pagina de libros
			Libreria.cargarDatos();
			nextPage = "/libros.jsp";
		}
		else if(drop.equals("volverBaja")) 
		{
			//vovlemos a la pagina de libros desde la página de baja de libros
			nextPage = "/libros.jsp";
		}
		
		//modificar Libro
		if(modify == null)
		{
			System.out.println("no hacer nada modify");
		}
		else if(modify.equals("modifyBook")) 
		{
			//obtenemos la posición del elemento seleccionado en el select
			int idTituloLibro = Integer.parseInt(request.getParameter("selectLibro"));
			//tomamos el id del elemento seleccionado y lo pasamos 
			modificarLibro.modLibro(Libreria.getIdLibro(idTituloLibro));
			System.out.println("estoy probando a ver que tiene "+ Libreria.getIdLibro(idTituloLibro));
			//enviamos a la nueva página de modificar los libros donde cargamos los inputs
			nextPage = "/confirmarModificar.jsp";
		}
		else if(modify.equals("volverModificar")) 
		{
			//vovlemos a la pagina de libros desde la página de modificar de libros
			nextPage = "/libros.jsp";
		}
		
		//Procesar pedidos
		
		if(processOrder==null)   
		{    
			System.out.println("no debo hacer nada processOrder");
		}
		else if(processOrder.equals("volverPedido"))  
		{ 
			// volver a la pagina principal de gestion   
			nextPage = "/gestion.jsp";
		}
		else if(processOrder.equals("procesarPedido"))  
		{ 	
			
			// volver a la pagina principal de gestion   
			nextPage = "/procesarPedido.jsp";
		}
		
		//Procesar pedidos
		
		if(processed==null)   
		{    
			System.out.println("no debo hacer nada processed");
		}
		else if(processed.equals("volverProcesPedido"))  
		{ 
			// volver a la pagina principal de pedidos   
			nextPage = "/pedidos.jsp";
		}
		else if(processed.equals("agregarFechaProcesado"))  
		{ 
			LocalDate datePros = LocalDate.now();
			DateTimeFormatter formatoPros = DateTimeFormatter.ofPattern("yyyy MM dd");
			String text = datePros.format(formatoPros);
			LocalDate parsedDatePros = LocalDate.parse(text, formatoPros);
			//obtenemos la posición del elemento seleccionado en el select
			int idPedidoSeleccionado = Integer.parseInt(request.getParameter("selectPedido"));
			//tomamos el id del elemento seleccionado y lo pasamos 
			modeloPedidoFecha.agregarFechaPedido(modeloPedidos.getIdPedido(idPedidoSeleccionado), parsedDatePros);
			modeloPedidos.listaPedidos();
			// volver a la pagina principal de pedidos   
			nextPage = "/gestion.jsp";
		}
		
		ServletContext servletContext = getServletContext();   
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);   
		requestDispatcher.forward(request,  response);  
	} 
}
	
		
				
					
				
			