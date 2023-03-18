package es.studium.practica4;

import java.io.IOException;  
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;  
import javax.naming.InitialContext; 
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
import javax.sql.DataSource;  

@WebServlet( 
		name = "LoginServlet",   
		urlPatterns = {"/login"})  

public class LoginServlet extends HttpServlet  
{  
	private static final long serialVersionUID = 1L;  
	// Pool de conexiones a la base de datos  
	private DataSource pool;   

	public LoginServlet()   
	{   
		super();  
	}  
	public void init(ServletConfig config) throws ServletException  
	{   
		super.init(config); 
		ServletControlador servletControler = new ServletControlador();
		servletControler.init(config);
		
		try   
		{    

			// Crea un contexto para poder luego buscar el recurso DataSource    
			InitialContext ctx = new InitialContext();    
			// Busca el recurso DataSource en el contexto    
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendaLibro");    
			if(pool == null)    
			{     
				throw new ServletException("DataSource desconocida 'mysql_tiendaLibro'");    
			}   
		}   
		catch(NamingException ex){}  
	}   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   
	{ 
		request.setCharacterEncoding("UTF-8");         
		Connection conn = null;   
		Statement stmt = null; 
		int tipoUsuarioConect = -1;
		String nextPage = "";
		try   
		{    
			   

			// Obtener una conexión del pool    
			conn = pool.getConnection();    
			stmt = conn.createStatement();    

			// Recuperar los parámetros usuario y password de la petición request    
			String usuario = request.getParameter("usuario");    

			String password = request.getParameter("password");    

			// Validar los parámetros de la petición request    
			if(usuario.length()==0)    
			{     
				//out.println("<h3>Debes introducir tu usuario</h3>");    
			}    
			else if(password.length()==0)    
			{     
				//out.println("<h3>Debes introducir tu contraseña</h3>");    
			}    
			else    
			{     
				// Verificar que existe el usuario y su correspondiente clave     
				StringBuilder sqlStr = new StringBuilder();     
				sqlStr.append("SELECT * FROM Usuarios WHERE ");    
				sqlStr.append("STRCMP(Usuarios.nombreUsuario, '").append(usuario).append("') = 0");     
				sqlStr.append(" AND STRCMP(Usuarios.claveUsuario, SHA2('").append(password).append("', 256)) = 0");     
				//out.println("<p>"+sqlStr.toString()+"</p>");     
				ResultSet rset = stmt.executeQuery(sqlStr.toString());     


				if(!rset.next())     
				{      
					// Si el resultset no está vacío 
					//out.println("<h3>Nombre de usuario o contraseña incorrectos</h3>");      
					//out.println("<p><a href='index.html'>Volver a Login</a></p>");     
				}     
				else     
				{      
					// Si los datos introducidos son correctos      
					// Crear una sesión nueva y guardar el usuario como variable de sesión      
					// Primero, invalida la sesión si ya existe      
					HttpSession session = request.getSession(false);      
					if(session != null)      
					{      
						session.invalidate();      
					}      
					session = request.getSession(true);      
					synchronized(session)      
					{       
						session.setAttribute("usuario", usuario);      
					}      
					

					//comprobar el tipo de usuario que se ha conectado 

					tipoUsuarioConect = rset.getInt("tipoUsuario");

					//out.println(tipoUsuarioConect);
					if(tipoUsuarioConect == 0) {
						nextPage = "/carrito.jsp";
						System.out.println("soy basico");
					}else if(tipoUsuarioConect == 1){
						nextPage = "/gestion.jsp";
						System.out.println("soy admin");
					}


				}  
			}
		}
		catch(SQLException ex)   
		{    
			//out.println("<p>Servicio no disponible:</p>");    
			//out.println("<p>"+ex.getMessage()+"</p>");    
			//out.println("</body>");    
			//out.println("</html>");  
		}
		finally   
		{    
			// Cerramos objetos    
			
			try    
			{ 
				if(stmt != null) 
				{ 
					stmt.close(); 
				} 
				if(conn != null) 
				{ 
					// Esto devolvería la conexión al pool 
					conn.close(); 
				} 
			} 
			catch(SQLException ex){} 
		}
		ServletContext servletContext = getServletContext();   
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);   
		requestDispatcher.forward(request,  response);  
		System.out.println("he llegado al final de loginservlet");
	} 
}